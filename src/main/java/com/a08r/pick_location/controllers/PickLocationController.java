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
    //TODO://PUT http://localhost:9999/pick-location/api/v1/get-loc/is-approved/43 الراشدين، سيدي بشر قبلي، اول المنتزه، محافظة الإسكندرية 5516340
    @PutMapping(path = "/is-approved/{address}")
    public ResponseEntity<PickLocationDTO> updatePickLocationIsApproved(@PathVariable String address,
                                                                       @RequestBody PickLocationDTO pickLocationDTO){
        return this.iPickLocationServices.updatePickLocationByAddressAndTechnicalAndIsApproved(address, pickLocationDTO);
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
    //TODO://GET http://localhost:9999/pick-location/api/v1/get-loc/address/43 الراشدين، سيدي بشر قبلي، اول المنتزه، محافظة الإسكندرية 5516340/handasah/handasah
    @GetMapping(path = "/address/{address}/handasah/{handasahName}")
    public ResponseEntity<PickLocationDTO> findPickLocationByAddressAndHandasahName(@PathVariable String address, @PathVariable String handasahName){
        return this.iPickLocationServices.findPickLocationByAddressAndHandasahName(address, handasahName);
    }
    //TODO://GET http://localhost:9999/pick-location/api/v1/get-loc/handasah/handasah/technical/user/is-finished/0
    @GetMapping(path = "/handasah/{handasahName}/technical/{technicalName}/is-finished/{isFinished}")
    public ResponseEntity<PickLocationDTO> findPickLocationByAddress(@PathVariable String handasahName,
                                                                     @PathVariable String technicalName,
                                                                     @PathVariable int isFinished){
        return this.iPickLocationServices.findPickLocationByHandasahAndTechnicalAndIsFinished(handasahName, technicalName, isFinished);
    }

    //TODO://GET http://localhost:9999/pick-location/api/v1/get-loc/handasah/free/technical/free
    @GetMapping(path = "/handasah/{handasahName}/technical/{technicalName}")
    public ResponseEntity<List<PickLocationDTO>> findPickLocationByHandasahAndTechnical(@PathVariable String handasahName,
                                                                     @PathVariable String technicalName){
        return this.iPickLocationServices.findPickLocationByHandasahAndTechnical(handasahName, technicalName);
    }
    //TODO://GET http://localhost:9999/pick-location/api/v1/get-loc/handasah/handasah/is-finished/0
    @GetMapping(path = "/handasah/{handasahName}/is-finished/{isFinished}")
    public ResponseEntity<List<PickLocationDTO>> findPickLocationByHandasahAndIsFinished(@PathVariable String handasahName,
                                                                     @PathVariable int isFinished){
        return this.iPickLocationServices.findPickLocationByHandasahAndIsFinished(handasahName, isFinished);
    }


}
