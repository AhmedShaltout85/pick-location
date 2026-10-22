# Enable / Disable Users — Implementation Plan

**Date:** 22-10-2026
**Entity:** `pick_location_users` (`PickLocationUsersEntity`)

---

## Overview

Add an enable/disable capability for application users. A new `is_active` flag on `pick_location_users` records whether an account may be used. Administrators can enable or disable an account through dedicated endpoints, and disabled accounts are rejected at login with HTTP 403.

**Total files to modify: 7** (+ 1 manual DDL statement)

---

## Decisions

| Decision | Choice | Rationale |
|----------|--------|-----------|
| Storage | `int isActive` (1 = active, 0 = disabled) | Matches the existing `int` flag convention in `ComplaintEntity` (`isDeleted`, `isFinished`, `isTracked`) and the table's existing `int` columns (`role`, `technical_id`) |
| Endpoints | Separate `enable` + `disable` + list-by-status | Explicit and self-documenting for the Flutter client |
| Login gating | Disabled user receives HTTP 403 | A disabled account must not be usable |
| Audit columns | None | Keeps the change minimal, consistent with the project's simple schema style |

---

## Conventions (from existing codebase)

| Concern | Pattern followed |
|---------|------------------|
| Flag type | `int` with `0`/`1` semantics (`ComplaintEntity.isDeleted`) |
| Lombok accessors | For `int isActive`, Lombok generates `getIsActive()` / `setIsActive()` — identical to the existing `getIsDeleted()` / `setIsDeleted()` on `ComplaintEntity` |
| Mapper | Manual field-by-field copy in both directions; null input throws `NullPointerException("<param> should not be null")` |
| Mapper wiring | `private static final I<Name>Mapper I_<NAME>_MAPPER = new <Name>MapperImpl();` inside the service impl — not a Spring bean |
| Repository | `@Repository` extending `JpaRepository<Entity, Long>`; derived query methods only |
| Service | Interface `I<Name>Services` + impl `<Name>ServicesImpl`; all methods return `ResponseEntity<T>` |
| Not found | `throw new RecordNotFoundException("the item with id: " + id + " not found!...")` → HTTP 404 |
| Empty list | `throw new RecordNotFoundException("Sorry, No DATA Found!...")` → HTTP 404 |
| Controller | `@RestController`, `@RequestMapping(path = "/api/v1/users")`, `@CrossOrigin(origins = "*")`, constructor injection |
| Base URL | `http://localhost:9999/pick-location/api/v1/users` |

---

## Important Context

1. **No migration tooling exists.** `spring.jpa.hibernate.ddl-auto=none` and there are no Flyway/Liquibase/`.sql` files in the repository. The DDL in Phase 0 must be run manually in SQL Server, otherwise every query against `pick_location_users` will fail.
2. **No authentication/authorization layer.** There is no Spring Security, no JWT, no `SecurityContext`. Login is `GET /api/v1/users/{userName}/{password}` with a plain-text password comparison. The enable/disable endpoints are therefore callable by anyone who can reach the API — consistent with every other endpoint in this project.
3. **The global exception handler maps unknown exceptions to HTTP 400.** `errors/ExceptionHandlers.java` has a catch-all `@ExceptionHandler(Exception.class)` returning `HttpStatus.BAD_REQUEST`. A 403 for a disabled account must therefore be returned directly from the service (`new ResponseEntity<>(msg, HttpStatus.FORBIDDEN)`), not thrown — exactly as `changePassword` returns `HttpStatus.UNAUTHORIZED` directly.
4. **`spring.jackson.default-property-inclusion=non_null`** is set. `isActive` is a primitive `int` and is therefore never omitted from JSON responses.

---

## Phase 0: Database DDL (manual)

Run once against `pick_location` on `localhost:1433` (credentials `awco` / `awco`):

```sql
ALTER TABLE pick_location_users ADD is_active INT NOT NULL DEFAULT 1;
```

`DEFAULT 1` back-fills all existing rows as active, so no existing account is disabled by the migration.

---

## Phase 1: Entity

**File:** `src/main/java/com/a08r/pick_location/models/users/PickLocationUsersEntity.java`

Add after the `technicalId` field:

```java
@Column(name = "is_active", nullable = false)
private int isActive; // 1 = enabled, 0 = disabled
```

---

## Phase 2: DTO

**File:** `src/main/java/com/a08r/pick_location/models/dto/PickLocationUsersDTO.java`

Add field:

```java
private int isActive; // 1 = enabled, 0 = disabled
```

The field name is identical in the entity and the DTO, mirroring `ComplaintDTO` / `ComplaintEntity`.

---

## Phase 3: Mapper

**File:** `src/main/java/com/a08r/pick_location/models/mapper/PickLocationUsersMapperImpl.java`

Two additions, one per direction:

- `pickLocationUsersDTOToPickLocationUsersEntity(...)`:
  ```java
  pickLocationUsersEntity.setIsActive(pickLocationUsersDTO.getIsActive());
  ```
- `pickLocationUsersEntityToPickLocationUsersDTO(...)`:
  ```java
  pickLocationUsersDTO.setIsActive(pickLocationUsersEntity.getIsActive());
  ```

The mapper interface `IPickLocationUsersMapper` needs no change — its two method signatures are unchanged.

---

## Phase 4: Repository

**File:** `src/main/java/com/a08r/pick_location/repositores/IPickLocationUsersRepository.java`

Add derived query:

```java
List<PickLocationUsersEntity> findByIsActive(int isActive);
```

---

## Phase 5: Service Interface

**File:** `src/main/java/com/a08r/pick_location/services/IPickLocationUsersServices.java`

Add three methods:

```java
ResponseEntity<PickLocationUsersDTO> enableUser(Long id);

ResponseEntity<PickLocationUsersDTO> disableUser(Long id);

ResponseEntity<List<PickLocationUsersDTO>> getUsersByStatus(int isActive);
```

---

## Phase 6: Service Implementation

**File:** `src/main/java/com/a08r/pick_location/services/PickLocationUsersServicesImpl.java`

### 6.1 Enable / Disable

Both public methods delegate to a single private helper:

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
    List<PickLocationUsersEntity> entities = iPickLocationUsersRepository.findByIsActive(isActive);
    List<PickLocationUsersDTO> usersDTOList = entities
            .stream()
            .map(I_PICK_LOCATION_USERS_MAPPER::pickLocationUsersEntityToPickLocationUsersDTO)
            .collect(Collectors.toList());
    if (usersDTOList.isEmpty()) {
        throw new RecordNotFoundException("Sorry, No DATA Found!...");
    }
    return new ResponseEntity<>(usersDTOList, HttpStatus.OK);
}
```

### 6.3 Login gating (modifies existing method)

`findUserByUserNameAndPassword` currently calls `.get()` on a possibly-empty `Optional` — a failed login throws `NoSuchElementException`, which the global handler turns into HTTP 400. Replace it with an explicit empty check plus the disabled check:

```java
@Override
public ResponseEntity<PickLocationUsersDTO> findUserByUserNameAndPassword(String userName, String password) {
    Optional<PickLocationUsersEntity> pickLocationUsersEntity =
            iPickLocationUsersRepository.findByUserNameAndPassword(userName, password);
    if (pickLocationUsersEntity.isEmpty()) {
        throw new RecordNotFoundException("the user with UserName: " + userName + " not found!...");
    }
    PickLocationUsersEntity user = pickLocationUsersEntity.get();
    if (user.getIsActive() == 0) {
        return new ResponseEntity<>("the user with UserName: " + userName + " is disabled!...",
                HttpStatus.FORBIDDEN);
    }
    PickLocationUsersDTO pickLocationUsersDTO =
            I_PICK_LOCATION_USERS_MAPPER.pickLocationUsersEntityToPickLocationUsersDTO(user);
    return new ResponseEntity<>(pickLocationUsersDTO, HttpStatus.OK);
}
```

### 6.4 `updateUser` — flag is preserved

`updateUser` sets fields individually and does **not** touch `isActive`. A general profile update therefore cannot silently re-enable a disabled account; re-enabling requires the explicit enable endpoint.

---

## Phase 7: Controller

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

### Route collision note

- The `PUT` paths are 3 segments and the existing `PUT /id/{id}` is 2 segments — no conflict.
- The `GET /status/{isActive}` path has the same shape as the existing `GET /{userName}/{password}`. Spring MVC prefers a literal segment over a path variable, so `/status/1` resolves to the status endpoint. Side effect: a user whose username is literally `status` can no longer log in via that route.

---

## Endpoint Summary

| Method | Path | Description | Success |
|--------|------|-------------|---------|
| PUT | `/api/v1/users/id/{id}/enable` | Enable a user | 200 + user DTO |
| PUT | `/api/v1/users/id/{id}/disable` | Disable a user | 200 + user DTO |
| GET | `/api/v1/users/status/{isActive}` | List users by state (`0`/`1`) | 200 + list of user DTOs |
| GET | `/api/v1/users/{userName}/{password}` | Login — **now returns 403 if the account is disabled** | 200 / 403 / 404 |

**Status codes**

| Code | When |
|------|------|
| 200 | Success |
| 403 | Login attempted with a disabled account |
| 404 | User id does not exist, unknown username, or no users match the requested status |

---

## Verification

### Compile
```bash
./mvnw compile
```
Expected: `BUILD SUCCESS`

### Manual test sequence

1. `GET /api/v1/users/all` → each user now includes `"isActive": 1`
2. `PUT /api/v1/users/id/1/disable` → returns the user DTO with `"isActive": 0`
3. `GET /api/v1/users/{userName}/{password}` for user 1 → **HTTP 403** with the "is disabled" message
4. `GET /api/v1/users/status/0` → lists the disabled users
5. `GET /api/v1/users/status/1` → lists the active users
6. `PUT /api/v1/users/id/1/enable` → returns `"isActive": 1`
7. `GET /api/v1/users/{userName}/{password}` for user 1 → **HTTP 200** again
8. `PUT /api/v1/users/id/9999/disable` → HTTP 404
9. `GET /api/v1/users/status/0` with no disabled users → HTTP 404

### HTTP test file
Add the new requests to `test_new_endpoint.http` (or a dedicated `test_users_status.http`).

---

## File Checklist

### Database
- [ ] Run `ALTER TABLE pick_location_users ADD is_active INT NOT NULL DEFAULT 1;` manually

### Modified files (7)
- [ ] `src/main/java/com/a08r/pick_location/models/users/PickLocationUsersEntity.java` — add `isActive`
- [ ] `src/main/java/com/a08r/pick_location/models/dto/PickLocationUsersDTO.java` — add `isActive`
- [ ] `src/main/java/com/a08r/pick_location/models/mapper/PickLocationUsersMapperImpl.java` — copy field both directions
- [ ] `src/main/java/com/a08r/pick_location/repositores/IPickLocationUsersRepository.java` — add `findByIsActive`
- [ ] `src/main/java/com/a08r/pick_location/services/IPickLocationUsersServices.java` — add 3 methods
- [ ] `src/main/java/com/a08r/pick_location/services/PickLocationUsersServicesImpl.java` — implement + login gate
- [ ] `src/main/java/com/a08r/pick_location/controllers/PickLocationUsersController.java` — add 3 endpoints

### Unchanged but related
- [ ] `src/main/java/com/a08r/pick_location/models/mapper/IPickLocationUsersMapper.java` — signatures unchanged, no edit needed
- [ ] `src/main/java/com/a08r/pick_location/errors/RecordNotFoundException.java` — reused as-is
- [ ] `src/main/java/com/a08r/pick_location/errors/ExceptionHandlers.java` — no change needed (403 is returned, not thrown)

---

## Out of Scope / Follow-up

- **Passwords are returned in plaintext** by every user endpoint, because the entity→DTO mapper copies `password`. This is pre-existing and unrelated to enable/disable, but should be addressed separately (e.g. stop copying `password` into the response DTO, and hash stored passwords with BCrypt).
- **No authorization on the enable/disable endpoints.** Since the project has no authentication layer, any reachable client can enable or disable any account. Adding Spring Security with role checks (`role = 1` control-room) would be a separate, larger change.
- **The `role` column is currently a bare `int`** (`control-room = 1`, `handasah = 2`, `technical = 3`) with no enum or validation. If enable/disable permissions are later tied to roles, an enum would be worth introducing first.
