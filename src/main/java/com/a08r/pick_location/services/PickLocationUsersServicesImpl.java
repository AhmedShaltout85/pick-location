package com.a08r.pick_location.services;

import com.a08r.pick_location.errors.RecordNotFoundException;
import com.a08r.pick_location.models.dto.PickLocationUsersDTO;
import com.a08r.pick_location.models.mapper.IPickLocationUsersMapper;
import com.a08r.pick_location.models.mapper.PickLocationUsersMapperImpl;
import com.a08r.pick_location.models.users.PickLocationUsersEntity;
import com.a08r.pick_location.repositores.IPickLocationUsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PickLocationUsersServicesImpl implements IPickLocationUsersServices {

    private final IPickLocationUsersRepository iPickLocationUsersRepository;
    private static final IPickLocationUsersMapper I_PICK_LOCATION_USERS_MAPPER = new PickLocationUsersMapperImpl();

    @Autowired
    public PickLocationUsersServicesImpl(IPickLocationUsersRepository iPickLocationUsersRepository) {
        this.iPickLocationUsersRepository = iPickLocationUsersRepository;
    }


    @Override
    public ResponseEntity<List<PickLocationUsersDTO>> getAllUsers() {
        List<PickLocationUsersEntity> pickLocationUsersEntities = iPickLocationUsersRepository.findAll();
        List<PickLocationUsersDTO> pickLocationUsersDTOList = pickLocationUsersEntities
                .stream()
                .map(I_PICK_LOCATION_USERS_MAPPER::pickLocationUsersEntityToPickLocationUsersDTO)
                .collect(Collectors.toList());
        if (pickLocationUsersDTOList.isEmpty()) {
            throw new RecordNotFoundException("Sorry, No DATA Found!...");
        }
        return new ResponseEntity<>(pickLocationUsersDTOList, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<PickLocationUsersDTO> findUserByUsersId(Long id) {
        Optional<PickLocationUsersEntity> pickLocationUsersEntity = iPickLocationUsersRepository.findById(id);
        if (pickLocationUsersEntity.isEmpty()) {
            throw new RecordNotFoundException("the item with id: " + id + " not found!...");

        }
        PickLocationUsersDTO pickLocationUsersDTO = I_PICK_LOCATION_USERS_MAPPER.pickLocationUsersEntityToPickLocationUsersDTO(pickLocationUsersEntity.get());
        return new ResponseEntity<>(pickLocationUsersDTO, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<PickLocationUsersDTO> createUser(PickLocationUsersDTO newPickLocationUsersDTO) {
//
        final PickLocationUsersEntity pickLocationEntity = I_PICK_LOCATION_USERS_MAPPER.pickLocationUsersDTOToPickLocationUsersEntity(newPickLocationUsersDTO);
        final PickLocationUsersEntity createPickLocationUsersEntity = this.iPickLocationUsersRepository.save(pickLocationEntity);
        PickLocationUsersDTO pickLocationUsersDTO = I_PICK_LOCATION_USERS_MAPPER.pickLocationUsersEntityToPickLocationUsersDTO(createPickLocationUsersEntity);
        return new ResponseEntity<>(pickLocationUsersDTO, HttpStatus.CREATED);

    }

    @Override
    public ResponseEntity<PickLocationUsersDTO> findUserByUserNameAndPassword(String userName, String password) {
        Optional<PickLocationUsersEntity> pickLocationUsersEntity = iPickLocationUsersRepository.findByUserNameAndPassword(userName, password);
//        if(pickLocationUsersEntity.isEmpty()){
//            throw new RecordNotFoundException("the item with UserName: "+userName +" and Password: "+password + " not found!...");
//
//        }
        PickLocationUsersDTO pickLocationUsersDTO = I_PICK_LOCATION_USERS_MAPPER.pickLocationUsersEntityToPickLocationUsersDTO(pickLocationUsersEntity.get());
        return new ResponseEntity<>(pickLocationUsersDTO, HttpStatus.OK);
    }


    @Override
    public ResponseEntity<List<String>> getUsersByRoleAndControlUnit(int role, String controlUnit) {
        List<PickLocationUsersEntity> pickLocationUsersEntity = iPickLocationUsersRepository.findByRoleAndControlUnit(role, controlUnit);

        List<String> usersNames = pickLocationUsersEntity
                .stream()
                .map(entity -> I_PICK_LOCATION_USERS_MAPPER.pickLocationUsersEntityToPickLocationUsersDTO(entity).getUsername()) // Extract name
                .collect(Collectors.toList());

        if (usersNames.isEmpty()) {
            throw new RecordNotFoundException("Sorry, No DATA Found!...");
        }

        return new ResponseEntity<>(usersNames, HttpStatus.OK);


    }


}
