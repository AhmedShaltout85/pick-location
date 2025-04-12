package com.a08r.pick_location.models.mapper;

import com.a08r.pick_location.models.dto.HotLineDTO;
import com.a08r.pick_location.models.dto.HotLineDataDTO;
import com.a08r.pick_location.models.hotline.HotLineDataEntity;
import com.a08r.pick_location.models.hotline.HotLineEntity;


public class HotLineMapperImpl implements IHotLineMapper {

    @Override
    public HotLineEntity hotLineDTOToHotLineEntity(HotLineDTO hotLineDTO) {
        //updated for unit testing (shouldThrowNotNullExceptionWhenLabsDTOisNull-- method)
        if(hotLineDTO == null){
            throw new NullPointerException("pickLocationDTO should not be null");
        }
        HotLineEntity hotLineEntity = new HotLineEntity();  // hotLineEntity
        hotLineEntity.setId(hotLineDTO.getId()); //
        hotLineEntity.setCaseReportDateTime(hotLineDTO.getCaseReportDateTime());
        hotLineEntity.setFinalClosed(hotLineDTO.isFinalClosed());
        hotLineEntity.setReporterName(hotLineDTO.getReporterName());
        hotLineEntity.setStreet(hotLineDTO.getStreet());
        hotLineEntity.setMainStreet(hotLineDTO.getMainStreet());
        hotLineEntity.setX(hotLineDTO.getX());
        hotLineEntity.setY(hotLineDTO.getY());


        return hotLineEntity;
    }

    @Override
    public HotLineDTO hotLineEntityToHotLineDTO(HotLineEntity hotLineEntity) {

        if(hotLineEntity == null){
            throw new NullPointerException("pickLocationEntity should not be null");
        }

        HotLineDTO hotLineDTO = new HotLineDTO();
        hotLineDTO.setId(hotLineEntity.getId());
        hotLineDTO.setCaseReportDateTime(hotLineEntity.getCaseReportDateTime());
        hotLineDTO.setFinalClosed(hotLineEntity.isFinalClosed());
        hotLineDTO.setReporterName(hotLineEntity.getReporterName());
        hotLineDTO.setStreet(hotLineEntity.getStreet());
        hotLineDTO.setMainStreet(hotLineEntity.getMainStreet());
        hotLineDTO.setX(hotLineEntity.getX());
        hotLineDTO.setY(hotLineEntity.getY());

        return hotLineDTO;
    }
}
