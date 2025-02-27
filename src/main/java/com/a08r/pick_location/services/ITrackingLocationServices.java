package com.a08r.pick_location.services;

import com.a08r.pick_location.models.dto.TrackingLocationDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ITrackingLocationServices {
    ResponseEntity<List<TrackingLocationDTO>> getTrackingLocations();
    ResponseEntity<TrackingLocationDTO> createTrackingLocation(TrackingLocationDTO newTrackingLocationDTO);

    ResponseEntity<TrackingLocationDTO> updateStartLocation(String address, TrackingLocationDTO newTrackingLocationDTO);
}
