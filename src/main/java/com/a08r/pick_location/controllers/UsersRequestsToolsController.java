package com.a08r.pick_location.controllers;

import com.a08r.pick_location.models.dto.PickLocationDTO;
import com.a08r.pick_location.models.dto.UsersRequestsToolsDTO;
import com.a08r.pick_location.services.IUsersRequestsToolsServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(path = "/api/v1/users-requests-tools")
@CrossOrigin(origins ="*" )  // Allow CORS for Flutter Web
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
                                                                    @RequestBody UsersRequestsToolsDTO usersRequestsToolsDto) {
        return this.iUsersRequestsToolsServices.updateRequestTools(id, usersRequestsToolsDto);
    }

    //TODO://PUT http://localhost:9999/pick-location/api/v1/users-requests-tools/address/شارع محمد صالح أبو يوسف طريق الحرية أبوقير أمام قسم الرمل أول الاسكندرية/tool-name/سباك
    @PutMapping(path = "/address/{address}/tool-name/{toolName}")
    public ResponseEntity<UsersRequestsToolsDTO> updateUserRequestByAddress(@PathVariable String address,
                                                                            @PathVariable String toolName,
                                                                            @RequestBody UsersRequestsToolsDTO usersRequestsToolsDTO) {
        return this.iUsersRequestsToolsServices.updateRequestToolsByAddressAndToolName(address,toolName, usersRequestsToolsDTO);
    }

    //TODO://GET http://localhost:9999/pick-location/api/v1/users-requests-tools/handasah/هندسة فرع الرمل/address/فمنج امام قسم الرمل اول/requestStatus/1
    @GetMapping(path = "/handasah/{handasahName}/address/{address}/requestStatus/{requestStatus}")
    public ResponseEntity<List<UsersRequestsToolsDTO>> findByHandasahNameAndAddressAndRequestStatus(@PathVariable String handasahName,
                                                                                                    @PathVariable String address,
                                                                                                    @PathVariable int requestStatus) {
        return this.iUsersRequestsToolsServices.findByHandasahNameAndAddressAndRequestStatus(handasahName, requestStatus, address);
    }
    //TODO://GET http://localhost:9999/pick-location/api/v1/users-requests-tools/handasah/هندسة فرع الرمل/address/فمنج امام قسم الرمل اول/tech-name/user1
    @GetMapping(path = "/handasah/{handasahName}/address/{address}/tech-name/{techName}")
    public ResponseEntity<List<UsersRequestsToolsDTO>> findByHandasahNameAndAddressAndTechName(@PathVariable String handasahName,
                                                                                                    @PathVariable String address,
                                                                                                    @PathVariable String techName) {
        return this.iUsersRequestsToolsServices.findByHandasahNameAndAddressAndTechName(handasahName, techName, address);
    }
}
