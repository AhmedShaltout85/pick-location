package com.a08r.pick_location.models.mapper;

import com.a08r.pick_location.models.dto.PickLocationDTO;
import com.a08r.pick_location.models.location.PickLocationEntity;

public interface IPickLocationMapper {

    PickLocationEntity pickLocationDTOToPickLocationEntity(PickLocationDTO pickLocationDTO);
    PickLocationDTO pickLocationEntityToPickLocationDTO(PickLocationEntity pickLocationEntity);
}
