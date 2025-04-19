package com.a08r.pick_location.controllers;

import com.a08r.pick_location.models.dto.HotLineDataDTO;
import com.a08r.pick_location.models.dto.PickLocationUsersDTO;
import com.a08r.pick_location.services.IHotLineDataServices;
import com.a08r.pick_location.services.IPickLocationUsersServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(path = "/api/v1/hot-line")
@CrossOrigin(origins ="*" )  // Allow CORS for Flutter Web
//TODO://http://localhost:9999/pick-location/api/v1/hot-line
//TODO: we use @CrossOrigin to enable CORS for this controller (this mean no need to using https://localhost:9999)
//TODO: https://documenter.getpostman.com/view/11963423/2sAXxQcrcV => POSTMAN DOCUMENTATION
public class HotLineDataController {
    private final IHotLineDataServices iHotLineDataServices;

    @Autowired
    public HotLineDataController(IHotLineDataServices iHotLineDataServices) {
        this.iHotLineDataServices = iHotLineDataServices;
    }


    //TODO://GET http://localhost:9999/pick-location/api/v1/hot-line/all
    @GetMapping("/all")
    public ResponseEntity<List<HotLineDataDTO>> getAllUsers() {
        return this.iHotLineDataServices.getHotLineAllData();
    }



    //TODO://POST http://localhost:9999/pick-location/api/v1/hot-line/create-address
    @PostMapping("/create-address")
    public ResponseEntity<HotLineDataDTO> createHotLineAddress(@RequestBody HotLineDataDTO addHotLineDataDto) {
        return this.iHotLineDataServices.createHotLineAddress(addHotLineDataDto);
    }


}
