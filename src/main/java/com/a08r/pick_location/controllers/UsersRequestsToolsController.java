package com.a08r.pick_location.controllers;

import com.a08r.pick_location.models.dto.HandasatToolsDTO;
import com.a08r.pick_location.models.dto.PickLocationDTO;
import com.a08r.pick_location.models.dto.UsersRequestsToolsDTO;
import com.a08r.pick_location.services.IHandasatToolsServices;
import com.a08r.pick_location.services.IUsersRequestsToolsServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(path = "/api/v1/users-requests-tools")
@CrossOrigin
//TODO://http://localhost:9999/pick-location/api/v1/users-requests-tools
//TODO: we use @CrossOrigin to enable CORS for this controller (this mean no need to using https://localhost:9999)
//TODO: https://documenter.getpostman.com/view/11963423/2sAXxQcrcV => POSTMAN DOCUMENTATION
public class UsersRequestsToolsController {
    private final IUsersRequestsToolsServices iUsersRequestsToolsServices;

    @Autowired
    public UsersRequestsToolsController(IUsersRequestsToolsServices iUsersRequestsToolsServices) {
        this.iUsersRequestsToolsServices = iUsersRequestsToolsServices;
    }


    //TODO://GET http://localhost:9999/pick-location/api/v1/users-requests-tools/all
    @GetMapping("/all")
    public ResponseEntity<List<UsersRequestsToolsDTO>> getAllUsersRequestsTools() {
        return this.iUsersRequestsToolsServices.getAllUsersRequestsTools();
    }


    //TODO://POST http://localhost:9999/pick-location/api/v1/users-requests-tools/create-new-request
    @PostMapping("/create-new-request")
    public ResponseEntity<UsersRequestsToolsDTO> createNewRequestTool(@RequestBody UsersRequestsToolsDTO addUsersRequestsToolsDto) {
        return this.iUsersRequestsToolsServices.createNewRequestTools(addUsersRequestsToolsDto);
    }

    //TODO://PUT http://localhost:9999/pick-location/api/v1/users-requests-tools/id/1
    @PutMapping(path = "/id/{id}")
    public ResponseEntity<UsersRequestsToolsDTO> updateRequestTools(@PathVariable Long id,
                                                         @RequestBody UsersRequestsToolsDTO usersRequestsToolsDto){
        return this.iUsersRequestsToolsServices.updateRequestTools(id, usersRequestsToolsDto);
    }
}
