package com.a08r.pick_location.controllers;

import com.a08r.pick_location.models.dto.PickLocationDTO;
import com.a08r.pick_location.services.IPickLocationServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;



@RestController
@RequestMapping(path = "/api/v1/get-loc")
@CrossOrigin
//TODO://http://localhost:9999/pick-location/api/v1/get-loc
//TODO: we use @CrossOrigin to enable CORS for this controller (this mean no need to using https://localhost:9999)
//TODO: https://documenter.getpostman.com/view/11963423/2sAXxQcrcV => POSTMAN DOCUMENTATION
public class PickLocationController {
    private final IPickLocationServices iPickLocationServices;

    @Autowired
    public PickLocationController(IPickLocationServices iPickLocationServices) {
        this.iPickLocationServices = iPickLocationServices;
    }
    //TODO://GET http://localhost:9999/pick-location/api/v1/get-loc/filter
    @GetMapping("/filter")
    public ResponseEntity<List<PickLocationDTO>> getAddress(){
        return this.iPickLocationServices.getAddress();
    }
    //TODO://GET http://localhost:9999/pick-location/api/v1/get-loc/flag/0
    @GetMapping(path = "/flag/{flag}")
    public ResponseEntity<List<PickLocationDTO>> getPickLocationByFlag(@PathVariable int flag){
        return this.iPickLocationServices.getPickLocationByFlag(flag);
    }

    //TODO://POST http://localhost:9999/pick-location/api/v1/get-loc
    @PostMapping
    public ResponseEntity<PickLocationDTO> createAddress(@RequestBody PickLocationDTO pickLocationDTO){
        return this.iPickLocationServices.createAddress(pickLocationDTO);
    }

    //TODO://PUT http://localhost:9999/pick-location/api/v1/get-loc/id/1
    @PutMapping(path = "/id/{id}")
    public ResponseEntity<PickLocationDTO> updateAddress(@PathVariable Long id,
                                                         @RequestBody PickLocationDTO pickLocationDTO){
        return this.iPickLocationServices.updateAddress(id, pickLocationDTO);
    }

    //TODO://PUT http://localhost:9999/pick-location/api/v1/get-loc/address/43 الراشدين، سيدي بشر قبلي، اول المنتزه، محافظة الإسكندرية 5516340
    @PutMapping(path = "/address/{address}")
    public ResponseEntity<PickLocationDTO> updatePickLocationByAddress(@PathVariable String address,
                                                                       @RequestBody PickLocationDTO pickLocationDTO){
        return this.iPickLocationServices.updatePickLocationByAddress(address, pickLocationDTO);
    }
}
