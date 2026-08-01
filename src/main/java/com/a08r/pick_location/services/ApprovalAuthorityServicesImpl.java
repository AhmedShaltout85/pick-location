package com.a08r.pick_location.services;

import com.a08r.pick_location.errors.RecordNotFoundException;
import com.a08r.pick_location.models.dto.ApprovalAuthorityDTO;
import com.a08r.pick_location.models.approval.ApprovalAuthorityEntity;
import com.a08r.pick_location.models.mapper.ApprovalAuthorityMapperImpl;
import com.a08r.pick_location.models.mapper.IApprovalAuthorityMapper;
import com.a08r.pick_location.repositores.IApprovalAuthorityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ApprovalAuthorityServicesImpl implements IApprovalAuthorityServices {

    private final IApprovalAuthorityRepository iApprovalAuthorityRepository;
    private static final IApprovalAuthorityMapper I_APPROVAL_AUTHORITY_MAPPER = new ApprovalAuthorityMapperImpl();

    @Autowired
    public ApprovalAuthorityServicesImpl(IApprovalAuthorityRepository iApprovalAuthorityRepository) {
        this.iApprovalAuthorityRepository = iApprovalAuthorityRepository;
    }

    @Override
    public ResponseEntity<List<ApprovalAuthorityDTO>> getAll() {
        List<ApprovalAuthorityEntity> approvalAuthorityEntities = iApprovalAuthorityRepository.findAll();
        List<ApprovalAuthorityDTO> approvalAuthorityDTOS = approvalAuthorityEntities
                .stream()
                .map(I_APPROVAL_AUTHORITY_MAPPER::approvalAuthorityEntityToApprovalAuthorityDTO)
                .collect(Collectors.toList());
        if (approvalAuthorityDTOS.isEmpty()) {
            throw new RecordNotFoundException("Sorry, No DATA Found!...");
        }
        return new ResponseEntity<>(approvalAuthorityDTOS, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ApprovalAuthorityDTO> findById(Long id) {
        Optional<ApprovalAuthorityEntity> approvalAuthorityEntity = iApprovalAuthorityRepository.findById(id);
        if (approvalAuthorityEntity.isEmpty()) {
            throw new RecordNotFoundException("the item with id: " + id + " not found!...");
        }
        ApprovalAuthorityDTO approvalAuthorityDTO = I_APPROVAL_AUTHORITY_MAPPER.approvalAuthorityEntityToApprovalAuthorityDTO(approvalAuthorityEntity.get());
        return new ResponseEntity<>(approvalAuthorityDTO, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ApprovalAuthorityDTO> create(ApprovalAuthorityDTO approvalAuthorityDTO) {
        final ApprovalAuthorityEntity approvalAuthorityEntity = I_APPROVAL_AUTHORITY_MAPPER.approvalAuthorityDTOToApprovalAuthorityEntity(approvalAuthorityDTO);
        final ApprovalAuthorityEntity createdEntity = this.iApprovalAuthorityRepository.save(approvalAuthorityEntity);
        ApprovalAuthorityDTO createdDTO = I_APPROVAL_AUTHORITY_MAPPER.approvalAuthorityEntityToApprovalAuthorityDTO(createdEntity);
        return new ResponseEntity<>(createdDTO, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<ApprovalAuthorityDTO> update(Long id, ApprovalAuthorityDTO approvalAuthorityDTO) {
        Optional<ApprovalAuthorityEntity> existingEntity = iApprovalAuthorityRepository.findById(id);
        if (existingEntity.isEmpty()) {
            throw new RecordNotFoundException("the item with id: " + id + " not found!...");
        }
        ApprovalAuthorityEntity approvalAuthorityEntity = existingEntity.get();
        approvalAuthorityEntity.setApprovalName(approvalAuthorityDTO.getApprovalName());
        ApprovalAuthorityEntity updatedEntity = this.iApprovalAuthorityRepository.save(approvalAuthorityEntity);
        ApprovalAuthorityDTO updatedDTO = I_APPROVAL_AUTHORITY_MAPPER.approvalAuthorityEntityToApprovalAuthorityDTO(updatedEntity);
        return new ResponseEntity<>(updatedDTO, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<String> remove(Long id) {
        if (!iApprovalAuthorityRepository.existsById(id)) {
            throw new RecordNotFoundException("the item with id: " + id + " not found!...");
        }
        iApprovalAuthorityRepository.deleteById(id);
        return new ResponseEntity<>("Deleted Successfully!...", HttpStatus.OK);
    }
}
