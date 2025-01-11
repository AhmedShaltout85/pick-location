package com.a08r.pick_location.controllers;

import com.a08r.pick_location.models.dto.PickLocationUsersDTO;
import com.a08r.pick_location.services.IPickLocationUsersServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(path = "/api/v1/users")
@CrossOrigin
//TODO://http://localhost:9999/pick-location/api/v1/users
//TODO: we use @CrossOrigin to enable CORS for this controller (this mean no need to using https://localhost:9999)
//TODO: https://documenter.getpostman.com/view/11963423/2sAXxQcrcV => POSTMAN DOCUMENTATION
public class PickLocationUsersController {
    private final IPickLocationUsersServices iPickLocationUsersServices;

    @Autowired
    public PickLocationUsersController(IPickLocationUsersServices iPickLocationUsersServices) {
        this.iPickLocationUsersServices = iPickLocationUsersServices;
    }


    //TODO://GET http://localhost:9999/pick-location/api/v1/users/all
    @GetMapping("/all")
    public ResponseEntity<List<PickLocationUsersDTO>> getAllUsers(){
        return this.iPickLocationUsersServices.getAllUsers();
    }

    //TODO://GET http://localhost:9999/pick-location/api/v1/users/{id}
    @GetMapping(path ="/{id}" )
    public ResponseEntity<PickLocationUsersDTO> findUserByUserId(@PathVariable Long id){
        return this.iPickLocationUsersServices.findUserByUsersId(id);

    }
    //TODO://GET http://localhost:9999/pick-location/api/v1/users/{username}/{password}
    @GetMapping(path ="/{userName}/{password}" )
    public ResponseEntity<PickLocationUsersDTO> findUserByUserNameAndPassword(@PathVariable String userName, @PathVariable String password){
        return this.iPickLocationUsersServices.findUserByUserNameAndPassword(userName, password);

    }

    //TODO://POST http://localhost:9999/pick-location/api/v1/users
    @PostMapping("/create-user")
    public ResponseEntity<PickLocationUsersDTO> createUser(@RequestBody PickLocationUsersDTO addPickLocationUserDto){
        return this.iPickLocationUsersServices.createUser(addPickLocationUserDto);
    }

}
