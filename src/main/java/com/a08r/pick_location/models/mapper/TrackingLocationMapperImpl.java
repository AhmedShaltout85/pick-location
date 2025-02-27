package com.a08r.pick_location.models.mapper;

import com.a08r.pick_location.models.dto.TrackingLocationDTO;
import com.a08r.pick_location.models.location.TrackingLocationEntity;

public class TrackingLocationMapperImpl implements ITrackingLocationMapper {
    @Override
    public TrackingLocationEntity trackingLocationDTOToTrackingLocationEntity(TrackingLocationDTO trackingLocationDTO) {
        //updated for unit testing (shouldThrowNotNullExceptionWhenLabsDTOisNull-- method)
        if(trackingLocationDTO == null){
            throw new NullPointerException("pickLocationDTO should not be null");
        }
        TrackingLocationEntity trackingLocationEntity = new TrackingLocationEntity();
        trackingLocationEntity.setId(trackingLocationDTO.getId());
        //
        trackingLocationEntity.setAddress(trackingLocationDTO.getAddress());
        trackingLocationEntity.setLatitude(trackingLocationDTO.getLatitude());
        trackingLocationEntity.setLongitude(trackingLocationDTO.getLongitude());
        trackingLocationEntity.setTechnicalName(trackingLocationDTO.getTechnicalName());
        trackingLocationEntity.setStartLatitude(trackingLocationDTO.getStartLatitude());
        trackingLocationEntity.setStartLongitude(trackingLocationDTO.getStartLongitude());
        trackingLocationEntity.setCurrentLatitude(trackingLocationDTO.getCurrentLatitude());
        trackingLocationEntity.setCurrentLongitude(trackingLocationDTO.getCurrentLongitude());

        return trackingLocationEntity;
    }

    @Override
    public TrackingLocationDTO trackingLocationEntityToTrackingLocationDTO(TrackingLocationEntity trackingLocationEntity) {
        if(trackingLocationEntity == null){
            throw new NullPointerException("pickLocationEntity should not be null");
        }

        TrackingLocationDTO trackingLocationDTO = new TrackingLocationDTO();
        trackingLocationDTO.setId(trackingLocationEntity.getId());
        //
        trackingLocationDTO.setAddress(trackingLocationEntity.getAddress());
        trackingLocationDTO.setLatitude(trackingLocationEntity.getLatitude());
        trackingLocationDTO.setLongitude(trackingLocationEntity.getLongitude());
        trackingLocationDTO.setTechnicalName(trackingLocationEntity.getTechnicalName());
        trackingLocationDTO.setStartLatitude(trackingLocationEntity.getStartLatitude());
        trackingLocationDTO.setStartLongitude(trackingLocationEntity.getStartLongitude());
        trackingLocationDTO.setCurrentLatitude(trackingLocationEntity.getCurrentLatitude());
        trackingLocationDTO.setCurrentLongitude(trackingLocationEntity.getCurrentLongitude());

        return trackingLocationDTO;
    }
}
