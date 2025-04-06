package com.a08r.pick_location.models.mapper;

import com.a08r.pick_location.models.dto.HotLineDataDTO;
import com.a08r.pick_location.models.hotline.HotLineDataEntity;

public interface IHotLineDataMapper {
    HotLineDataEntity hotLineDataDTOToHotLineDataEntity(HotLineDataDTO hotLineDataDTO);
    HotLineDataDTO hotLineDataEntityToHotLineDataDTO(HotLineDataEntity hotLineDataEntity);
}
