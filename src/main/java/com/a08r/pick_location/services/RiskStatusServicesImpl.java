package com.a08r.pick_location.services;

import com.a08r.pick_location.errors.RecordNotFoundException;
import com.a08r.pick_location.models.dto.RiskStatusDTO;
import com.a08r.pick_location.models.risk.RiskStatusEntity;
import com.a08r.pick_location.models.mapper.IRiskStatusMapper;
import com.a08r.pick_location.models.mapper.RiskStatusMapperImpl;
import com.a08r.pick_location.repositores.IRiskStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RiskStatusServicesImpl implements IRiskStatusServices {

    private final IRiskStatusRepository iRiskStatusRepository;
    private static final IRiskStatusMapper I_RISK_STATUS_MAPPER = new RiskStatusMapperImpl();

    @Autowired
    public RiskStatusServicesImpl(IRiskStatusRepository iRiskStatusRepository) {
        this.iRiskStatusRepository = iRiskStatusRepository;
    }

    @Override
    public ResponseEntity<List<RiskStatusDTO>> getAll() {
        List<RiskStatusEntity> riskStatusEntities = iRiskStatusRepository.findAll();
        List<RiskStatusDTO> riskStatusDTOS = riskStatusEntities
                .stream()
                .map(I_RISK_STATUS_MAPPER::riskStatusEntityToRiskStatusDTO)
                .collect(Collectors.toList());
        if (riskStatusDTOS.isEmpty()) {
            throw new RecordNotFoundException("Sorry, No DATA Found!...");
        }
        return new ResponseEntity<>(riskStatusDTOS, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<RiskStatusDTO> findById(Long id) {
        Optional<RiskStatusEntity> riskStatusEntity = iRiskStatusRepository.findById(id);
        if (riskStatusEntity.isEmpty()) {
            throw new RecordNotFoundException("the item with id: " + id + " not found!...");
        }
        RiskStatusDTO riskStatusDTO = I_RISK_STATUS_MAPPER.riskStatusEntityToRiskStatusDTO(riskStatusEntity.get());
        return new ResponseEntity<>(riskStatusDTO, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<RiskStatusDTO> create(RiskStatusDTO riskStatusDTO) {
        final RiskStatusEntity riskStatusEntity = I_RISK_STATUS_MAPPER.riskStatusDTOToRiskStatusEntity(riskStatusDTO);
        final RiskStatusEntity createdEntity = this.iRiskStatusRepository.save(riskStatusEntity);
        RiskStatusDTO createdDTO = I_RISK_STATUS_MAPPER.riskStatusEntityToRiskStatusDTO(createdEntity);
        return new ResponseEntity<>(createdDTO, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<RiskStatusDTO> update(Long id, RiskStatusDTO riskStatusDTO) {
        Optional<RiskStatusEntity> existingEntity = iRiskStatusRepository.findById(id);
        if (existingEntity.isEmpty()) {
            throw new RecordNotFoundException("the item with id: " + id + " not found!...");
        }
        RiskStatusEntity riskStatusEntity = existingEntity.get();
        riskStatusEntity.setRiskName(riskStatusDTO.getRiskName());
        RiskStatusEntity updatedEntity = this.iRiskStatusRepository.save(riskStatusEntity);
        RiskStatusDTO updatedDTO = I_RISK_STATUS_MAPPER.riskStatusEntityToRiskStatusDTO(updatedEntity);
        return new ResponseEntity<>(updatedDTO, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<String> remove(Long id) {
        if (!iRiskStatusRepository.existsById(id)) {
            throw new RecordNotFoundException("the item with id: " + id + " not found!...");
        }
        iRiskStatusRepository.deleteById(id);
        return new ResponseEntity<>("Deleted Successfully!...", HttpStatus.OK);
    }
}
