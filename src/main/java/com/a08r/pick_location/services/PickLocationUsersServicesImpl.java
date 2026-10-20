package com.a08r.pick_location.services;

import com.a08r.pick_location.errors.RecordNotFoundException;
import com.a08r.pick_location.models.dto.ChangePasswordRequestDTO;
import com.a08r.pick_location.models.dto.PickLocationDTO;
import com.a08r.pick_location.models.dto.PickLocationUsersDTO;
import com.a08r.pick_location.models.location.PickLocationEntity;
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
//TODO: update user
    @Override
    public ResponseEntity<PickLocationUsersDTO> updateUser(Long id, PickLocationUsersDTO newPickLocationUserDTO) {
        Optional<PickLocationUsersEntity> pickLocationUsersEntity = iPickLocationUsersRepository.findById(id);
        if (pickLocationUsersEntity.isEmpty()) {
            throw new RecordNotFoundException("the user with id: " + id + " not found!...");
        }

        PickLocationUsersEntity existingPickLocationUsersEntity = pickLocationUsersEntity.get();

        existingPickLocationUsersEntity.setUserName(newPickLocationUserDTO.getUsername());
        existingPickLocationUsersEntity.setPassword(newPickLocationUserDTO.getPassword());
        existingPickLocationUsersEntity.setRole(newPickLocationUserDTO.getRole());
        existingPickLocationUsersEntity.setControlUnit(newPickLocationUserDTO.getControlUnit());
        existingPickLocationUsersEntity.setTechnicalId(newPickLocationUserDTO.getTechnicalId());

        PickLocationUsersEntity updatedPickLocationUsersEntity = iPickLocationUsersRepository.save(existingPickLocationUsersEntity);

        PickLocationUsersDTO pickLocationUsersDTO = I_PICK_LOCATION_USERS_MAPPER
                .pickLocationUsersEntityToPickLocationUsersDTO(updatedPickLocationUsersEntity);

        return new ResponseEntity<>(pickLocationUsersDTO, HttpStatus.OK);
    }
//TODO: change password
@Override
public ResponseEntity<String> changePassword(ChangePasswordRequestDTO request) {

    // 1) basic validation
    if (request.getUserName() == null || request.getUserName().isBlank()
            || request.getOldPassword() == null || request.getOldPassword().isBlank()
            || request.getNewPassword() == null || request.getNewPassword().isBlank()) {
        return new ResponseEntity<>("userName, oldPassword and newPassword are required.",
                HttpStatus.BAD_REQUEST);
    }

    // 2) find the user
    Optional<PickLocationUsersEntity> pickLocationUsersEntity =
            iPickLocationUsersRepository.findByUserName(request.getUserName());

    if (pickLocationUsersEntity.isEmpty()) {
        throw new RecordNotFoundException(
                "the user with username: " + request.getUserName() + " not found!...");
    }

    PickLocationUsersEntity user = pickLocationUsersEntity.get();

    // 3) verify old password
    if (!user.getPassword().equals(request.getOldPassword())) {
        return new ResponseEntity<>("Old password is incorrect.", HttpStatus.UNAUTHORIZED);
        // or HttpStatus.BAD_REQUEST / FORBIDDEN depending on your semantics
    }

    // 4) prevent "new same as old"
    if (user.getPassword().equals(request.getNewPassword())) {
        return new ResponseEntity<>("New password must be different from the old one.",
                HttpStatus.BAD_REQUEST);
    }

    // 5) optionally: minimum length / complexity check
    if (request.getNewPassword().length() < 6) {
        return new ResponseEntity<>("New password must be at least 6 characters.",
                HttpStatus.BAD_REQUEST);
    }

    // 6) update & save
    user.setPassword(request.getNewPassword());
    iPickLocationUsersRepository.save(user);

    return new ResponseEntity<>("Password updated successfully!", HttpStatus.OK);
}

    @Override
    public ResponseEntity<String> remove(Long id) {
        boolean exists = iPickLocationUsersRepository.existsById(id);
        if (!exists) {
            throw new RecordNotFoundException("the item with id: " + id + " not found!...");

        }
        this.iPickLocationUsersRepository.deleteById(id);
        return new ResponseEntity<>("The item has been deleted successfully!", HttpStatus.OK);
    }

}
