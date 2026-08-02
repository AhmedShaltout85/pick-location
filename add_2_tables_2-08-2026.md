# Plan: Implement CRUD for `important_status` and `risk_status`

## Date: 2026-08-02

## Database Tables

```sql
-- important-status درجة الاهمية
CREATE TABLE important_status(
    id BIGINT IDENTITY(1,1) PRIMARY KEY,
    important_name NVARCHAR(255) NOT NULL
);

-- risk-status درجة الخطورة
CREATE TABLE risk_status(
    id BIGINT IDENTITY(1,1) PRIMARY KEY,
    risk_name NVARCHAR(255) NOT NULL
);
```

## Pattern Summary (from existing code)

- **Entity** in `models/<domain>/` with Lombok `@Setter @Getter @AllArgsConstructor @NoArgsConstructor @ToString`
- **DTO** in `models/dto/` with Lombok `@Data @Setter @Getter @NoArgsConstructor @AllArgsConstructor`
- **Mapper Interface** in `models/mapper/` (hand-written, 2 methods: entityToDTO, DTOToEntity)
- **Mapper Impl** in `models/mapper/` (manual field copying with null check)
- **Repository** in `repositores/` (extends `JpaRepository<Entity, Long>`, `@Repository`)
- **Service Interface** in `services/` (methods return `ResponseEntity<...>`)
- **Service Impl** in `services/` (`@Service`, constructor injection, mapper as `static final`)
- **Controller** in `controllers/` (`@RestController`, `@RequestMapping`, `@CrossOrigin`, delegates to service)

---

## Phase 1: Important Status (8 files)

| # | File | Path |
|---|---|---|
| 1 | Entity | `models/important/ImportantStatusEntity.java` |
| 2 | DTO | `models/dto/ImportantStatusDTO.java` |
| 3 | Mapper Interface | `models/mapper/IImportantStatusMapper.java` |
| 4 | Mapper Impl | `models/mapper/ImportantStatusMapperImpl.java` |
| 5 | Repository | `repositores/IImportantStatusRepository.java` |
| 6 | Service Interface | `services/IImportantStatusServices.java` |
| 7 | Service Impl | `services/ImportantStatusServicesImpl.java` |
| 8 | Controller | `controllers/ImportantStatusController.java` |

**Fields:** `id` (Long), `importantName` (String) mapped to column `important_name`
**Table name:** `important_status`
**Base URL:** `/api/v1/important-status`

---

## Phase 2: Risk Status (8 files)

| # | File | Path |
|---|---|---|
| 1 | Entity | `models/risk/RiskStatusEntity.java` |
| 2 | DTO | `models/dto/RiskStatusDTO.java` |
| 3 | Mapper Interface | `models/mapper/IRiskStatusMapper.java` |
| 4 | Mapper Impl | `models/mapper/RiskStatusMapperImpl.java` |
| 5 | Repository | `repositores/IRiskStatusRepository.java` |
| 6 | Service Interface | `services/IRiskStatusServices.java` |
| 7 | Service Impl | `services/RiskStatusServicesImpl.java` |
| 8 | Controller | `controllers/RiskStatusController.java` |

**Fields:** `id` (Long), `riskName` (String) mapped to column `risk_name`
**Table name:** `risk_status`
**Base URL:** `/api/v1/risk-status`

---

## Endpoints for each (identical CRUD pattern)

| Method | Endpoint | Description |
|---|---|---|
| GET | `/all` | Get all records |
| GET | `/{id}` | Get by ID |
| POST | `/create` | Create new record |
| PUT | `/{id}` | Update record |
| DELETE | `/{id}` | Delete record |

---

## Total: 16 new files
