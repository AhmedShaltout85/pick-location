package com.a08r.pick_location.models.mapper;

import com.a08r.pick_location.models.dto.PickLocationUsersDTO;
import com.a08r.pick_location.models.users.PickLocationUsersEntity;

public class PickLocationUsersMapperImpl implements IPickLocationUsersMapper {
    @Override
    public PickLocationUsersEntity pickLocationUsersDTOToPickLocationUsersEntity(PickLocationUsersDTO pickLocationUsersDTO) {
        //updated for unit testing (shouldThrowNotNullExceptionWhenLabsDTOisNull-- method)
        if (pickLocationUsersDTO == null) {
            throw new NullPointerException("pickLocationDTO should not be null");
        }
        PickLocationUsersEntity pickLocationUsersEntity = new PickLocationUsersEntity();
        pickLocationUsersEntity.setId(pickLocationUsersDTO.getId());
        pickLocationUsersEntity.setUserName(pickLocationUsersDTO.getUsername());
        pickLocationUsersEntity.setPassword(pickLocationUsersDTO.getPassword());
        pickLocationUsersEntity.setRole(pickLocationUsersDTO.getRole());
        pickLocationUsersEntity.setControlUnit(pickLocationUsersDTO.getControlUnit());
        pickLocationUsersEntity.setTechnicalId(pickLocationUsersDTO.getTechnicalId());



        return pickLocationUsersEntity;
    }

    @Override
    public PickLocationUsersDTO pickLocationUsersEntityToPickLocationUsersDTO(PickLocationUsersEntity pickLocationUsersEntity) {
        if(pickLocationUsersEntity == null){
            throw new NullPointerException("pickLocationUsersEntity should not be null");
        }

        PickLocationUsersDTO pickLocationUsersDTO = new PickLocationUsersDTO();
        pickLocationUsersDTO.setId(pickLocationUsersEntity.getId());
        pickLocationUsersDTO.setUsername(pickLocationUsersEntity.getUserName());
        pickLocationUsersDTO.setPassword(pickLocationUsersEntity.getPassword());
        pickLocationUsersDTO.setRole(pickLocationUsersEntity.getRole());
        pickLocationUsersDTO.setControlUnit(pickLocationUsersEntity.getControlUnit());
        pickLocationUsersDTO.setTechnicalId(pickLocationUsersEntity.getTechnicalId());

        return pickLocationUsersDTO;
    }
}
