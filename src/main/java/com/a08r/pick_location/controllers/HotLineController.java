package com.a08r.pick_location.controllers;

import com.a08r.pick_location.models.dto.HotLineDTO;
import com.a08r.pick_location.models.dto.HotLineDataDTO;
import com.a08r.pick_location.models.dto.PickLocationDTO;
import com.a08r.pick_location.services.IHotLineDataServices;
import com.a08r.pick_location.services.IHotLineServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(path = "/api/v1/hot-address")
@CrossOrigin(origins ="*" )  // Allow CORS for Flutter Web
//TODO://http://localhost:9999/pick-location/api/v1/hot-address
//TODO: we use @CrossOrigin to enable CORS for this controller (this mean no need to using https://localhost:9999)
//TODO: https://documenter.getpostman.com/view/11963423/2sAXxQcrcV => POSTMAN DOCUMENTATION
public class HotLineController {
    private final IHotLineServices iHotLineServices;

    @Autowired
    public HotLineController(IHotLineServices iHotLineServices) {
        this.iHotLineServices = iHotLineServices;
    }


    //TODO://GET http://localhost:9999/pick-location/api/v1/hot-address/all
    @GetMapping("/all")
    public ResponseEntity<List<HotLineDTO>> getAll() {
        return this.iHotLineServices.getHotLineAll();
    }



    //TODO://POST http://localhost:9999/pick-location/api/v1/hot-address/create
    @PostMapping("/create")
    public ResponseEntity<HotLineDTO> createHotLineAddress(@RequestBody HotLineDTO addHotLineDto) {
        return this.iHotLineServices.createHotLineAddress(addHotLineDto);
    }

    //TODO://GET http://localhost:9999/pick-location/api/v1/hot-address/address/43 الراشدين، سيدي بشر قبلي، اول المنتزه، محافظة الإسكندرية 5516340
    @GetMapping(path = "/address/{address}")
    public ResponseEntity<HotLineDTO> findHotLineByAddress(@PathVariable String address){
        return this.iHotLineServices.findHotLineByAddress(address);
    }

    //TODO://PUT http://localhost:9999/pick-location/api/v1/hot-address/address/43 الراشدين، سيدي بشر قبلي، اول المنتزه، محافظة الإسكندرية 5516340
    @PutMapping(path = "/address/{address}")
    public ResponseEntity<HotLineDTO> updateHotLineByAddress(@PathVariable String address,
                                                                       @RequestBody HotLineDTO hotLineDTO){
        return this.iHotLineServices.updateHotLineByAddress(address, hotLineDTO);
    }

    //TODO://GET http://localhost:9999/pick-location/api/v1/hot-address/id/6
    @GetMapping(path ="/id/{id}" )
    public ResponseEntity<HotLineDTO> findById(@PathVariable Long id){
        return this.iHotLineServices.findById(id);

    }

}
