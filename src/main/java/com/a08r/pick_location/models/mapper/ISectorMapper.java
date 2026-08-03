package com.a08r.pick_location.models.mapper;

import com.a08r.pick_location.models.dto.SectorDTO;
import com.a08r.pick_location.models.sector.SectorEntity;

public interface ISectorMapper {
    SectorEntity sectorDTOToSectorEntity(SectorDTO sectorDTO);
    SectorDTO sectorEntityToSectorDTO(SectorEntity sectorEntity);
}
