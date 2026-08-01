package com.a08r.pick_location.services;

import com.a08r.pick_location.errors.RecordNotFoundException;
import com.a08r.pick_location.models.dto.ComplaintTypeDTO;
import com.a08r.pick_location.models.complaint.ComplaintTypeEntity;
import com.a08r.pick_location.models.mapper.ComplaintTypeMapperImpl;
import com.a08r.pick_location.models.mapper.IComplaintTypeMapper;
import com.a08r.pick_location.repositores.IComplaintTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ComplaintTypeServicesImpl implements IComplaintTypeServices {

    private final IComplaintTypeRepository iComplaintTypeRepository;
    private static final IComplaintTypeMapper I_COMPLAINT_TYPE_MAPPER = new ComplaintTypeMapperImpl();

    @Autowired
    public ComplaintTypeServicesImpl(IComplaintTypeRepository iComplaintTypeRepository) {
        this.iComplaintTypeRepository = iComplaintTypeRepository;
    }

    @Override
    public ResponseEntity<List<ComplaintTypeDTO>> getAll() {
        List<ComplaintTypeEntity> complaintTypeEntities = iComplaintTypeRepository.findAll();
        List<ComplaintTypeDTO> complaintTypeDTOS = complaintTypeEntities
                .stream()
                .map(I_COMPLAINT_TYPE_MAPPER::complaintTypeEntityToComplaintTypeDTO)
                .collect(Collectors.toList());
        if (complaintTypeDTOS.isEmpty()) {
            throw new RecordNotFoundException("Sorry, No DATA Found!...");
        }
        return new ResponseEntity<>(complaintTypeDTOS, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ComplaintTypeDTO> findById(Long id) {
        Optional<ComplaintTypeEntity> complaintTypeEntity = iComplaintTypeRepository.findById(id);
        if (complaintTypeEntity.isEmpty()) {
            throw new RecordNotFoundException("the item with id: " + id + " not found!...");
        }
        ComplaintTypeDTO complaintTypeDTO = I_COMPLAINT_TYPE_MAPPER.complaintTypeEntityToComplaintTypeDTO(complaintTypeEntity.get());
        return new ResponseEntity<>(complaintTypeDTO, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ComplaintTypeDTO> create(ComplaintTypeDTO complaintTypeDTO) {
        final ComplaintTypeEntity complaintTypeEntity = I_COMPLAINT_TYPE_MAPPER.complaintTypeDTOToComplaintTypeEntity(complaintTypeDTO);
        final ComplaintTypeEntity createdEntity = this.iComplaintTypeRepository.save(complaintTypeEntity);
        ComplaintTypeDTO createdDTO = I_COMPLAINT_TYPE_MAPPER.complaintTypeEntityToComplaintTypeDTO(createdEntity);
        return new ResponseEntity<>(createdDTO, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<ComplaintTypeDTO> update(Long id, ComplaintTypeDTO complaintTypeDTO) {
        Optional<ComplaintTypeEntity> existingEntity = iComplaintTypeRepository.findById(id);
        if (existingEntity.isEmpty()) {
            throw new RecordNotFoundException("the item with id: " + id + " not found!...");
        }
        ComplaintTypeEntity complaintTypeEntity = existingEntity.get();
        complaintTypeEntity.setComplaintName(complaintTypeDTO.getComplaintName());
        ComplaintTypeEntity updatedEntity = this.iComplaintTypeRepository.save(complaintTypeEntity);
        ComplaintTypeDTO updatedDTO = I_COMPLAINT_TYPE_MAPPER.complaintTypeEntityToComplaintTypeDTO(updatedEntity);
        return new ResponseEntity<>(updatedDTO, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<String> remove(Long id) {
        if (!iComplaintTypeRepository.existsById(id)) {
            throw new RecordNotFoundException("the item with id: " + id + " not found!...");
        }
        iComplaintTypeRepository.deleteById(id);
        return new ResponseEntity<>("Deleted Successfully!...", HttpStatus.OK);
    }
}
