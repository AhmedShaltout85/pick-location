# Plan: Implement CRUD for `sectors` table

## Date: 2026-08-03

## Database Table

```sql
CREATE TABLE sectors(
    id BIGINT IDENTITY(1,1) PRIMARY KEY,
    sector_name NVARCHAR(255) NOT NULL
);
```

## Pattern Summary (from latest RiskStatus convention)

- **Entity** in `models/sector/` with Lombok `@Setter @Getter @AllArgsConstructor @NoArgsConstructor @ToString`
- **DTO** in `models/dto/` with Lombok `@Data @Setter @Getter @NoArgsConstructor @AllArgsConstructor`
- **Mapper Interface** in `models/mapper/` (hand-written, 2 methods: sectorDTOToSectorEntity, sectorEntityToSectorDTO)
- **Mapper Impl** in `models/mapper/` (manual field copying with null check)
- **Repository** in `repositores/` (extends `JpaRepository<SectorEntity, Long>`, `@Repository`)
- **Service Interface** in `services/` (methods return `ResponseEntity<...>`)
- **Service Impl** in `services/` (`@Service`, constructor injection, mapper as `static final`)
- **Controller** in `controllers/` (`@RestController`, `@RequestMapping`, `@CrossOrigin`, delegates to service)

---

## Files to Create (10 total)

| # | Layer | File | Path |
|---|---|---|---|
| 1 | Entity | `SectorEntity.java` | `models/sector/SectorEntity.java` |
| 2 | DTO | `SectorDTO.java` | `models/dto/SectorDTO.java` |
| 3 | Mapper Interface | `ISectorMapper.java` | `models/mapper/ISectorMapper.java` |
| 4 | Mapper Impl | `SectorMapperImpl.java` | `models/mapper/SectorMapperImpl.java` |
| 5 | Repository | `ISectorRepository.java` | `repositores/ISectorRepository.java` |
| 6 | Service Interface | `ISectorServices.java` | `services/ISectorServices.java` |
| 7 | Service Impl | `SectorServicesImpl.java` | `services/SectorServicesImpl.java` |
| 8 | Controller | `SectorController.java` | `controllers/SectorController.java` |
| 9 | Plan file | `add_sectors_table_3-08-2026.md` | root directory |
| 10 | Test HTTP | `sector_test_endpoint.http` | root directory |

---

## Fields

- `id` (Long) mapped to column `ID`
- `sectorName` (String) mapped to column `sector_name`

---

## Endpoints

| Method | Endpoint | Description |
|---|---|---|
| GET | `/api/v1/sectors/all` | Get all sectors |
| GET | `/api/v1/sectors/{id}` | Get sector by ID |
| POST | `/api/v1/sectors/create` | Create sector |
| PUT | `/api/v1/sectors/{id}` | Update sector |
| DELETE | `/api/v1/sectors/{id}` | Delete sector |

---

## Custom Repository Method

- `findBySectorName(String sectorName)` — consistent with other lookup tables
