package com.a08r.pick_location.controllers;

import com.a08r.pick_location.models.dto.PickLocationDTO;
import com.a08r.pick_location.models.dto.TrackingLocationDTO;
import com.a08r.pick_location.services.ITrackingLocationServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(path = "/api/v1/track-location")
@CrossOrigin
//TODO://http://localhost:9999/pick-location/api/v1/track-location
//TODO: we use @CrossOrigin to enable CORS for this controller (this mean no need to using https://localhost:9999)
//TODO: https://documenter.getpostman.com/view/11963423/2sAXxQcrcV => POSTMAN DOCUMENTATION
public class TrackingLocationController {
    private final ITrackingLocationServices iTrackingLocationServices;

    @Autowired
    public TrackingLocationController(ITrackingLocationServices iTrackingLocationServices) {
        this.iTrackingLocationServices = iTrackingLocationServices;
    }

    //TODO://GET http://localhost:9999/pick-location/api/v1/track-location/all
    @GetMapping("/all")
    public ResponseEntity<List<TrackingLocationDTO>> getAllTrackingLocations(){
        return this.iTrackingLocationServices.getTrackingLocations();
    }

    //TODO://POST http://localhost:9999/pick-location/api/v1/track-location
    @PostMapping
    public ResponseEntity<TrackingLocationDTO> createTrackingLocation(@RequestBody TrackingLocationDTO trackingLocationDTO){
        return this.iTrackingLocationServices.createTrackingLocation(trackingLocationDTO);
    }
    //TODO://PUT http://localhost:9999/pick-location/api/v1/track-location/address/43 الراشدين، سيدي بشر قبلي، اول المنتزه، محافظة الإسكندرية 5516340
    @PutMapping(path = "/address/{address}")
    public ResponseEntity<TrackingLocationDTO> updateTrackingLocationByAddress(@PathVariable String address,
                                                                       @RequestBody TrackingLocationDTO trackingLocationDTO){
        return this.iTrackingLocationServices.updateStartLocation(address, trackingLocationDTO);
    }

    //TODO://GET http://localhost:9999/pick-location/api/v1/track-location/address/2second_address/tech-name/user
    @GetMapping(path = "/address/{address}/tech-name/{tech}")
    public ResponseEntity<TrackingLocationDTO> findTrackingLocationByAddressAndTechName(@PathVariable String address, @PathVariable String tech){
        return this.iTrackingLocationServices.getTrackingLocationByAddressAndTechnicalName(address, tech);
    }

}
