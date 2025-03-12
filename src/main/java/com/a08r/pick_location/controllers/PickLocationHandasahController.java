package com.a08r.pick_location.controllers;

import com.a08r.pick_location.models.dto.PickLocationHandasahDTO;
import com.a08r.pick_location.models.dto.PickLocationUsersDTO;
import com.a08r.pick_location.services.IPickLocationHandasahServices;
import com.a08r.pick_location.services.IPickLocationUsersServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(path = "/api/v1/handasah")
@CrossOrigin
//TODO://http://localhost:9999/pick-location/api/v1/users
//TODO: we use @CrossOrigin to enable CORS for this controller (this mean no need to using https://localhost:9999)
//TODO: https://documenter.getpostman.com/view/11963423/2sAXxQcrcV => POSTMAN DOCUMENTATION
public class PickLocationHandasahController {
    private final IPickLocationHandasahServices iPickLocationHandasahServices;

    @Autowired
    public PickLocationHandasahController(IPickLocationHandasahServices iPickLocationHandasahServices) {
        this.iPickLocationHandasahServices = iPickLocationHandasahServices;
    }


    //TODO://GET http://localhost:9999/pick-location/api/v1/handasah/all
    @GetMapping("/all")
    public ResponseEntity<List<String>> getAllHandasah(){
        return this.iPickLocationHandasahServices.getAllHandasah();
    }

    //TODO://GET http://localhost:9999/pick-location/api/v1/handasah/{id}
    @GetMapping(path ="/{id}" )
    public ResponseEntity<PickLocationHandasahDTO> findHandasahById(@PathVariable Long id){
        return this.iPickLocationHandasahServices.findHandasahById(id);

    }


    //TODO://POST http://localhost:9999/pick-location/api/v1/handasah
    @PostMapping("/create-handasah")
    public ResponseEntity<PickLocationHandasahDTO> createHandasah(@RequestBody PickLocationHandasahDTO addPickLocationHandasahDto){
        return this.iPickLocationHandasahServices.createHandasah(addPickLocationHandasahDto);
    }
    //TODO://GET http://localhost:9999/pick-location/api/v1/handasah/handasah-name/{handasahName}
    //TODO://GET http://localhost:9999/pick-location/api/v1/handasah/handasah-name/handasah
    @GetMapping(path ="/handasah-name/{handasahName}" )
    public ResponseEntity<PickLocationHandasahDTO> findStoreNameByHandasahName(@PathVariable String handasahName){
        return this.iPickLocationHandasahServices.getStoreName(handasahName);

    }


}
