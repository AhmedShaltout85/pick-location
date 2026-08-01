package com.a08r.pick_location.services;

import com.a08r.pick_location.errors.RecordNotFoundException;
import com.a08r.pick_location.models.dto.RepaireStatusDTO;
import com.a08r.pick_location.models.repair.RepaireStatusEntity;
import com.a08r.pick_location.models.mapper.IRepaireStatusMapper;
import com.a08r.pick_location.models.mapper.RepaireStatusMapperImpl;
import com.a08r.pick_location.repositores.IRepaireStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RepaireStatusServicesImpl implements IRepaireStatusServices {

    private final IRepaireStatusRepository iRepaireStatusRepository;
    private static final IRepaireStatusMapper I_REPAIRE_STATUS_MAPPER = new RepaireStatusMapperImpl();

    @Autowired
    public RepaireStatusServicesImpl(IRepaireStatusRepository iRepaireStatusRepository) {
        this.iRepaireStatusRepository = iRepaireStatusRepository;
    }

    @Override
    public ResponseEntity<List<RepaireStatusDTO>> getAll() {
        List<RepaireStatusEntity> repaireStatusEntities = iRepaireStatusRepository.findAll();
        List<RepaireStatusDTO> repaireStatusDTOS = repaireStatusEntities
                .stream()
                .map(I_REPAIRE_STATUS_MAPPER::repaireStatusEntityToRepaireStatusDTO)
                .collect(Collectors.toList());
        if (repaireStatusDTOS.isEmpty()) {
            throw new RecordNotFoundException("Sorry, No DATA Found!...");
        }
        return new ResponseEntity<>(repaireStatusDTOS, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<RepaireStatusDTO> findById(Long id) {
        Optional<RepaireStatusEntity> repaireStatusEntity = iRepaireStatusRepository.findById(id);
        if (repaireStatusEntity.isEmpty()) {
            throw new RecordNotFoundException("the item with id: " + id + " not found!...");
        }
        RepaireStatusDTO repaireStatusDTO = I_REPAIRE_STATUS_MAPPER.repaireStatusEntityToRepaireStatusDTO(repaireStatusEntity.get());
        return new ResponseEntity<>(repaireStatusDTO, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<RepaireStatusDTO> create(RepaireStatusDTO repaireStatusDTO) {
        final RepaireStatusEntity repaireStatusEntity = I_REPAIRE_STATUS_MAPPER.repaireStatusDTOToRepaireStatusEntity(repaireStatusDTO);
        final RepaireStatusEntity createdEntity = this.iRepaireStatusRepository.save(repaireStatusEntity);
        RepaireStatusDTO createdDTO = I_REPAIRE_STATUS_MAPPER.repaireStatusEntityToRepaireStatusDTO(createdEntity);
        return new ResponseEntity<>(createdDTO, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<RepaireStatusDTO> update(Long id, RepaireStatusDTO repaireStatusDTO) {
        Optional<RepaireStatusEntity> existingEntity = iRepaireStatusRepository.findById(id);
        if (existingEntity.isEmpty()) {
            throw new RecordNotFoundException("the item with id: " + id + " not found!...");
        }
        RepaireStatusEntity repaireStatusEntity = existingEntity.get();
        repaireStatusEntity.setRepaireType(repaireStatusDTO.getRepaireType());
        RepaireStatusEntity updatedEntity = this.iRepaireStatusRepository.save(repaireStatusEntity);
        RepaireStatusDTO updatedDTO = I_REPAIRE_STATUS_MAPPER.repaireStatusEntityToRepaireStatusDTO(updatedEntity);
        return new ResponseEntity<>(updatedDTO, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<String> remove(Long id) {
        if (!iRepaireStatusRepository.existsById(id)) {
            throw new RecordNotFoundException("the item with id: " + id + " not found!...");
        }
        iRepaireStatusRepository.deleteById(id);
        return new ResponseEntity<>("Deleted Successfully!...", HttpStatus.OK);
    }
}
