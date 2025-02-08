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
    //TODO://GET http://localhost:9999/pick-location/api/v1/get-loc/flag/1/is-finished/0
    @GetMapping(path = "/flag/{flag}/is-finished/{is_finished}")
    public ResponseEntity<List<PickLocationDTO>> getPickLocationByFlagAndIsFinished(@PathVariable int flag, @PathVariable int is_finished){
        return this.iPickLocationServices.getPickLocationByFlagAndIsFinished(flag, is_finished);
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
    //TODO://PUT http://localhost:9999/pick-location/api/v1/get-loc/handasah/43 الراشدين، سيدي بشر قبلي، اول المنتزه، محافظة الإسكندرية 5516340
    @PutMapping(path = "/handasah/{address}")
    public ResponseEntity<PickLocationDTO> updatePickLocationHandasah(@PathVariable String address,
                                                                       @RequestBody PickLocationDTO pickLocationDTO){
        return this.iPickLocationServices.updatePickLocationHandasah(address, pickLocationDTO);
    }
    //TODO://PUT http://localhost:9999/pick-location/api/v1/get-loc/technical/43 الراشدين، سيدي بشر قبلي، اول المنتزه، محافظة الإسكندرية 5516340
    @PutMapping(path = "/technical/{address}")
    public ResponseEntity<PickLocationDTO> updatePickLocationTechnicalUser(@PathVariable String address,
                                                                       @RequestBody PickLocationDTO pickLocationDTO){
        return this.iPickLocationServices.updatePickLocationTechnicalUser(address, pickLocationDTO);
    }
    //TODO://PUT http://localhost:9999/pick-location/api/v1/get-loc/is-finished/43 الراشدين، سيدي بشر قبلي، اول المنتزه، محافظة الإسكندرية 5516340
    @PutMapping(path = "/is-finished/{address}")
    public ResponseEntity<PickLocationDTO> updatePickLocationIsFinished(@PathVariable String address,
                                                                       @RequestBody PickLocationDTO pickLocationDTO){
        return this.iPickLocationServices.updatePickLocationIsFinished(address, pickLocationDTO);
    }

    //TODO://GET http://localhost:9999/pick-location/api/v1/get-loc/flag/0/address/43 الراشدين، سيدي بشر قبلي، اول المنتزه، محافظة الإسكندرية 5516340
    @GetMapping(path = "/flag/{flag}/address/{address}")
    public ResponseEntity<PickLocationDTO> findPickLocationByAddressAndFlag(@PathVariable String address, @PathVariable int flag){
        return this.iPickLocationServices.findPickLocationByAddressAndFlag(address, flag);
    }

    //TODO://GET http://localhost:9999/pick-location/api/v1/get-loc/address/43 الراشدين، سيدي بشر قبلي، اول المنتزه، محافظة الإسكندرية 5516340
    @GetMapping(path = "/address/{address}")
    public ResponseEntity<PickLocationDTO> findPickLocationByAddress(@PathVariable String address){
        return this.iPickLocationServices.findPickLocationByAddress(address);
    }
}
