package com.a08r.pick_location.services;

import com.a08r.pick_location.errors.RecordNotFoundException;
import com.a08r.pick_location.models.dto.PickLocationDTO;
import com.a08r.pick_location.models.dto.TrackingLocationDTO;
import com.a08r.pick_location.models.location.PickLocationEntity;
import com.a08r.pick_location.models.location.TrackingLocationEntity;
import com.a08r.pick_location.models.mapper.ITrackingLocationMapper;
import com.a08r.pick_location.models.mapper.TrackingLocationMapperImpl;
import com.a08r.pick_location.repositores.ITracingLocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TrackingLocationServicesImpl implements ITrackingLocationServices{
    private final ITracingLocationRepository iTracingLocationRepository;
    private static final ITrackingLocationMapper I_TRACKING_LOCATION_MAPPER = new TrackingLocationMapperImpl();

    @Autowired
    public TrackingLocationServicesImpl(ITracingLocationRepository iTracingLocationRepository) {
        this.iTracingLocationRepository = iTracingLocationRepository;
    }

    @Override
    public ResponseEntity<List<TrackingLocationDTO>> getTrackingLocations() {
        List<TrackingLocationEntity> trackingLocationEntityList = iTracingLocationRepository.findAll();
        List<TrackingLocationDTO> trackingLocationDTOList = trackingLocationEntityList
                .stream()
                .map(I_TRACKING_LOCATION_MAPPER::trackingLocationEntityToTrackingLocationDTO)
                .collect(Collectors.toList());
        if (trackingLocationDTOList.isEmpty()) {
            throw new RecordNotFoundException("Sorry, No DATA Found!...");
        }
        return new ResponseEntity<>(trackingLocationDTOList, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<TrackingLocationDTO> createTrackingLocation(TrackingLocationDTO newTrackingLocationDTO) {
        final TrackingLocationEntity trackingLocationEntity = I_TRACKING_LOCATION_MAPPER.trackingLocationDTOToTrackingLocationEntity(newTrackingLocationDTO);
        final TrackingLocationEntity createTrackingLocationEntity = this.iTracingLocationRepository.save(trackingLocationEntity);
        TrackingLocationDTO pickLocationDTO = I_TRACKING_LOCATION_MAPPER.trackingLocationEntityToTrackingLocationDTO(createTrackingLocationEntity);
        return new ResponseEntity<>(pickLocationDTO, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<TrackingLocationDTO> updateStartLocation(String address, TrackingLocationDTO newTrackingLocationDTO) {
        Optional<TrackingLocationEntity>  trackingLocationEntity = iTracingLocationRepository.findByAddress(address);
        if (trackingLocationEntity.isEmpty()) {
            throw new RecordNotFoundException("the item with address: " + address + " not found!...");
        }
        TrackingLocationEntity exitingTrackingLocationEntity = trackingLocationEntity.get();
        exitingTrackingLocationEntity.setCurrentLatitude(newTrackingLocationDTO.getCurrentLatitude());
        exitingTrackingLocationEntity.setCurrentLongitude(newTrackingLocationDTO.getCurrentLongitude());
        TrackingLocationEntity updateTrackingLocationEntity = this.iTracingLocationRepository.save(exitingTrackingLocationEntity);
        TrackingLocationDTO trackingLocationDTO = I_TRACKING_LOCATION_MAPPER.trackingLocationEntityToTrackingLocationDTO(updateTrackingLocationEntity);
        return new ResponseEntity<>(trackingLocationDTO, HttpStatus.OK);
    }
}
