package com.a08r.pick_location.services;

import com.a08r.pick_location.errors.RecordNotFoundException;
import com.a08r.pick_location.models.dto.ComplaintDTO;
import com.a08r.pick_location.models.complaints.ComplaintEntity;
import com.a08r.pick_location.models.mapper.IComplaintMapper;
import com.a08r.pick_location.models.mapper.ComplaintMapperImpl;
import com.a08r.pick_location.repositores.IComplaintRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ComplaintServicesImpl implements IComplaintServices {

    private final IComplaintRepository iComplaintRepository;
    private static final IComplaintMapper I_COMPLAINT_MAPPER = new ComplaintMapperImpl();

    @Autowired
    public ComplaintServicesImpl(IComplaintRepository iComplaintRepository) {
        this.iComplaintRepository = iComplaintRepository;
    }

    @Override
    public ResponseEntity<List<ComplaintDTO>> getAll() {
        List<ComplaintEntity> complaintEntities = iComplaintRepository.findAll();
        List<ComplaintDTO> complaintDTOS = complaintEntities
                .stream()
                .map(I_COMPLAINT_MAPPER::complaintEntityToComplaintDTO)
                .collect(Collectors.toList());
        if (complaintDTOS.isEmpty()) {
            throw new RecordNotFoundException("Sorry, No DATA Found!...");
        }
        return new ResponseEntity<>(complaintDTOS, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ComplaintDTO> findById(Long id) {
        Optional<ComplaintEntity> complaintEntity = iComplaintRepository.findById(id);
        if (complaintEntity.isEmpty()) {
            throw new RecordNotFoundException("the item with id: " + id + " not found!...");
        }
        ComplaintDTO complaintDTO = I_COMPLAINT_MAPPER.complaintEntityToComplaintDTO(complaintEntity.get());
        return new ResponseEntity<>(complaintDTO, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<ComplaintDTO>> findByNeighborhood(String neighborhood) {
        List<ComplaintEntity> complaintEntities = iComplaintRepository.findByNeighborhood(neighborhood);
        List<ComplaintDTO> complaintDTOS = complaintEntities
                .stream()
                .map(I_COMPLAINT_MAPPER::complaintEntityToComplaintDTO)
                .collect(Collectors.toList());
        if (complaintDTOS.isEmpty()) {
            throw new RecordNotFoundException("Sorry, No DATA Found for neighborhood: " + neighborhood + "!...");
        }
        return new ResponseEntity<>(complaintDTOS, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<ComplaintDTO>> findByComplaintStatus(String complaintStatus) {
        List<ComplaintEntity> complaintEntities = iComplaintRepository.findByComplaintStatus(complaintStatus);
        List<ComplaintDTO> complaintDTOS = complaintEntities
                .stream()
                .map(I_COMPLAINT_MAPPER::complaintEntityToComplaintDTO)
                .collect(Collectors.toList());
        if (complaintDTOS.isEmpty()) {
            throw new RecordNotFoundException("Sorry, No DATA Found for status: " + complaintStatus + "!...");
        }
        return new ResponseEntity<>(complaintDTOS, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<ComplaintDTO>> findByCurrentUsername(String currentUsername) {
        List<ComplaintEntity> complaintEntities = iComplaintRepository.findByCurrentUsername(currentUsername);
        List<ComplaintDTO> complaintDTOS = complaintEntities
                .stream()
                .map(I_COMPLAINT_MAPPER::complaintEntityToComplaintDTO)
                .collect(Collectors.toList());
        if (complaintDTOS.isEmpty()) {
            throw new RecordNotFoundException("Sorry, No DATA Found for user: " + currentUsername + "!...");
        }
        return new ResponseEntity<>(complaintDTOS, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ComplaintDTO> create(ComplaintDTO complaintDTO) {
        final ComplaintEntity complaintEntity = I_COMPLAINT_MAPPER.complaintDTOToComplaintEntity(complaintDTO);
        final ComplaintEntity createdEntity = this.iComplaintRepository.save(complaintEntity);
        ComplaintDTO createdDTO = I_COMPLAINT_MAPPER.complaintEntityToComplaintDTO(createdEntity);
        return new ResponseEntity<>(createdDTO, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<ComplaintDTO> update(Long id, ComplaintDTO complaintDTO) {
        Optional<ComplaintEntity> existingEntity = iComplaintRepository.findById(id);
        if (existingEntity.isEmpty()) {
            throw new RecordNotFoundException("the item with id: " + id + " not found!...");
        }
        ComplaintEntity complaintEntity = existingEntity.get();
        complaintEntity.setNeighborhood(complaintDTO.getNeighborhood());
        complaintEntity.setRepeatComplaintNumber(complaintDTO.getRepeatComplaintNumber() != null ? complaintDTO.getRepeatComplaintNumber() : 0L);
        complaintEntity.setComplaintSource(complaintDTO.getComplaintSource());
        complaintEntity.setReporterName(complaintDTO.getReporterName());
        complaintEntity.setReporterPhone(complaintDTO.getReporterPhone());
        complaintEntity.setComplaintAddress(complaintDTO.getComplaintAddress());
        complaintEntity.setComplaintRepairStatus(complaintDTO.getComplaintRepairStatus());
        complaintEntity.setApprovalAuthority(complaintDTO.getApprovalAuthority());
        complaintEntity.setPumpDiameter(complaintDTO.getPumpDiameter());
        complaintEntity.setSeriousStatus(complaintDTO.getSeriousStatus());
        complaintEntity.setComplaintStatus(complaintDTO.getComplaintStatus());
        complaintEntity.setComplaintNote(complaintDTO.getComplaintNote());
        complaintEntity.setRecipientDestination(complaintDTO.getRecipientDestination());
        complaintEntity.setRecipientUser(complaintDTO.getRecipientUser());
        complaintEntity.setReportNumber(complaintDTO.getReportNumber() != null ? complaintDTO.getReportNumber() : 0L);
        complaintEntity.setRecipientName(complaintDTO.getRecipientName());
        complaintEntity.setGisLink(complaintDTO.getGisLink());
        complaintEntity.setLongitude(complaintDTO.getLongitude());
        complaintEntity.setLatitude(complaintDTO.getLatitude());
        complaintEntity.setIsDeleted(complaintDTO.getIsDeleted());
        complaintEntity.setIsFinished(complaintDTO.getIsFinished());
        complaintEntity.setIsTracked(complaintDTO.getIsTracked());
        complaintEntity.setCurrentUsername(complaintDTO.getCurrentUsername());
        complaintEntity.setDeletedAt(complaintDTO.getDeletedAt());
        complaintEntity.setFinishedAt(complaintDTO.getFinishedAt());
        complaintEntity.setUpdatedAt(java.time.LocalDateTime.now().toString());
        ComplaintEntity updatedEntity = this.iComplaintRepository.save(complaintEntity);
        ComplaintDTO updatedDTO = I_COMPLAINT_MAPPER.complaintEntityToComplaintDTO(updatedEntity);
        return new ResponseEntity<>(updatedDTO, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ComplaintDTO> updateRepeatComplaintNumber(Long id, ComplaintDTO complaintDTO) {
        Optional<ComplaintEntity> existingEntity = iComplaintRepository.findById(id);
        if (existingEntity.isEmpty()) {
            throw new RecordNotFoundException("the item with id: " + id + " not found!...");
        }
        ComplaintEntity complaintEntity = existingEntity.get();
        complaintEntity.setRepeatComplaintNumber(
                complaintDTO.getRepeatComplaintNumber() != null ? complaintDTO.getRepeatComplaintNumber() : 0L);
        complaintEntity.setUpdatedAt(java.time.LocalDateTime.now().toString());
        ComplaintEntity updatedEntity = this.iComplaintRepository.save(complaintEntity);
        ComplaintDTO updatedDTO = I_COMPLAINT_MAPPER.complaintEntityToComplaintDTO(updatedEntity);
        return new ResponseEntity<>(updatedDTO, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ComplaintDTO> updateRecipient(Long id, ComplaintDTO complaintDTO) {
        Optional<ComplaintEntity> existingEntity = iComplaintRepository.findById(id);
        if (existingEntity.isEmpty()) {
            throw new RecordNotFoundException("the item with id: " + id + " not found!...");
        }
        ComplaintEntity complaintEntity = existingEntity.get();
        complaintEntity.setRecipientDestination(complaintDTO.getRecipientDestination());
        complaintEntity.setRecipientUser(complaintDTO.getRecipientUser());
        complaintEntity.setUpdatedAt(java.time.LocalDateTime.now().toString());
        ComplaintEntity updatedEntity = this.iComplaintRepository.save(complaintEntity);
        ComplaintDTO updatedDTO = I_COMPLAINT_MAPPER.complaintEntityToComplaintDTO(updatedEntity);
        return new ResponseEntity<>(updatedDTO, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ComplaintDTO> finish(Long id) {
        Optional<ComplaintEntity> existingEntity = iComplaintRepository.findById(id);
        if (existingEntity.isEmpty()) {
            throw new RecordNotFoundException("the item with id: " + id + " not found!...");
        }
        ComplaintEntity complaintEntity = existingEntity.get();
        complaintEntity.setIsFinished(1);
        complaintEntity.setFinishedAt(java.time.LocalDateTime.now().toString());
        complaintEntity.setUpdatedAt(java.time.LocalDateTime.now().toString());
        ComplaintEntity updatedEntity = this.iComplaintRepository.save(complaintEntity);
        ComplaintDTO updatedDTO = I_COMPLAINT_MAPPER.complaintEntityToComplaintDTO(updatedEntity);
        return new ResponseEntity<>(updatedDTO, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ComplaintDTO> updateTracked(Long id, ComplaintDTO complaintDTO) {
        Optional<ComplaintEntity> existingEntity = iComplaintRepository.findById(id);
        if (existingEntity.isEmpty()) {
            throw new RecordNotFoundException("the item with id: " + id + " not found!...");
        }
        ComplaintEntity complaintEntity = existingEntity.get();
        complaintEntity.setIsTracked(complaintDTO.getIsTracked());
        complaintEntity.setUpdatedAt(java.time.LocalDateTime.now().toString());
        ComplaintEntity updatedEntity = this.iComplaintRepository.save(complaintEntity);
        ComplaintDTO updatedDTO = I_COMPLAINT_MAPPER.complaintEntityToComplaintDTO(updatedEntity);
        return new ResponseEntity<>(updatedDTO, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ComplaintDTO> softDelete(Long id) {
        Optional<ComplaintEntity> existingEntity = iComplaintRepository.findById(id);
        if (existingEntity.isEmpty()) {
            throw new RecordNotFoundException("the item with id: " + id + " not found!...");
        }
        ComplaintEntity complaintEntity = existingEntity.get();
        complaintEntity.setIsDeleted(1);
        complaintEntity.setDeletedAt(java.time.LocalDateTime.now().toString());
        complaintEntity.setUpdatedAt(java.time.LocalDateTime.now().toString());
        ComplaintEntity updatedEntity = this.iComplaintRepository.save(complaintEntity);
        ComplaintDTO updatedDTO = I_COMPLAINT_MAPPER.complaintEntityToComplaintDTO(updatedEntity);
        return new ResponseEntity<>(updatedDTO, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<String> remove(Long id) {
        if (!iComplaintRepository.existsById(id)) {
            throw new RecordNotFoundException("the item with id: " + id + " not found!...");
        }
        iComplaintRepository.deleteById(id);
        return new ResponseEntity<>("Deleted Successfully!...", HttpStatus.OK);
    }
}
