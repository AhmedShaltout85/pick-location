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

import java.util.ArrayList;
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
        List<PickLocationDTO> pickLocationDTOList = pickLocationEntities
                .stream()
                .map(I_PICK_LOCATION_MAPPER::pickLocationEntityToPickLocationDTO)
                .collect(Collectors.toList());
        if (pickLocationDTOList.isEmpty()) {
            throw new RecordNotFoundException("Sorry, No DATA Found!...");
        }
        return new ResponseEntity<>(pickLocationDTOList, HttpStatus.OK);

    }

    @Override
    public ResponseEntity<List<PickLocationDTO>> getPickLocationByFlagAndIsFinished(int flag, int is_finished) {

        List<PickLocationEntity> pickLocationEntities = iPickLocationRepository.findByFlagAndIsFinished(flag, is_finished);
        List<PickLocationDTO> pickLocationDTOList = pickLocationEntities
                .stream()
                .map(I_PICK_LOCATION_MAPPER::pickLocationEntityToPickLocationDTO)
                .collect(Collectors.toList());
        if (pickLocationDTOList.isEmpty()) {
            throw new RecordNotFoundException("Sorry, No DATA Found!...");
        }
        return new ResponseEntity<>(pickLocationDTOList, HttpStatus.OK);

    }

    @Override
    public ResponseEntity<List<PickLocationDTO>> getPickLocationByFlag(int flag) {

        List<PickLocationEntity> pickLocationEntities = iPickLocationRepository.findByFlag(flag);
        List<PickLocationDTO> pickLocationDTOList = pickLocationEntities
                .stream()
                .map(I_PICK_LOCATION_MAPPER::pickLocationEntityToPickLocationDTO)
                .collect(Collectors.toList());
        if (pickLocationDTOList.isEmpty()) {
//            throw new RecordNotFoundException("Sorry, No DATA Found!...");
            return new ResponseEntity<>(new ArrayList<>(), HttpStatus.OK);
        }
        return new ResponseEntity<>(pickLocationDTOList, HttpStatus.OK);

    }

    @Override
    public ResponseEntity<PickLocationDTO> createAddress(PickLocationDTO newPickLocationDTO) {
        // Check if address with the given id already exists(comment now because address is not unique, we don't need to send it)
        // Check if address with the given id already exists
//        Optional<PickLocationEntity> existingAddress = iPickLocationRepository.findById(newPickLocationDTO.getId());
//
//        if (existingAddress.isPresent()) {
//            // Return some message indicating the address already exists
//            return ResponseEntity.status(HttpStatus.CONFLICT).build();
//        }
        final PickLocationEntity pickLocationEntity = I_PICK_LOCATION_MAPPER.pickLocationDTOToPickLocationEntity(newPickLocationDTO);
        final PickLocationEntity createPickLocationEntity = this.iPickLocationRepository.save(pickLocationEntity);
        PickLocationDTO pickLocationDTO = I_PICK_LOCATION_MAPPER.pickLocationEntityToPickLocationDTO(createPickLocationEntity);
        return new ResponseEntity<>(pickLocationDTO, HttpStatus.CREATED);

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
        exitingPickLocationEntity.setGis_url(newPickLocationDTO.getGis_url());
        PickLocationEntity updatePickLocationEntity = iPickLocationRepository.save(exitingPickLocationEntity);
        PickLocationDTO pickLocationDTO = I_PICK_LOCATION_MAPPER.pickLocationEntityToPickLocationDTO(updatePickLocationEntity);
        return new ResponseEntity<>(pickLocationDTO, HttpStatus.OK);

    }

    @Override
    public ResponseEntity<PickLocationDTO> updatePickLocationByAddress(String address, PickLocationDTO newPickLocationDTO) {
        Optional<PickLocationEntity> pickLocationEntity = iPickLocationRepository.findByAddress(address);
        if (pickLocationEntity.isEmpty()) {
            throw new RecordNotFoundException("the item with address: " + address + " not found!...");
        }
        PickLocationEntity exitingPickLocationEntity = pickLocationEntity.get();
        exitingPickLocationEntity.setLatitude(newPickLocationDTO.getLatitude());
        exitingPickLocationEntity.setLongitude(newPickLocationDTO.getLongitude());
        exitingPickLocationEntity.setFlag(newPickLocationDTO.getFlag());
        exitingPickLocationEntity.setGis_url(newPickLocationDTO.getGis_url());
//        exitingPickLocationEntity.setHandasahName(newPickLocationDTO.getHandasah_name());
//        exitingPickLocationEntity.setTechnicalName(newPickLocationDTO.getTechnical_name());
//        exitingPickLocationEntity.setIsFinished(newPickLocationDTO.getIs_finished());
        PickLocationEntity updatePickLocationEntity = this.iPickLocationRepository.save(exitingPickLocationEntity);
        PickLocationDTO pickLocationDTO = I_PICK_LOCATION_MAPPER.pickLocationEntityToPickLocationDTO(updatePickLocationEntity);
        return new ResponseEntity<>(pickLocationDTO, HttpStatus.OK);

    }

    //    updatePickLocationHandasahByAddress
    @Override
    public ResponseEntity<PickLocationDTO> updatePickLocationHandasah(String address, PickLocationDTO newPickLocationDTO) {
        Optional<PickLocationEntity> pickLocationEntity = iPickLocationRepository.findByAddress(address);
        if (pickLocationEntity.isEmpty()) {
            throw new RecordNotFoundException("the item with address: " + address + " not found!...");
        }
        PickLocationEntity exitingPickLocationEntity = pickLocationEntity.get();
        exitingPickLocationEntity.setHandasahName(newPickLocationDTO.getHandasah_name());
        PickLocationEntity updatePickLocationEntity = this.iPickLocationRepository.save(exitingPickLocationEntity);
        PickLocationDTO pickLocationDTO = I_PICK_LOCATION_MAPPER.pickLocationEntityToPickLocationDTO(updatePickLocationEntity);
        return new ResponseEntity<>(pickLocationDTO, HttpStatus.OK);

    }

    //     updatePickLocationTechnicalUserByAddress
    @Override
    public ResponseEntity<PickLocationDTO> updatePickLocationTechnicalUser(String address, PickLocationDTO newPickLocationDTO) {
        Optional<PickLocationEntity> pickLocationEntity = iPickLocationRepository.findByAddress(address);
        if (pickLocationEntity.isEmpty()) {
            throw new RecordNotFoundException("the item with address: " + address + " not found!...");
        }
        PickLocationEntity exitingPickLocationEntity = pickLocationEntity.get();
        exitingPickLocationEntity.setTechnicalName(newPickLocationDTO.getTechnical_name());
        PickLocationEntity updatePickLocationEntity = this.iPickLocationRepository.save(exitingPickLocationEntity);
        PickLocationDTO pickLocationDTO = I_PICK_LOCATION_MAPPER.pickLocationEntityToPickLocationDTO(updatePickLocationEntity);
        return new ResponseEntity<>(pickLocationDTO, HttpStatus.OK);
    }

    //    updatePickLocationIsFinishedByAddress
    @Override
    public ResponseEntity<PickLocationDTO> updatePickLocationIsFinished(String address, PickLocationDTO newPickLocationDTO) {
        Optional<PickLocationEntity> pickLocationEntity = iPickLocationRepository.findByAddress(address);
        if (pickLocationEntity.isEmpty()) {
            throw new RecordNotFoundException("the item with address: " + address + " not found!...");
        }
        PickLocationEntity exitingPickLocationEntity = pickLocationEntity.get();
        exitingPickLocationEntity.setIsFinished(newPickLocationDTO.getIs_finished());
        PickLocationEntity updatePickLocationEntity = this.iPickLocationRepository.save(exitingPickLocationEntity);
        PickLocationDTO pickLocationDTO = I_PICK_LOCATION_MAPPER.pickLocationEntityToPickLocationDTO(updatePickLocationEntity);
        return new ResponseEntity<>(pickLocationDTO, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<PickLocationDTO> findPickLocationByAddressAndFlag(String address, int flag) {

        Optional<PickLocationEntity> pickLocationEntity = iPickLocationRepository.findByAddressAndFlag(address, flag);
        if (pickLocationEntity.isEmpty()) {
//            return new ResponseEntity<>(new PickLocationDTO(), HttpStatus.NOT_FOUND);
            throw new RecordNotFoundException("the item with address: " + address + " and flag: " + flag + " not found!...");
        }
        PickLocationEntity exitingPickLocationEntity = pickLocationEntity.get();
        PickLocationDTO pickLocationDTO = I_PICK_LOCATION_MAPPER.pickLocationEntityToPickLocationDTO(exitingPickLocationEntity);
        return new ResponseEntity<>(pickLocationDTO, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<PickLocationDTO> findPickLocationByAddress(String address) {
        Optional<PickLocationEntity> pickLocationEntity = iPickLocationRepository.findByAddress(address);
        if (pickLocationEntity.isEmpty()) {
//            return new ResponseEntity<>(new PickLocationDTO(), HttpStatus.NOT_FOUND);
            throw new RecordNotFoundException("the item with address: " + address + " not found!...");
        }
        PickLocationEntity exitingPickLocationEntity = pickLocationEntity.get();
        PickLocationDTO pickLocationDTO = I_PICK_LOCATION_MAPPER.pickLocationEntityToPickLocationDTO(exitingPickLocationEntity);
        return new ResponseEntity<>(pickLocationDTO, HttpStatus.OK);
    }


}
