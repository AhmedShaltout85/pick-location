# CRUD Implementation Plan — 4 New Tables

**Date:** 01-08-2026  
**Tables:** `complaint_type`, `neighborhoods`, `repaire_status`, `approval_authorities`

---

## Overview

Implement full CRUD (Create, Read, Update, Delete) for 4 lookup/reference tables added to the database. Each table requires 6 files following the existing project patterns.

**Total files to create: 24**

---

## Conventions (from existing codebase)

| Layer | Naming Pattern | Annotations |
|-------|---------------|-------------|
| Entity | `<Name>Entity` | `@Entity`, `@Table`, `@Setter`, `@Getter`, `@AllArgsConstructor`, `@NoArgsConstructor`, `@ToString` |
| DTO | `<Name>DTO` | `@Setter`, `@Getter`, `@Data`, `@NoArgsConstructor`, `@AllArgsConstructor` |
| Mapper Interface | `I<Name>Mapper` | Interface with `DTOToEntity` / `EntityToDTO` methods |
| Mapper Impl | `<Name>MapperImpl` | Implements mapper interface, null-checks included |
| Repository | `I<Name>Repository` | `@Repository`, extends `JpaRepository<Entity, Long>` |
| Service Interface | `I<Name>Services` | Methods return `ResponseEntity<T>` |
| Service Impl | `<Name>ServicesImpl` | `@Service`, constructor injection, static mapper |
| Controller | `<Name>Controller` | `@RestController`, `@RequestMapping`, `@CrossOrigin(origins="*")` |

**ID strategy:** `@Id` + `@GeneratedValue(strategy = GenerationType.IDENTITY)` of type `Long`

**API path pattern:** `/api/v1/<resource-name>`

---

## Phase 1: Entities

Create 4 entity classes in new sub-packages under `models/`:

### 1.1 `models/complaint/ComplaintTypeEntity.java`
- Package: `com.a08r.pick_location.models.complaint`
- Table: `complaint_type`
- Fields:
  - `Long id` -> `@Column(name = "ID")`
  - `String complaintName` -> `@Column(name = "complaint_name")`

### 1.2 `models/neighborhood/NeighborhoodEntity.java`
- Package: `com.a08r.pick_location.models.neighborhood`
- Table: `neighborhoods`
- Fields:
  - `Long id` -> `@Column(name = "ID")`
  - `String neighborhoodName` -> `@Column(name = "neighborhood_name")`

### 1.3 `models/repair/RepaireStatusEntity.java`
- Package: `com.a08r.pick_location.models.repair`
- Table: `repaire_status`
- Fields:
  - `Long id` -> `@Column(name = "ID")`
  - `String repaireType` -> `@Column(name = "repaire_type")`

### 1.4 `models/approval/ApprovalAuthorityEntity.java`
- Package: `com.a08r.pick_location.models.approval`
- Table: `approval_authorities`
- Fields:
  - `Long id` -> `@Column(name = "ID")`
  - `String approvalName` -> `@Column(name = "approval_name")`

---

## Phase 2: DTOs

Create 4 DTO classes in `models/dto/`:

### 2.1 `models/dto/ComplaintTypeDTO.java`
- Fields: `Long id`, `String complaintName`

### 2.2 `models/dto/NeighborhoodDTO.java`
- Fields: `Long id`, `String neighborhoodName`

### 2.3 `models/dto/RepaireStatusDTO.java`
- Fields: `Long id`, `String repaireType`

### 2.4 `models/dto/ApprovalAuthorityDTO.java`
- Fields: `Long id`, `String approvalName`

---

## Phase 3: Mappers

Create 4 mapper interfaces + 4 implementations in `models/mapper/`:

### 3.1 ComplaintType
- `IComplaintTypeMapper.java` — interface
- `ComplaintTypeMapperImpl.java` — implementation

### 3.2 Neighborhood
- `INeighborhoodMapper.java` — interface
- `NeighborhoodMapperImpl.java` — implementation

### 3.3 RepaireStatus
- `IRepaireStatusMapper.java` — interface
- `RepaireStatusMapperImpl.java` — implementation

### 3.4 ApprovalAuthority
- `IApprovalAuthorityMapper.java` — interface
- `ApprovalAuthorityMapperImpl.java` — implementation

---

## Phase 4: Repositories

Create 4 repository interfaces in `repositores/` (keeping existing typo):

### 4.1 `IComplaintTypeRepository.java`
- Custom query: `Optional<ComplaintTypeEntity> findByComplaintName(String complaintName)`

### 4.2 `INeighborhoodRepository.java`
- Custom query: `Optional<NeighborhoodEntity> findByNeighborhoodName(String neighborhoodName)`

### 4.3 `IRepaireStatusRepository.java`
- Custom query: `Optional<RepaireStatusEntity> findByRepaireType(String repaireType)`

### 4.4 `IApprovalAuthorityRepository.java`
- Custom query: `Optional<ApprovalAuthorityEntity> findByApprovalName(String approvalName)`

---

## Phase 5: Services

Create 4 service interfaces + 4 implementations in `services/`:

### Methods for all services:
- `getAll()`, `findById(Long id)`, `create(DTO)`, `update(Long id, DTO)`, `remove(Long id)`

---

## Phase 6: Controllers

Create 4 controllers in `controllers/`:

| Controller | Base Path | Endpoints |
|-----------|-----------|-----------|
| `ComplaintTypeController` | `/api/v1/complaint-types` | `GET /all`, `GET /{id}`, `POST /create`, `PUT /{id}`, `DELETE /{id}` |
| `NeighborhoodController` | `/api/v1/neighborhoods` | `GET /all`, `GET /{id}`, `POST /create`, `PUT /{id}`, `DELETE /{id}` |
| `RepaireStatusController` | `/api/v1/repaire-status` | `GET /all`, `GET /{id}`, `POST /create`, `PUT /{id}`, `DELETE /{id}` |
| `ApprovalAuthorityController` | `/api/v1/approval-authorities` | `GET /all`, `GET /{id}`, `POST /create`, `PUT /{id}`, `DELETE /{id}` |

---

## File Checklist (24 files)

### Entities (4 files)
- [ ] `src/main/java/com/a08r/pick_location/models/complaint/ComplaintTypeEntity.java`
- [ ] `src/main/java/com/a08r/pick_location/models/neighborhood/NeighborhoodEntity.java`
- [ ] `src/main/java/com/a08r/pick_location/models/repair/RepaireStatusEntity.java`
- [ ] `src/main/java/com/a08r/pick_location/models/approval/ApprovalAuthorityEntity.java`

### DTOs (4 files)
- [ ] `src/main/java/com/a08r/pick_location/models/dto/ComplaintTypeDTO.java`
- [ ] `src/main/java/com/a08r/pick_location/models/dto/NeighborhoodDTO.java`
- [ ] `src/main/java/com/a08r/pick_location/models/dto/RepaireStatusDTO.java`
- [ ] `src/main/java/com/a08r/pick_location/models/dto/ApprovalAuthorityDTO.java`

### Mappers (8 files)
- [ ] `src/main/java/com/a08r/pick_location/models/mapper/IComplaintTypeMapper.java`
- [ ] `src/main/java/com/a08r/pick_location/models/mapper/ComplaintTypeMapperImpl.java`
- [ ] `src/main/java/com/a08r/pick_location/models/mapper/INeighborhoodMapper.java`
- [ ] `src/main/java/com/a08r/pick_location/models/mapper/NeighborhoodMapperImpl.java`
- [ ] `src/main/java/com/a08r/pick_location/models/mapper/IRepaireStatusMapper.java`
- [ ] `src/main/java/com/a08r/pick_location/models/mapper/RepaireStatusMapperImpl.java`
- [ ] `src/main/java/com/a08r/pick_location/models/mapper/IApprovalAuthorityMapper.java`
- [ ] `src/main/java/com/a08r/pick_location/models/mapper/ApprovalAuthorityMapperImpl.java`

### Repositories (4 files)
- [ ] `src/main/java/com/a08r/pick_location/repositores/IComplaintTypeRepository.java`
- [ ] `src/main/java/com/a08r/pick_location/repositores/INeighborhoodRepository.java`
- [ ] `src/main/java/com/a08r/pick_location/repositores/IRepaireStatusRepository.java`
- [ ] `src/main/java/com/a08r/pick_location/repositores/IApprovalAuthorityRepository.java`

### Services (8 files)
- [ ] `src/main/java/com/a08r/pick_location/services/IComplaintTypeServices.java`
- [ ] `src/main/java/com/a08r/pick_location/services/ComplaintTypeServicesImpl.java`
- [ ] `src/main/java/com/a08r/pick_location/services/INeighborhoodServices.java`
- [ ] `src/main/java/com/a08r/pick_location/services/NeighborhoodServicesImpl.java`
- [ ] `src/main/java/com/a08r/pick_location/services/IRepaireStatusServices.java`
- [ ] `src/main/java/com/a08r/pick_location/services/RepaireStatusServicesImpl.java`
- [ ] `src/main/java/com/a08r/pick_location/services/IApprovalAuthorityServices.java`
- [ ] `src/main/java/com/a08r/pick_location/services/ApprovalAuthorityServicesImpl.java`

### Controllers (4 files)
- [ ] `src/main/java/com/a08r/pick_location/controllers/ComplaintTypeController.java`
- [ ] `src/main/java/com/a08r/pick_location/controllers/NeighborhoodController.java`
- [ ] `src/main/java/com/a08r/pick_location/controllers/RepaireStatusController.java`
- [ ] `src/main/java/com/a08r/pick_location/controllers/ApprovalAuthorityController.java`
