package com.a08r.pick_location.services;

import com.a08r.pick_location.errors.RecordNotFoundException;
import com.a08r.pick_location.models.dto.SectorDTO;
import com.a08r.pick_location.models.sector.SectorEntity;
import com.a08r.pick_location.models.mapper.ISectorMapper;
import com.a08r.pick_location.models.mapper.SectorMapperImpl;
import com.a08r.pick_location.repositores.ISectorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SectorServicesImpl implements ISectorServices {

    private final ISectorRepository iSectorRepository;
    private static final ISectorMapper I_SECTOR_MAPPER = new SectorMapperImpl();

    @Autowired
    public SectorServicesImpl(ISectorRepository iSectorRepository) {
        this.iSectorRepository = iSectorRepository;
    }

    @Override
    public ResponseEntity<List<SectorDTO>> getAll() {
        List<SectorEntity> sectorEntities = iSectorRepository.findAll();
        List<SectorDTO> sectorDTOS = sectorEntities
                .stream()
                .map(I_SECTOR_MAPPER::sectorEntityToSectorDTO)
                .collect(Collectors.toList());
        if (sectorDTOS.isEmpty()) {
            throw new RecordNotFoundException("Sorry, No DATA Found!...");
        }
        return new ResponseEntity<>(sectorDTOS, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<SectorDTO> findById(Long id) {
        Optional<SectorEntity> sectorEntity = iSectorRepository.findById(id);
        if (sectorEntity.isEmpty()) {
            throw new RecordNotFoundException("the item with id: " + id + " not found!...");
        }
        SectorDTO sectorDTO = I_SECTOR_MAPPER.sectorEntityToSectorDTO(sectorEntity.get());
        return new ResponseEntity<>(sectorDTO, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<SectorDTO> create(SectorDTO sectorDTO) {
        final SectorEntity sectorEntity = I_SECTOR_MAPPER.sectorDTOToSectorEntity(sectorDTO);
        final SectorEntity createdEntity = this.iSectorRepository.save(sectorEntity);
        SectorDTO createdDTO = I_SECTOR_MAPPER.sectorEntityToSectorDTO(createdEntity);
        return new ResponseEntity<>(createdDTO, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<SectorDTO> update(Long id, SectorDTO sectorDTO) {
        Optional<SectorEntity> existingEntity = iSectorRepository.findById(id);
        if (existingEntity.isEmpty()) {
            throw new RecordNotFoundException("the item with id: " + id + " not found!...");
        }
        SectorEntity sectorEntity = existingEntity.get();
        sectorEntity.setSectorName(sectorDTO.getSectorName());
        SectorEntity updatedEntity = this.iSectorRepository.save(sectorEntity);
        SectorDTO updatedDTO = I_SECTOR_MAPPER.sectorEntityToSectorDTO(updatedEntity);
        return new ResponseEntity<>(updatedDTO, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<String> remove(Long id) {
        if (!iSectorRepository.existsById(id)) {
            throw new RecordNotFoundException("the item with id: " + id + " not found!...");
        }
        iSectorRepository.deleteById(id);
        return new ResponseEntity<>("Deleted Successfully!...", HttpStatus.OK);
    }
}
