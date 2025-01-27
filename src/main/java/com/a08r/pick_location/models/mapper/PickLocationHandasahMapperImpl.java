package com.a08r.pick_location.models.mapper;

import com.a08r.pick_location.models.dto.PickLocationHandasahDTO;
import com.a08r.pick_location.models.dto.PickLocationUsersDTO;
import com.a08r.pick_location.models.handasah.PickLocationHandasahEntity;
import com.a08r.pick_location.models.users.PickLocationUsersEntity;

public class PickLocationHandasahMapperImpl implements IPickLocationHandasahMapper {
    @Override
    public PickLocationHandasahEntity pickLocationHandasahDTOToPickLocationHandasahEntity(PickLocationHandasahDTO pickLocationHandasahDTO) {
        //updated for unit testing (shouldThrowNotNullExceptionWhenLabsDTOisNull-- method)
        if (pickLocationHandasahDTO == null) {
            throw new NullPointerException("pickLocationDTO should not be null");
        }
        PickLocationHandasahEntity pickLocationHandasahEntity = new PickLocationHandasahEntity();
        pickLocationHandasahEntity.setId(pickLocationHandasahDTO.getId());
        pickLocationHandasahEntity.setHandasahName(pickLocationHandasahDTO.getHandasahName());
        pickLocationHandasahEntity.setStoreName(pickLocationHandasahDTO.getStoreName());
        pickLocationHandasahEntity.setStoreNumber(pickLocationHandasahDTO.getStoreNumber());


        return pickLocationHandasahEntity;
    }

    @Override
    public PickLocationHandasahDTO pickLocationHandasahEntityToPickLocationHandasahDTO(PickLocationHandasahEntity pickLocationHandasahEntity) {
        if (pickLocationHandasahEntity == null) {
            throw new NullPointerException("pickLocationUsersEntity should not be null");
        }

        PickLocationHandasahDTO pickLocationHandasahDTO = new PickLocationHandasahDTO();
        pickLocationHandasahDTO.setId(pickLocationHandasahEntity.getId());
        pickLocationHandasahDTO.setHandasahName(pickLocationHandasahEntity.getHandasahName());
        pickLocationHandasahDTO.setStoreName(pickLocationHandasahEntity.getStoreName());
        pickLocationHandasahDTO.setStoreNumber(pickLocationHandasahEntity.getStoreNumber());

        return pickLocationHandasahDTO;
    }


}
