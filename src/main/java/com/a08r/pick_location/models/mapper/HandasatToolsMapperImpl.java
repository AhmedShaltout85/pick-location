package com.a08r.pick_location.models.mapper;

import com.a08r.pick_location.models.dto.HandasatToolsDTO;
import com.a08r.pick_location.models.handasatTools.HandasatToolsEntity;

public class HandasatToolsMapperImpl implements IHandasatToolsMapper {
    @Override
    public HandasatToolsEntity handasatToolsDTOToHandasatToolsEntity(HandasatToolsDTO handasatToolsDTO) {
        //updated for unit testing (shouldThrowNotNullExceptionWhenLabsDTOisNull-- method)
        if (handasatToolsDTO == null) {
            throw new NullPointerException("pickLocationDTO should not be null");
        }
        HandasatToolsEntity handasatToolsEntity = new HandasatToolsEntity();
        handasatToolsEntity.setHandasahName(handasatToolsDTO.getHandasahName());
        handasatToolsEntity.setToolName(handasatToolsDTO.getToolName());
        handasatToolsEntity.setToolQty(handasatToolsDTO.getToolQty());
        return handasatToolsEntity;
    }

    @Override
    public HandasatToolsDTO handasatToolsEntityToHandasatToolsDTO(HandasatToolsEntity handasatToolsEntity) {
        if (handasatToolsEntity == null) {
            throw new NullPointerException("pickLocationEntity should not be null");
        }
        HandasatToolsDTO handasatToolsDTO = new HandasatToolsDTO();
        handasatToolsDTO.setHandasahName(handasatToolsEntity.getHandasahName());
        handasatToolsDTO.setToolName(handasatToolsEntity.getToolName());
        handasatToolsDTO.setToolQty(handasatToolsEntity.getToolQty());
        return handasatToolsDTO;
    }

}
