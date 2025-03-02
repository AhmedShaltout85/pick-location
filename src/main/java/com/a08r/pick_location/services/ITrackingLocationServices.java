package com.a08r.pick_location.services;

import com.a08r.pick_location.models.dto.PickLocationDTO;
import com.a08r.pick_location.models.dto.TrackingLocationDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ITrackingLocationServices {
    ResponseEntity<List<TrackingLocationDTO>> getTrackingLocations();
    ResponseEntity<TrackingLocationDTO> createTrackingLocation(TrackingLocationDTO newTrackingLocationDTO);

    ResponseEntity<TrackingLocationDTO> updateStartLocation(String address, TrackingLocationDTO newTrackingLocationDTO);
    ResponseEntity<TrackingLocationDTO> getTrackingLocationByAddressAndTechnicalName(String address, String technicalName);

    ResponseEntity<TrackingLocationDTO> findTrackingLocationByAddress(String address);
    ResponseEntity<TrackingLocationDTO> updateTrackingLocationByAddress(String address, TrackingLocationDTO newTrackingLocationDTO);


}
