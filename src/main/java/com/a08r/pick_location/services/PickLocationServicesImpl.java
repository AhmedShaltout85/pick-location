package com.a08r.pick_location.services;

import com.a08r.pick_location.errors.RecordNotFoundException;
import com.a08r.pick_location.models.dto.PickLocationDTO;
import com.a08r.pick_location.models.location.PickLocationEntity;
import com.a08r.pick_location.models.mapper.IPickLocationMapper;
import com.a08r.pick_location.models.mapper.PickLocationMapperImpl;
import com.a08r.pick_location.repositores.IPickLocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PickLocationServicesImpl implements IPickLocationServices {

    private final IPickLocationRepository iPickLocationRepository;
    private static final IPickLocationMapper I_PICK_LOCATION_MAPPER = new PickLocationMapperImpl();

    @Autowired
    public PickLocationServicesImpl(IPickLocationRepository iPickLocationRepository) {
        this.iPickLocationRepository = iPickLocationRepository;
    }

    @Override
    public ResponseEntity<List<PickLocationDTO>> getAddress() {

        List<PickLocationEntity> pickLocationEntities = iPickLocationRepository.findAll();
//        List<PickLocationEntity> pickLocationEntities = iPickLocationRepository.GetAddress();
        List<PickLocationDTO> labsDailyTestDTOList = pickLocationEntities
                .stream()
                .map(I_PICK_LOCATION_MAPPER::pickLocationEntityToPickLocationDTO)
                .collect(Collectors.toList());
        if (labsDailyTestDTOList.isEmpty()) {
            throw new RecordNotFoundException("Sorry, No DATA Found!...");
        }
        return new ResponseEntity<>(labsDailyTestDTOList, HttpStatus.OK);

    }

    @Override
    public ResponseEntity<List<PickLocationDTO>> getPickLocationByFlag(int flag) {

        List<PickLocationEntity> pickLocationEntities = iPickLocationRepository.findByFlag(flag);
        List<PickLocationDTO> labsDailyTestDTOList = pickLocationEntities
                .stream()
                .map(I_PICK_LOCATION_MAPPER::pickLocationEntityToPickLocationDTO)
                .collect(Collectors.toList());
        if (labsDailyTestDTOList.isEmpty()) {
            throw new RecordNotFoundException("Sorry, No DATA Found!...");
        }
        return new ResponseEntity<>(labsDailyTestDTOList, HttpStatus.OK);

    }

    @Override
    public ResponseEntity<PickLocationDTO> updateAddress(Long id, PickLocationDTO newPickLocationDTO) {
        Optional<PickLocationEntity> pickLocationEntity = iPickLocationRepository.findById(id);
        if (pickLocationEntity.isEmpty()) {
            throw new RecordNotFoundException("the item with id: " + id + " not found!...");
        }
        PickLocationEntity exitingPickLocationEntity = pickLocationEntity.get();
        exitingPickLocationEntity.setLatitude(newPickLocationDTO.getLatitude());
        exitingPickLocationEntity.setLongitude(newPickLocationDTO.getLongitude());
        exitingPickLocationEntity.setFlag(newPickLocationDTO.getFlag());
        PickLocationEntity updatePickLocationEntity = iPickLocationRepository.save(exitingPickLocationEntity);
        PickLocationDTO pickLocationDTO = I_PICK_LOCATION_MAPPER.pickLocationEntityToPickLocationDTO(updatePickLocationEntity);
        return new ResponseEntity<>(pickLocationDTO, HttpStatus.OK);

    }



}
