package com.a08r.pick_location.services;

import com.a08r.pick_location.models.dto.HandasatToolsDTO;
import com.a08r.pick_location.models.dto.UsersRequestsToolsDTO;
import com.a08r.pick_location.models.handasatTools.UsersRequestsToolsEntity;
import com.a08r.pick_location.models.location.PickLocationEntity;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface IUsersRequestsToolsServices {

    ResponseEntity<List<UsersRequestsToolsDTO>> getAllUsersRequestsTools();

    ResponseEntity<UsersRequestsToolsDTO> createNewRequestTools(UsersRequestsToolsDTO newUsersRequestsToolsDTO);
    ResponseEntity<UsersRequestsToolsDTO> updateRequestTools(Long id, UsersRequestsToolsDTO newUsersRequestsToolsDTO);
    ResponseEntity<UsersRequestsToolsDTO> updateRequestToolsByAddress(String address, UsersRequestsToolsDTO newUsersRequestsToolsDTO);
    ResponseEntity<List<UsersRequestsToolsDTO>> findByHandasahNameAndAddressAndRequestStatus(String handasahName, int requestStatus, String address);
    ResponseEntity<List<UsersRequestsToolsDTO>> findByHandasahNameAndAddressAndTechName(String handasahName, String techName, String address);

}
