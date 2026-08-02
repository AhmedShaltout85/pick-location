package com.a08r.pick_location.services;

import com.a08r.pick_location.errors.RecordNotFoundException;
import com.a08r.pick_location.models.dto.ImportantStatusDTO;
import com.a08r.pick_location.models.important.ImportantStatusEntity;
import com.a08r.pick_location.models.mapper.IImportantStatusMapper;
import com.a08r.pick_location.models.mapper.ImportantStatusMapperImpl;
import com.a08r.pick_location.repositores.IImportantStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ImportantStatusServicesImpl implements IImportantStatusServices {

    private final IImportantStatusRepository iImportantStatusRepository;
    private static final IImportantStatusMapper I_IMPORTANT_STATUS_MAPPER = new ImportantStatusMapperImpl();

    @Autowired
    public ImportantStatusServicesImpl(IImportantStatusRepository iImportantStatusRepository) {
        this.iImportantStatusRepository = iImportantStatusRepository;
    }

    @Override
    public ResponseEntity<List<ImportantStatusDTO>> getAll() {
        List<ImportantStatusEntity> importantStatusEntities = iImportantStatusRepository.findAll();
        List<ImportantStatusDTO> importantStatusDTOS = importantStatusEntities
                .stream()
                .map(I_IMPORTANT_STATUS_MAPPER::importantStatusEntityToImportantStatusDTO)
                .collect(Collectors.toList());
        if (importantStatusDTOS.isEmpty()) {
            throw new RecordNotFoundException("Sorry, No DATA Found!...");
        }
        return new ResponseEntity<>(importantStatusDTOS, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ImportantStatusDTO> findById(Long id) {
        Optional<ImportantStatusEntity> importantStatusEntity = iImportantStatusRepository.findById(id);
        if (importantStatusEntity.isEmpty()) {
            throw new RecordNotFoundException("the item with id: " + id + " not found!...");
        }
        ImportantStatusDTO importantStatusDTO = I_IMPORTANT_STATUS_MAPPER.importantStatusEntityToImportantStatusDTO(importantStatusEntity.get());
        return new ResponseEntity<>(importantStatusDTO, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ImportantStatusDTO> create(ImportantStatusDTO importantStatusDTO) {
        final ImportantStatusEntity importantStatusEntity = I_IMPORTANT_STATUS_MAPPER.importantStatusDTOToImportantStatusEntity(importantStatusDTO);
        final ImportantStatusEntity createdEntity = this.iImportantStatusRepository.save(importantStatusEntity);
        ImportantStatusDTO createdDTO = I_IMPORTANT_STATUS_MAPPER.importantStatusEntityToImportantStatusDTO(createdEntity);
        return new ResponseEntity<>(createdDTO, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<ImportantStatusDTO> update(Long id, ImportantStatusDTO importantStatusDTO) {
        Optional<ImportantStatusEntity> existingEntity = iImportantStatusRepository.findById(id);
        if (existingEntity.isEmpty()) {
            throw new RecordNotFoundException("the item with id: " + id + " not found!...");
        }
        ImportantStatusEntity importantStatusEntity = existingEntity.get();
        importantStatusEntity.setImportantName(importantStatusDTO.getImportantName());
        ImportantStatusEntity updatedEntity = this.iImportantStatusRepository.save(importantStatusEntity);
        ImportantStatusDTO updatedDTO = I_IMPORTANT_STATUS_MAPPER.importantStatusEntityToImportantStatusDTO(updatedEntity);
        return new ResponseEntity<>(updatedDTO, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<String> remove(Long id) {
        if (!iImportantStatusRepository.existsById(id)) {
            throw new RecordNotFoundException("the item with id: " + id + " not found!...");
        }
        iImportantStatusRepository.deleteById(id);
        return new ResponseEntity<>("Deleted Successfully!...", HttpStatus.OK);
    }
}
