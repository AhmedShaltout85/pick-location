package com.a08r.pick_location.models.mapper;

import com.a08r.pick_location.models.dto.PickLocationDTO;
import com.a08r.pick_location.models.location.PickLocationEntity;

public class PickLocationMapperImpl implements IPickLocationMapper {
    @Override
    public PickLocationEntity pickLocationDTOToPickLocationEntity(PickLocationDTO pickLocationDTO) {
        //updated for unit testing (shouldThrowNotNullExceptionWhenLabsDTOisNull-- method)
        if(pickLocationDTO == null){
            throw new NullPointerException("pickLocationDTO should not be null");
        }
        PickLocationEntity pickLocationEntity = new PickLocationEntity();
        pickLocationDTO.setId(pickLocationEntity.getId());
        pickLocationEntity.setAddress(pickLocationDTO.getAddress());
        pickLocationEntity.setLatitude(pickLocationDTO.getLatitude());
        pickLocationEntity.setLongitude(pickLocationDTO.getLongitude());
        pickLocationEntity.setFlag(pickLocationDTO.getFlag());
        pickLocationEntity.setReal_address(pickLocationDTO.getReal_address());

        return pickLocationEntity;
    }

    @Override
    public PickLocationDTO pickLocationEntityToPickLocationDTO(PickLocationEntity pickLocationEntity) {

        if(pickLocationEntity == null){
            throw new NullPointerException("pickLocationEntity should not be null");
        }

        PickLocationDTO pickLocationDTO = new PickLocationDTO();
        pickLocationDTO.setId(pickLocationEntity.getId());
        pickLocationDTO.setAddress(pickLocationEntity.getAddress());
        pickLocationDTO.setLatitude(pickLocationEntity.getLatitude());
        pickLocationDTO.setLongitude(pickLocationEntity.getLongitude());
        pickLocationDTO.setFlag(pickLocationEntity.getFlag());
        pickLocationDTO.setReal_address(pickLocationEntity.getReal_address());

        return pickLocationDTO;

    }
}
