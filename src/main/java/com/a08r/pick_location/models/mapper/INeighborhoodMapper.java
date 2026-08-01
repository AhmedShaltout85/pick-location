package com.a08r.pick_location.models.mapper;

import com.a08r.pick_location.models.dto.NeighborhoodDTO;
import com.a08r.pick_location.models.neighborhood.NeighborhoodEntity;

public interface INeighborhoodMapper {
    NeighborhoodEntity neighborhoodDTOToNeighborhoodEntity(NeighborhoodDTO neighborhoodDTO);
    NeighborhoodDTO neighborhoodEntityToNeighborhoodDTO(NeighborhoodEntity neighborhoodEntity);
}
