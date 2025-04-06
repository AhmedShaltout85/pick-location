package com.a08r.pick_location.models.mapper;

import com.a08r.pick_location.models.dto.HotLineDataDTO;
import com.a08r.pick_location.models.hotline.HotLineDataEntity;


public class HotLineDataMapperImpl implements IHotLineDataMapper {

    @Override
    public HotLineDataEntity hotLineDataDTOToHotLineDataEntity(HotLineDataDTO hotLineDataDTO) {
        //updated for unit testing (shouldThrowNotNullExceptionWhenLabsDTOisNull-- method)
        if(hotLineDataDTO == null){
            throw new NullPointerException("pickLocationDTO should not be null");
        }
        HotLineDataEntity hotLineDataEntity = new HotLineDataEntity();
        hotLineDataEntity.setCustomerID(hotLineDataDTO.getCustomerID());
        hotLineDataEntity.setPhone(hotLineDataDTO.getPhone());
        hotLineDataEntity.setMobile(hotLineDataDTO.getMobile());
        hotLineDataEntity.setTitleID(hotLineDataDTO.getTitleID());
        hotLineDataEntity.setHomeNumber(hotLineDataDTO.getHomeNumber());
        hotLineDataEntity.setFloorNumberID(hotLineDataDTO.getFloorNumberID());
        hotLineDataEntity.setAreaID(hotLineDataDTO.getAreaID());
        hotLineDataEntity.setSideStreet(hotLineDataDTO.getSideStreet());
        hotLineDataEntity.setMainStreet(hotLineDataDTO.getMainStreet());
        hotLineDataEntity.setNearTO(hotLineDataDTO.getNearTO());
        hotLineDataEntity.setName(hotLineDataDTO.getName());
        hotLineDataEntity.setSubscribeNo(hotLineDataDTO.getSubscribeNo());
        hotLineDataEntity.setAreaNo(hotLineDataDTO.getAreaNo());
        hotLineDataEntity.setEmail(hotLineDataDTO.getEmail());
        hotLineDataEntity.setUserID(hotLineDataDTO.getUserID());
        hotLineDataEntity.setSourceID(hotLineDataDTO.getSourceID());
        hotLineDataEntity.setDate_Time(hotLineDataDTO.getDate_Time());
        hotLineDataEntity.setNotes(hotLineDataDTO.getNotes());
        hotLineDataEntity.setX(hotLineDataDTO.getX());
        hotLineDataEntity.setY(hotLineDataDTO.getY());
        hotLineDataEntity.setSig(hotLineDataDTO.getSig());
        hotLineDataEntity.setKey(hotLineDataDTO.getKey());

        return hotLineDataEntity;
    }

    @Override
    public HotLineDataDTO hotLineDataEntityToHotLineDataDTO(HotLineDataEntity hotLineDataEntity) {

        if(hotLineDataEntity == null){
            throw new NullPointerException("pickLocationEntity should not be null");
        }

        HotLineDataDTO hotLineDataDTO = new HotLineDataDTO();
        hotLineDataDTO.setCustomerID(hotLineDataEntity.getCustomerID());
        hotLineDataDTO.setPhone(hotLineDataEntity.getPhone());
        hotLineDataDTO.setMobile(hotLineDataEntity.getMobile());
        hotLineDataDTO.setTitleID(hotLineDataEntity.getTitleID());
        hotLineDataDTO.setHomeNumber(hotLineDataEntity.getHomeNumber());
        hotLineDataDTO.setFloorNumberID(hotLineDataEntity.getFloorNumberID());
        hotLineDataDTO.setAreaID(hotLineDataEntity.getAreaID());
        hotLineDataDTO.setSideStreet(hotLineDataEntity.getSideStreet());
        hotLineDataDTO.setMainStreet(hotLineDataEntity.getMainStreet());
        hotLineDataDTO.setNearTO(hotLineDataEntity.getNearTO());
        hotLineDataDTO.setName(hotLineDataEntity.getName());
        hotLineDataDTO.setSubscribeNo(hotLineDataEntity.getSubscribeNo());
        hotLineDataDTO.setAreaNo(hotLineDataEntity.getAreaNo());
        hotLineDataDTO.setEmail(hotLineDataEntity.getEmail());
        hotLineDataDTO.setUserID(hotLineDataEntity.getUserID());
        hotLineDataDTO.setSourceID(hotLineDataEntity.getSourceID());
        hotLineDataDTO.setDate_Time(hotLineDataEntity.getDate_Time());
        hotLineDataDTO.setNotes(hotLineDataEntity.getNotes());
        hotLineDataDTO.setX(hotLineDataEntity.getX());
        hotLineDataDTO.setY(hotLineDataEntity.getY());
        hotLineDataDTO.setSig(hotLineDataEntity.getSig());
        hotLineDataDTO.setKey(hotLineDataEntity.getKey());
        return hotLineDataDTO;
    }
}
