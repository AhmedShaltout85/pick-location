package com.a08r.pick_location.services;

import com.a08r.pick_location.models.dto.HandasatToolsDTO;
import com.a08r.pick_location.models.dto.UsersRequestsToolsDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IUsersRequestsToolsServices {

    ResponseEntity<List<UsersRequestsToolsDTO>> getAllUsersRequestsTools();

    ResponseEntity<UsersRequestsToolsDTO> createNewRequestTools(UsersRequestsToolsDTO newUsersRequestsToolsDTO);
    ResponseEntity<UsersRequestsToolsDTO> updateRequestTools(Long id, UsersRequestsToolsDTO newUsersRequestsToolsDTO);
}
