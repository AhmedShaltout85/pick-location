package com.a08r.pick_location.services;

import com.a08r.pick_location.errors.RecordNotFoundException;
import com.a08r.pick_location.models.dto.PickLocationHandasahDTO;
import com.a08r.pick_location.models.dto.PickLocationUsersDTO;
import com.a08r.pick_location.models.handasah.PickLocationHandasahEntity;
import com.a08r.pick_location.models.mapper.IPickLocationHandasahMapper;
import com.a08r.pick_location.models.mapper.IPickLocationUsersMapper;
import com.a08r.pick_location.models.mapper.PickLocationHandasahMapperImpl;
import com.a08r.pick_location.models.mapper.PickLocationUsersMapperImpl;
import com.a08r.pick_location.models.users.PickLocationUsersEntity;
import com.a08r.pick_location.repositores.IPickLocationHandasahRepository;
import com.a08r.pick_location.repositores.IPickLocationUsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PickLocationHandasahServicesImpl implements IPickLocationHandasahServices {
    private final IPickLocationHandasahRepository iPickLocationHandasahRepository;

    private static final IPickLocationHandasahMapper I_PICK_LOCATION_HANDASAH_MAPPER = new PickLocationHandasahMapperImpl();

    @Autowired
    public PickLocationHandasahServicesImpl(IPickLocationHandasahRepository iPickLocationHandasahRepository) {
        this.iPickLocationHandasahRepository = iPickLocationHandasahRepository;
    }

//    @Override
//    public ResponseEntity<List<PickLocationHandasahDTO>> getAllHandasah() {
//        List<PickLocationHandasahEntity> pickLocationHandasahEntities = iPickLocationHandasahRepository.findAll();
//        List<PickLocationHandasahDTO> pickLocationHandasahDTOList = pickLocationHandasahEntities
//                .stream()
//                .map(I_PICK_LOCATION_HANDASAH_MAPPER::pickLocationHandasahEntityToPickLocationHandasahDTO)
//                .collect(Collectors.toList());
//        if (pickLocationHandasahDTOList.isEmpty()) {
//            throw new RecordNotFoundException("Sorry, No DATA Found!...");
//        }
//        return new ResponseEntity<>(pickLocationHandasahDTOList, HttpStatus.OK);
//    }

    public ResponseEntity<List<String>> getAllHandasah() {
        List<PickLocationHandasahEntity> pickLocationHandasahEntities = iPickLocationHandasahRepository.findAll();

        List<String> handasahNames = pickLocationHandasahEntities
                .stream()
                .map(entity -> I_PICK_LOCATION_HANDASAH_MAPPER.pickLocationHandasahEntityToPickLocationHandasahDTO(entity).getHandasahName()) // Extract name
                .collect(Collectors.toList());

        if (handasahNames.isEmpty()) {
            throw new RecordNotFoundException("Sorry, No DATA Found!...");
        }

        return new ResponseEntity<>(handasahNames, HttpStatus.OK);
    }


    @Override
    public ResponseEntity<PickLocationHandasahDTO> findHandasahById(Long id) {

        Optional<PickLocationHandasahEntity> pickLocationHandasahEntity = iPickLocationHandasahRepository.findById(id);
        if (pickLocationHandasahEntity.isEmpty()) {
            throw new RecordNotFoundException("the item with id: " + id + " not found!...");
        }
        PickLocationHandasahDTO pickLocationHandasahDTO = I_PICK_LOCATION_HANDASAH_MAPPER.pickLocationHandasahEntityToPickLocationHandasahDTO(pickLocationHandasahEntity.get());
        return new ResponseEntity<>(pickLocationHandasahDTO, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<PickLocationHandasahDTO> createHandasah(PickLocationHandasahDTO newPickLocationHandasahDTO) {

        final PickLocationHandasahEntity pickLocationHandasahEntity = I_PICK_LOCATION_HANDASAH_MAPPER.pickLocationHandasahDTOToPickLocationHandasahEntity(newPickLocationHandasahDTO);
        final PickLocationHandasahEntity createPickLocationHandasahEntity = this.iPickLocationHandasahRepository.save(pickLocationHandasahEntity);
        PickLocationHandasahDTO pickLocationHandasahDTO = I_PICK_LOCATION_HANDASAH_MAPPER.pickLocationHandasahEntityToPickLocationHandasahDTO(createPickLocationHandasahEntity);
        return new ResponseEntity<>(pickLocationHandasahDTO, HttpStatus.CREATED);

    }


}
