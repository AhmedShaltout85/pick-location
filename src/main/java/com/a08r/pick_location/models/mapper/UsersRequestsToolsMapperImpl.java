package com.a08r.pick_location.models.mapper;

import com.a08r.pick_location.models.dto.HandasatToolsDTO;
import com.a08r.pick_location.models.dto.UsersRequestsToolsDTO;
import com.a08r.pick_location.models.handasatTools.HandasatToolsEntity;
import com.a08r.pick_location.models.handasatTools.UsersRequestsToolsEntity;

public class UsersRequestsToolsMapperImpl implements IUsersRequestsToolsMapper {
    @Override
    public UsersRequestsToolsEntity handasatToolsDTOToUsersRequestsToolsEntity(UsersRequestsToolsDTO usersRequestsToolsDTO) {

        //updated for unit testing (shouldThrowNotNullExceptionWhenLabsDTOisNull-- method)
        if (usersRequestsToolsDTO == null) {
            throw new NullPointerException("pickLocationDTO should not be null");
        }
        UsersRequestsToolsEntity usersRequestsToolsEntity = new UsersRequestsToolsEntity();
        usersRequestsToolsEntity.setHandasahName(usersRequestsToolsDTO.getHandasahName());
        usersRequestsToolsEntity.setToolName(usersRequestsToolsDTO.getToolName());
        usersRequestsToolsEntity.setToolQty(usersRequestsToolsDTO.getToolQty());
        usersRequestsToolsEntity.setTechName(usersRequestsToolsDTO.getTechName());
        usersRequestsToolsEntity.setDate(usersRequestsToolsDTO.getDate());
        usersRequestsToolsEntity.setRequestStatus(usersRequestsToolsDTO.getRequestStatus());
        usersRequestsToolsEntity.setIsApproved(usersRequestsToolsDTO.getIsApproved());
        usersRequestsToolsEntity.setAddress(usersRequestsToolsDTO.getAddress());


        return usersRequestsToolsEntity;
    }

    @Override
    public UsersRequestsToolsDTO usersRequestsToolsEntityToUsersRequestsToolsDTO(UsersRequestsToolsEntity usersRequestsToolsEntity) {

        if (usersRequestsToolsEntity == null) {
            throw new NullPointerException("pickLocationEntity should not be null");
        }
        UsersRequestsToolsDTO usersRequestsToolsDTO = new UsersRequestsToolsDTO();
        usersRequestsToolsDTO.setHandasahName(usersRequestsToolsEntity.getHandasahName());
        usersRequestsToolsDTO.setToolName(usersRequestsToolsEntity.getToolName());
        usersRequestsToolsDTO.setToolQty(usersRequestsToolsEntity.getToolQty());
        usersRequestsToolsDTO.setTechName(usersRequestsToolsEntity.getTechName());
        usersRequestsToolsDTO.setDate(usersRequestsToolsEntity.getDate());
        usersRequestsToolsDTO.setRequestStatus(usersRequestsToolsEntity.getRequestStatus());
        usersRequestsToolsDTO.setIsApproved(usersRequestsToolsEntity.getIsApproved());
        usersRequestsToolsDTO.setAddress(usersRequestsToolsEntity.getAddress());
        return usersRequestsToolsDTO;
    }


}
