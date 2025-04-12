package com.a08r.pick_location.models.mapper;

import com.a08r.pick_location.models.dto.HotLineDTO;
import com.a08r.pick_location.models.hotline.HotLineDataEntity;
import com.a08r.pick_location.models.hotline.HotLineEntity;

public interface IHotLineMapper {
    HotLineEntity hotLineDTOToHotLineEntity(HotLineDTO hotLineDTO);
    HotLineDTO hotLineEntityToHotLineDTO(HotLineEntity hotLineEntity);
}
