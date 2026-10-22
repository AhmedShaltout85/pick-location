# Phases To Implement — Enable / Disable User

**Date:** 22-10-2026
**Full plan:** `.opencode/plans/enable_disable_users_22-10-2026.md`
**Entity:** `pick_location_users` (`PickLocationUsersEntity`)

---

## Decisions

| Decision | Choice |
|----------|--------|
| Storage | `int isActive` (1 = active, 0 = disabled), column `is_active INT NOT NULL DEFAULT 1` |
| Endpoints | Separate `enable` / `disable` + list-by-status |
| Login gating | Disabled user → HTTP 403 |
| Audit columns | None |

**Files modified: 7** + 1 manual DDL statement.

---

## Phase 0 — Database DDL (manual)

Run in SSMS against `pick_location` on `localhost:1433` (`awco` / `awco`):

```sql
ALTER TABLE pick_location_users ADD is_active INT NOT NULL DEFAULT 1;
```

> `ddl-auto=none` and no Flyway/Liquibase exists — this must be run manually **before** the app is started, otherwise every query on `pick_location_users` fails.
> `DEFAULT 1` back-fills existing rows as active.

**Done when:** `SELECT is_active FROM pick_location_users;` returns `1` for every row.

---

## Phase 1 — Entity

**File:** `src/main/java/com/a08r/pick_location/models/users/PickLocationUsersEntity.java`

Add after the `technicalId` field (currently line 28):

```java
    @Column(name = "is_active", nullable = false)
    private int isActive; // 1 = enabled, 0 = disabled
```

**Done when:** the field exists and the Lombok accessors are `getIsActive()` / `setIsActive()`.

---

## Phase 2 — DTO

**File:** `src/main/java/com/a08r/pick_location/models/dto/PickLocationUsersDTO.java`

Add field:

```java
    private int isActive; // 1 = enabled, 0 = disabled
```

**Done when:** the field exists with the same name as in the entity.

---

## Phase 3 — Mapper

**File:** `src/main/java/com/a08r/pick_location/models/mapper/PickLocationUsersMapperImpl.java`

In `pickLocationUsersDTOToPickLocationUsersEntity(...)`, after `setTechnicalId(...)`:

```java
        pickLocationUsersEntity.setIsActive(pickLocationUsersDTO.getIsActive());
```

In `pickLocationUsersEntityToPickLocationUsersDTO(...)`, after `setTechnicalId(...)`:

```java
        pickLocationUsersDTO.setIsActive(pickLocationUsersEntity.getIsActive());
```

`IPickLocationUsersMapper` needs no change — signatures are unchanged.

**Done when:** the flag round-trips in both directions.

---

## Phase 4 — Repository

**File:** `src/main/java/com/a08r/pick_location/repositores/IPickLocationUsersRepository.java`

Add:

```java
    List<PickLocationUsersEntity> findByIsActive(int isActive);
```

**Done when:** Spring Data derives the query successfully at startup.

---

## Phase 5 — Service Interface

**File:** `src/main/java/com/a08r/pick_location/services/IPickLocationUsersServices.java`

Add:

```java
    ResponseEntity<PickLocationUsersDTO> enableUser(Long id);

    ResponseEntity<PickLocationUsersDTO> disableUser(Long id);

    ResponseEntity<List<PickLocationUsersDTO>> getUsersByStatus(int isActive);
```

**Done when:** the interface declares all three methods.

---

## Phase 6 — Service Implementation

**File:** `src/main/java/com/a08r/pick_location/services/PickLocationUsersServicesImpl.java`

### 6.1 Enable / Disable + private helper

```java
    @Override
    public ResponseEntity<PickLocationUsersDTO> enableUser(Long id) {
        return setUserActiveStatus(id, 1);
    }

    @Override
    public ResponseEntity<PickLocationUsersDTO> disableUser(Long id) {
        return setUserActiveStatus(id, 0);
    }

    private ResponseEntity<PickLocationUsersDTO> setUserActiveStatus(Long id, int isActive) {
        Optional<PickLocationUsersEntity> pickLocationUsersEntity = iPickLocationUsersRepository.findById(id);
        if (pickLocationUsersEntity.isEmpty()) {
            throw new RecordNotFoundException("the user with id: " + id + " not found!...");
        }
        PickLocationUsersEntity existingUser = pickLocationUsersEntity.get();
        existingUser.setIsActive(isActive);
        PickLocationUsersEntity updatedUser = iPickLocationUsersRepository.save(existingUser);
        PickLocationUsersDTO pickLocationUsersDTO = I_PICK_LOCATION_USERS_MAPPER
                .pickLocationUsersEntityToPickLocationUsersDTO(updatedUser);
        return new ResponseEntity<>(pickLocationUsersDTO, HttpStatus.OK);
    }
```

### 6.2 List by status

```java
    @Override
    public ResponseEntity<List<PickLocationUsersDTO>> getUsersByStatus(int isActive) {
        List<PickLocationUsersEntity> pickLocationUsersEntities = iPickLocationUsersRepository.findByIsActive(isActive);
        List<PickLocationUsersDTO> pickLocationUsersDTOList = pickLocationUsersEntities
                .stream()
                .map(I_PICK_LOCATION_USERS_MAPPER::pickLocationUsersEntityToPickLocationUsersDTO)
                .collect(Collectors.toList());
        if (pickLocationUsersDTOList.isEmpty()) {
            throw new RecordNotFoundException("Sorry, No DATA Found!...");
        }
        return new ResponseEntity<>(pickLocationUsersDTOList, HttpStatus.OK);
    }
```

### 6.3 Login gating — replaces the existing `findUserByUserNameAndPassword` body

The current implementation calls `.get()` on a possibly-empty `Optional`, so a failed login throws `NoSuchElementException` → HTTP 400 via the global catch-all handler. Replace the whole method body:

```java
    @Override
    public ResponseEntity<PickLocationUsersDTO> findUserByUserNameAndPassword(String userName, String password) {
        Optional<PickLocationUsersEntity> pickLocationUsersEntity = iPickLocationUsersRepository.findByUserNameAndPassword(userName, password);
        if (pickLocationUsersEntity.isEmpty()) {
            throw new RecordNotFoundException("the user with UserName: " + userName + " not found!...");
        }
        PickLocationUsersEntity user = pickLocationUsersEntity.get();
        if (user.getIsActive() == 0) {
            return new ResponseEntity<>("the user with UserName: " + userName + " is disabled!...", HttpStatus.FORBIDDEN);
        }
        PickLocationUsersDTO pickLocationUsersDTO = I_PICK_LOCATION_USERS_MAPPER.pickLocationUsersEntityToPickLocationUsersDTO(user);
        return new ResponseEntity<>(pickLocationUsersDTO, HttpStatus.OK);
    }
```

> The 403 is **returned**, not thrown. `errors/ExceptionHandlers.java` has a catch-all `@ExceptionHandler(Exception.class)` that would turn a thrown exception into HTTP 400. Returning directly matches how `changePassword` returns `HttpStatus.UNAUTHORIZED`.

### 6.4 `updateUser` — flag preserved

`updateUser` sets fields individually and does **not** touch `isActive`, so a profile update cannot silently re-enable a disabled account. No change required.

**Done when:** all three methods are implemented and the login gate returns 403 for `isActive == 0`.

---

## Phase 7 — Controller

**File:** `src/main/java/com/a08r/pick_location/controllers/PickLocationUsersController.java`

Add:

```java
    //TODO://PUT http://localhost:9999/pick-location/api/v1/users/id/{id}/enable
    @PutMapping("/id/{id}/enable")
    public ResponseEntity<PickLocationUsersDTO> enableUser(@PathVariable Long id) {
        return this.iPickLocationUsersServices.enableUser(id);
    }

    //TODO://PUT http://localhost:9999/pick-location/api/v1/users/id/{id}/disable
    @PutMapping("/id/{id}/disable")
    public ResponseEntity<PickLocationUsersDTO> disableUser(@PathVariable Long id) {
        return this.iPickLocationUsersServices.disableUser(id);
    }

    //TODO://GET http://localhost:9999/pick-location/api/v1/users/status/{isActive}
    @GetMapping("/status/{isActive}")
    public ResponseEntity<List<PickLocationUsersDTO>> getUsersByStatus(@PathVariable int isActive) {
        return this.iPickLocationUsersServices.getUsersByStatus(isActive);
    }
```

**Route notes**

- `PUT /id/{id}/enable` and `/id/{id}/disable` are 3 segments; the existing `PUT /id/{id}` is 2 — no conflict.
- `GET /status/{isActive}` has the same shape as the existing `GET /{userName}/{password}`. Spring MVC prefers the literal segment, so `/status/1` hits the status endpoint. Side effect: a user literally named `status` can no longer log in via that route.

---

## Phase 8 — Verification

### Compile
```bash
./mvnw compile
```
Expected: `BUILD SUCCESS`

### Manual test sequence

| # | Request | Expected |
|---|---------|----------|
| 1 | `GET /api/v1/users/all` | each user includes `"isActive": 1` |
| 2 | `PUT /api/v1/users/id/1/disable` | 200, body has `"isActive": 0` |
| 3 | `GET /api/v1/users/{userName}/{password}` (user 1) | **403** "is disabled" |
| 4 | `GET /api/v1/users/status/0` | 200, lists disabled users |
| 5 | `GET /api/v1/users/status/1` | 200, lists active users |
| 6 | `PUT /api/v1/users/id/1/enable` | 200, body has `"isActive": 1` |
| 7 | `GET /api/v1/users/{userName}/{password}` (user 1) | **200** |
| 8 | `PUT /api/v1/users/id/9999/disable` | 404 |
| 9 | `GET /api/v1/users/status/0` with none disabled | 404 |

### HTTP test file
Add the new requests to `test_new_endpoint.http`, or create a dedicated `test_users_status.http`.

---

## Phase Checklist

- [ ] Phase 0 — Run `ALTER TABLE pick_location_users ADD is_active INT NOT NULL DEFAULT 1;`
- [ ] Phase 1 — Entity: add `isActive`
- [ ] Phase 2 — DTO: add `isActive`
- [ ] Phase 3 — Mapper: copy flag both directions
- [ ] Phase 4 — Repository: add `findByIsActive`
- [ ] Phase 5 — Service interface: add 3 methods
- [ ] Phase 6 — Service impl: implement + login 403 gate
- [ ] Phase 7 — Controller: add 3 endpoints
- [ ] Phase 8 — Compile + manual test sequence

---

## Out of Scope / Follow-up

- **Passwords are returned in plaintext** by every user endpoint (the entity→DTO mapper copies `password`). Pre-existing and unrelated to enable/disable, but worth fixing separately.
- **No authorization on the enable/disable endpoints.** The project has no Spring Security / JWT layer, so any reachable client can enable or disable any account.
- **The `role` column is a bare `int`** (`control-room = 1`, `handasah = 2`, `technical = 3`) with no enum or validation. Worth an enum if enable/disable permissions become role-based later.
