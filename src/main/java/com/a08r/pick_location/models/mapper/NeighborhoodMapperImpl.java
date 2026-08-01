package com.a08r.pick_location.models.mapper;

import com.a08r.pick_location.models.dto.NeighborhoodDTO;
import com.a08r.pick_location.models.neighborhood.NeighborhoodEntity;

public class NeighborhoodMapperImpl implements INeighborhoodMapper {

    @Override
    public NeighborhoodEntity neighborhoodDTOToNeighborhoodEntity(NeighborhoodDTO neighborhoodDTO) {
        if (neighborhoodDTO == null) {
            throw new NullPointerException("neighborhoodDTO should not be null");
        }
        NeighborhoodEntity neighborhoodEntity = new NeighborhoodEntity();
        neighborhoodEntity.setId(neighborhoodDTO.getId());
        neighborhoodEntity.setNeighborhoodName(neighborhoodDTO.getNeighborhoodName());
        return neighborhoodEntity;
    }

    @Override
    public NeighborhoodDTO neighborhoodEntityToNeighborhoodDTO(NeighborhoodEntity neighborhoodEntity) {
        if (neighborhoodEntity == null) {
            throw new NullPointerException("neighborhoodEntity should not be null");
        }
        NeighborhoodDTO neighborhoodDTO = new NeighborhoodDTO();
        neighborhoodDTO.setId(neighborhoodEntity.getId());
        neighborhoodDTO.setNeighborhoodName(neighborhoodEntity.getNeighborhoodName());
        return neighborhoodDTO;
    }
}
