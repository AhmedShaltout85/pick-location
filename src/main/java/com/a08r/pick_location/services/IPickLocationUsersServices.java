package com.a08r.pick_location.services;

import com.a08r.pick_location.models.dto.PickLocationUsersDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IPickLocationUsersServices {

//    ResponseEntity<List<PickLocationUsersDTO>> getAllUsers();
    ResponseEntity<List<PickLocationUsersDTO>> getAllUsers();

    ResponseEntity<PickLocationUsersDTO> findUserByUsersId(Long id);

    ResponseEntity<PickLocationUsersDTO> createUser(PickLocationUsersDTO newPickLocationUsersDTO);

    ResponseEntity<PickLocationUsersDTO> findUserByUserNameAndPassword(String userName, String password);

    ResponseEntity<List<String>> getUsersByRoleAndControlUnit(int role,String controlUnit);


}
