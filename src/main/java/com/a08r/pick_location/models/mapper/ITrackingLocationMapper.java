package com.a08r.pick_location.models.mapper;

import com.a08r.pick_location.models.dto.TrackingLocationDTO;
import com.a08r.pick_location.models.location.TrackingLocationEntity;

public interface ITrackingLocationMapper {
   TrackingLocationEntity trackingLocationDTOToTrackingLocationEntity(TrackingLocationDTO trackingLocationDTO);
   TrackingLocationDTO trackingLocationEntityToTrackingLocationDTO(TrackingLocationEntity trackingLocationEntity);
}
