package com.a08r.pick_location.services;

import com.a08r.pick_location.errors.RecordNotFoundException;
import com.a08r.pick_location.models.dto.NeighborhoodDTO;
import com.a08r.pick_location.models.neighborhood.NeighborhoodEntity;
import com.a08r.pick_location.models.mapper.INeighborhoodMapper;
import com.a08r.pick_location.models.mapper.NeighborhoodMapperImpl;
import com.a08r.pick_location.repositores.INeighborhoodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class NeighborhoodServicesImpl implements INeighborhoodServices {

    private final INeighborhoodRepository iNeighborhoodRepository;
    private static final INeighborhoodMapper I_NEIGHBORHOOD_MAPPER = new NeighborhoodMapperImpl();

    @Autowired
    public NeighborhoodServicesImpl(INeighborhoodRepository iNeighborhoodRepository) {
        this.iNeighborhoodRepository = iNeighborhoodRepository;
    }

    @Override
    public ResponseEntity<List<NeighborhoodDTO>> getAll() {
        List<NeighborhoodEntity> neighborhoodEntities = iNeighborhoodRepository.findAll();
        List<NeighborhoodDTO> neighborhoodDTOS = neighborhoodEntities
                .stream()
                .map(I_NEIGHBORHOOD_MAPPER::neighborhoodEntityToNeighborhoodDTO)
                .collect(Collectors.toList());
        if (neighborhoodDTOS.isEmpty()) {
            throw new RecordNotFoundException("Sorry, No DATA Found!...");
        }
        return new ResponseEntity<>(neighborhoodDTOS, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<NeighborhoodDTO> findById(Long id) {
        Optional<NeighborhoodEntity> neighborhoodEntity = iNeighborhoodRepository.findById(id);
        if (neighborhoodEntity.isEmpty()) {
            throw new RecordNotFoundException("the item with id: " + id + " not found!...");
        }
        NeighborhoodDTO neighborhoodDTO = I_NEIGHBORHOOD_MAPPER.neighborhoodEntityToNeighborhoodDTO(neighborhoodEntity.get());
        return new ResponseEntity<>(neighborhoodDTO, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<NeighborhoodDTO> create(NeighborhoodDTO neighborhoodDTO) {
        final NeighborhoodEntity neighborhoodEntity = I_NEIGHBORHOOD_MAPPER.neighborhoodDTOToNeighborhoodEntity(neighborhoodDTO);
        final NeighborhoodEntity createdEntity = this.iNeighborhoodRepository.save(neighborhoodEntity);
        NeighborhoodDTO createdDTO = I_NEIGHBORHOOD_MAPPER.neighborhoodEntityToNeighborhoodDTO(createdEntity);
        return new ResponseEntity<>(createdDTO, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<NeighborhoodDTO> update(Long id, NeighborhoodDTO neighborhoodDTO) {
        Optional<NeighborhoodEntity> existingEntity = iNeighborhoodRepository.findById(id);
        if (existingEntity.isEmpty()) {
            throw new RecordNotFoundException("the item with id: " + id + " not found!...");
        }
        NeighborhoodEntity neighborhoodEntity = existingEntity.get();
        neighborhoodEntity.setNeighborhoodName(neighborhoodDTO.getNeighborhoodName());
        NeighborhoodEntity updatedEntity = this.iNeighborhoodRepository.save(neighborhoodEntity);
        NeighborhoodDTO updatedDTO = I_NEIGHBORHOOD_MAPPER.neighborhoodEntityToNeighborhoodDTO(updatedEntity);
        return new ResponseEntity<>(updatedDTO, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<String> remove(Long id) {
        if (!iNeighborhoodRepository.existsById(id)) {
            throw new RecordNotFoundException("the item with id: " + id + " not found!...");
        }
        iNeighborhoodRepository.deleteById(id);
        return new ResponseEntity<>("Deleted Successfully!...", HttpStatus.OK);
    }
}
