# Plan: Implement CRUD for `complaints` table

## Date: 2026-08-04 (Updated: 2026-08-15)

## Database Table

```sql
CREATE TABLE complaints (
    complaint_id            BIGINT IDENTITY(1,1) PRIMARY KEY,
    created_at              DATETIME2 NOT NULL DEFAULT SYSDATETIME(),
    updated_at              DATETIME2 NOT NULL DEFAULT SYSDATETIME(),
    neighborhood            NVARCHAR(255) NOT NULL DEFAULT N'لم يدرج',
    repeat_complaint_number BIGINT NOT NULL DEFAULT 0,
    complaint_source        NVARCHAR(255) NOT NULL DEFAULT N'لم يدرج',
    reporter_name           NVARCHAR(255) NOT NULL DEFAULT N'لم يدرج',
    reporter_phone          NVARCHAR(255) NOT NULL DEFAULT N'لم يدرج',
    complaint_address       NVARCHAR(255) NOT NULL,
    complaint_repair_status NVARCHAR(255) NOT NULL DEFAULT N'لم يدرج',
    approval_authority      NVARCHAR(255) NOT NULL DEFAULT N'لم يدرج',
    pump_diameter           NVARCHAR(255) NOT NULL DEFAULT N'لم يدرج',
    serious_status          NVARCHAR(255) NOT NULL DEFAULT N'لم يدرج',
    complaint_status        NVARCHAR(255) NOT NULL DEFAULT N'لم يدرج',
    complaint_note          NVARCHAR(255) NOT NULL DEFAULT N'لم يدرج',
    recipient_destination   NVARCHAR(255) NOT NULL DEFAULT N'لم يدرج',
    recipient_user          NVARCHAR(255) NOT NULL DEFAULT N'لم يدرج',
    report_number           BIGINT NOT NULL DEFAULT 0,
    recipient_name          NVARCHAR(255) NOT NULL DEFAULT N'لم يدرج',
    gis_link                NVARCHAR(255) NOT NULL DEFAULT N'لم يدرج',
    longitude               NVARCHAR(255) NOT NULL DEFAULT N'لم يدرج',
    latitude                NVARCHAR(255) NOT NULL DEFAULT N'لم يدرج',
    is_deleted              BIT NOT NULL DEFAULT 0,
    is_finished             BIT NOT NULL DEFAULT 0,
    is_tracked              BIT NOT NULL DEFAULT 0,
    current_username        NVARCHAR(255) NOT NULL,
    deleted_at              DATETIME2 NULL,
    finished_at             DATETIME2 NULL,
    complaint_type          NVARCHAR(255) NOT NULL DEFAULT N'لم يدرج',
    sector_name             NVARCHAR(255) NOT NULL DEFAULT N'لم يدرج',
    urgency_number          BIGINT NOT NULL DEFAULT 0
);
```

## Pattern Summary

- **Entity** in `models/complaints/` with Lombok `@Setter @Getter @AllArgsConstructor @NoArgsConstructor @ToString`
- **DTO** in `models/dto/` with Lombok `@Data @Setter @Getter @NoArgsConstructor @AllArgsConstructor`
- **Mapper Interface** in `models/mapper/` (hand-written, 2 methods: dtoToEntity, entityToDto)
- **Mapper Impl** in `models/mapper/` (manual field copying with null check + defaults)
- **Repository** in `repositores/` (extends `JpaRepository<ComplaintEntity, Long>`, `@Repository`)
- **Service Interface** in `services/` (methods return `ResponseEntity<...>`)
- **Service Impl** in `services/` (`@Service`, constructor injection, mapper as `static final`)
- **Controller** in `controllers/` (`@RestController`, `@RequestMapping`, `@CrossOrigin`, delegates to service)

---

## Field Mapping (30 fields)

| DB Column | Java Field | Java Type | Default |
|---|---|---|---|
| `complaint_id` | `complaintId` | `Long` | auto-increment |
| `created_at` | `createdAt` | `String` | `LocalDateTime.now()` in mapper |
| `updated_at` | `updatedAt` | `String` | `LocalDateTime.now()` in mapper |
| `neighborhood` | `neighborhood` | `String` | `"لم يدرج"` |
| `repeat_complaint_number` | `repeatComplaintNumber` | `Long` | `0` |
| `complaint_source` | `complaintSource` | `String` | `"لم يدرج"` |
| `reporter_name` | `reporterName` | `String` | `"لم يدرج"` |
| `reporter_phone` | `reporterPhone` | `String` | `"لم يدرج"` |
| `complaint_address` | `complaintAddress` | `String` | **required** |
| `complaint_repair_status` | `complaintRepairStatus` | `String` | `"لم يدرج"` |
| `approval_authority` | `approvalAuthority` | `String` | `"لم يدرج"` |
| `pump_diameter` | `pumpDiameter` | `String` | `"لم يدرج"` |
| `serious_status` | `seriousStatus` | `String` | `"لم يدرج"` |
| `complaint_status` | `complaintStatus` | `String` | `"لم يدرج"` |
| `complaint_note` | `complaintNote` | `String` | `"لم يدرج"` |
| `recipient_destination` | `recipientDestination` | `String` | `"لم يدرج"` |
| `recipient_user` | `recipientUser` | `String` | `"لم يدرج"` |
| `report_number` | `reportNumber` | `Long` | `0` |
| `recipient_name` | `recipientName` | `String` | `"لم يدرج"` |
| `gis_link` | `gisLink` | `String` | `"لم يدرج"` |
| `longitude` | `longitude` | `String` | `"لم يدرج"` |
| `latitude` | `latitude` | `String` | `"لم يدرج"` |
| `is_deleted` | `isDeleted` | `Integer` | `0` |
| `is_finished` | `isFinished` | `Integer` | `0` |
| `is_tracked` | `isTracked` | `int` | `0` |
| `current_username` | `currentUsername` | `String` | **required** |
| `deleted_at` | `deletedAt` | `String` | `null` |
| `finished_at` | `finishedAt` | `String` | `null` |
| `complaint_type` | `complaintType` | `String` | `"لم يدرج"` |
| `sector_name` | `sectorName` | `String` | `"لم يدرج"` |
| `urgency_number` | `urgencyNumber` | `Long` | `0` |

---

## Files to Create (10 total)

| # | Layer | File | Path |
|---|---|---|---|
| 1 | Entity | `ComplaintEntity.java` | `models/complaints/ComplaintEntity.java` |
| 2 | DTO | `ComplaintDTO.java` | `models/dto/ComplaintDTO.java` |
| 3 | Mapper Interface | `IComplaintMapper.java` | `models/mapper/IComplaintMapper.java` |
| 4 | Mapper Impl | `ComplaintMapperImpl.java` | `models/mapper/ComplaintMapperImpl.java` |
| 5 | Repository | `IComplaintRepository.java` | `repositores/IComplaintRepository.java` |
| 6 | Service Interface | `IComplaintServices.java` | `services/IComplaintServices.java` |
| 7 | Service Impl | `ComplaintServicesImpl.java` | `services/ComplaintServicesImpl.java` |
| 8 | Controller | `ComplaintController.java` | `controllers/ComplaintController.java` |
| 9 | Plan | `complaints_table_4-08-2026.md` | root directory |
| 10 | Test HTTP | `complaints_test_endpoint.http` | root directory |

---

## Endpoints (21 total)

| Method | Endpoint | Description |
|---|---|---|
| GET | `/api/v1/complaints/all` | Get all complaints |
| GET | `/api/v1/complaints/{id}` | Get complaint by ID |
| GET | `/api/v1/complaints/neighborhood/{neighborhood}` | Filter by neighborhood |
| GET | `/api/v1/complaints/status/{complaintStatus}` | Filter by complaint status |
| GET | `/api/v1/complaints/user/{currentUsername}` | Filter by current user |
| GET | `/api/v1/complaints/finished/{isFinished}` | Filter by isFinished (0=not finished, 1=finished) |
| GET | `/api/v1/complaints/complaint-type/{complaintType}` | Filter by complaint type |
| GET | `/api/v1/complaints/sector-name/{sectorName}` | Filter by sector name |
| POST | `/api/v1/complaints/create` | Create complaint |
| PUT | `/api/v1/complaints/{id}` | Update complaint (full) |
| PUT | `/api/v1/complaints/{id}/repeat-complaint-number` | Update repeat complaint number only |
| PUT | `/api/v1/complaints/{id}/urgency-number` | Update urgency number only |
| PUT | `/api/v1/complaints/{id}/recipient` | Update recipient destination and user only |
| PUT | `/api/v1/complaints/{id}/tracked` | Update tracked status only |
| PUT | `/api/v1/complaints/{id}/status-flags` | Update isDeleted and isFinished flags |
| PUT | `/api/v1/complaints/{id}/approval-authority` | Update approval authority only |
| PUT | `/api/v1/complaints/{id}/recipient-destination` | Update recipient destination only |
| PUT | `/api/v1/complaints/{id}/finish` | Mark as finished |
| PUT | `/api/v1/complaints/{id}/delete` | Soft-delete complaint |
| DELETE | `/api/v1/complaints/{id}` | Hard-delete complaint |

---

## Mapper Defaults (applied in dtoToEntity)

- `createdAt` / `updatedAt`: `LocalDateTime.now().toString()` if null/blank
- All string fields with DB default `"لم يدرج"`: default to `"لم يدرج"` if null/blank
- `repeatComplaintNumber`, `reportNumber`: default to `0` if null
- `urgencyNumber`: default to `0` if null
- `isDeleted`, `isFinished`, `isTracked`: default to `0` if null

---

## Changes Log

### 2026-08-04
- Initial implementation of complaints table CRUD

### 2026-08-05
- Added dedicated PUT endpoints for partial updates:
  - `PUT /{id}/repeat-complaint-number` — updates `repeatComplaintNumber` only
  - `PUT /{id}/recipient` — updates `recipientDestination` and `recipientUser` only
  - `PUT /{id}/finish` — sets `isFinished=1` and `finishedAt`
- Renamed `repeat_complaint_num` → `repeat_complaint_number` (DB column)
- Renamed `current_user` → `current_username` (MSSQL reserved word)
- Added null-safe defaults for `repeatComplaintNumber` and `reportNumber` in mapper

### 2026-08-11
- Added new column `is_tracked BIT NULL DEFAULT 0`
- Added `isTracked` field to Entity (`Integer`), DTO (`Integer`), and Mapper
- Added `PUT /{id}/tracked` endpoint for updating tracked status
- Changed `isTracked` to `Integer` (wrapper) to handle NULL values from existing records

### 2026-08-11 (Updated)
- Changed `is_tracked` column from `BIT NULL DEFAULT 0` to `BIT NOT NULL DEFAULT 0`
- Changed `isTracked` type from `Integer` (wrapper) to `int` (primitive) in Entity and DTO

### 2026-08-15
- Added `GET /api/v1/complaints/finished/{isFinished}` endpoint to filter complaints by isFinished status
- Service method: `findByIsFinished(int isFinished)` returns complaints where isFinished matches (0=not finished, 1=finished)
- Uses existing repository method `findByIsFinished(int isFinished)`

### 2026-08-15 (Updated)
- Added 2 new columns: `complaint_type NVARCHAR(255) NOT NULL DEFAULT N'لم يدرج'` and `sector_name NVARCHAR(255) NOT NULL DEFAULT N'لم يدرج'`
- Added `complaintType` and `sectorName` fields to Entity, DTO, and Mapper
- Added `findByComplaintType(String)` and `findBySectorName(String)` to Repository
- Added `GET /api/v1/complaints/complaint-type/{complaintType}` endpoint
- Added `GET /api/v1/complaints/sector-name/{sectorName}` endpoint
- Total fields: 29, Total endpoints: 17

### 2026-08-15 (Updated)
- Added new column `urgency_number BIGINT NOT NULL DEFAULT 0`
- Added `urgencyNumber` field (`Long`) to Entity, DTO, and Mapper
- Added `PUT /api/v1/complaints/{id}/urgency-number` endpoint for updating urgency number
- Added `urgencyNumber` to `update()` full update method
- Total fields: 30, Total endpoints: 18

### 2026-08-15 (Updated)
- Added `PUT /api/v1/complaints/{id}/status-flags` endpoint to update both `isDeleted` and `isFinished` in a single request
- Automatically sets `deletedAt` when `isDeleted` changes to 1
- Automatically sets `finishedAt` when `isFinished` changes to 1
- Request body: `{ "isDeleted": 1, "isFinished": 1 }`
- Total fields: 30, Total endpoints: 19

### 2026-08-15 (Updated)
- Added `PUT /api/v1/complaints/{id}/approval-authority` endpoint for updating approval authority only
- Request body: `{ "approvalAuthority": "إدارة الهندسة" }`
- Total fields: 30, Total endpoints: 20

### 2026-08-15 (Updated)
- Added `PUT /api/v1/complaints/{id}/recipient-destination` endpoint for updating recipient destination only
- Request body: `{ "recipientDestination": "قسم الصيانة" }`
- Total fields: 30, Total endpoints: 21
