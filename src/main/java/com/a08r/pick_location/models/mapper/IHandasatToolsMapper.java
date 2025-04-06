package com.a08r.pick_location.models.mapper;

import com.a08r.pick_location.models.dto.HandasatToolsDTO;
import com.a08r.pick_location.models.handasatTools.HandasatToolsEntity;

public interface IHandasatToolsMapper {

    HandasatToolsEntity handasatToolsDTOToHandasatToolsEntity(HandasatToolsDTO handasatToolsDTO);
    HandasatToolsDTO handasatToolsEntityToPickLocationDTO(HandasatToolsEntity handasatToolsEntity);
}
