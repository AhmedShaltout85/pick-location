package com.a08r.pick_location.models.mapper;

import com.a08r.pick_location.models.dto.SectorDTO;
import com.a08r.pick_location.models.sector.SectorEntity;

public class SectorMapperImpl implements ISectorMapper {

    @Override
    public SectorEntity sectorDTOToSectorEntity(SectorDTO sectorDTO) {
        if (sectorDTO == null) {
            throw new NullPointerException("sectorDTO should not be null");
        }
        SectorEntity sectorEntity = new SectorEntity();
        sectorEntity.setId(sectorDTO.getId());
        sectorEntity.setSectorName(sectorDTO.getSectorName());
        return sectorEntity;
    }

    @Override
    public SectorDTO sectorEntityToSectorDTO(SectorEntity sectorEntity) {
        if (sectorEntity == null) {
            throw new NullPointerException("sectorEntity should not be null");
        }
        SectorDTO sectorDTO = new SectorDTO();
        sectorDTO.setId(sectorEntity.getId());
        sectorDTO.setSectorName(sectorEntity.getSectorName());
        return sectorDTO;
    }
}
