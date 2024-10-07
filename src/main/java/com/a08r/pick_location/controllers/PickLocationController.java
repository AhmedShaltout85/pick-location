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
public class PickLocationController {
    private final IPickLocationServices iPickLocationServices;

    @Autowired
    public PickLocationController(IPickLocationServices iPickLocationServices) {
        this.iPickLocationServices = iPickLocationServices;
    }

    @GetMapping("/filter")
    public ResponseEntity<List<PickLocationDTO>> getAddress(){
        return this.iPickLocationServices.getAddress();
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<PickLocationDTO> updateAddress(@PathVariable Long id,
                                                         @RequestBody PickLocationDTO pickLocationDTO){
        return this.iPickLocationServices.updateAddress(id, pickLocationDTO);
    }

    @GetMapping(path = "/flag/{flag}")
    public ResponseEntity<List<PickLocationDTO>> getPickLocationByFlag(@PathVariable int flag){
        return this.iPickLocationServices.getPickLocationByFlag(flag);
    }
}
