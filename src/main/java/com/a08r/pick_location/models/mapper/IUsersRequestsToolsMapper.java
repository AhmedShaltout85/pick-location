package com.a08r.pick_location.models.mapper;

import com.a08r.pick_location.models.dto.UsersRequestsToolsDTO;
import com.a08r.pick_location.models.handasatTools.UsersRequestsToolsEntity;

public interface IUsersRequestsToolsMapper {
    UsersRequestsToolsEntity handasatToolsDTOToUsersRequestsToolsEntity(UsersRequestsToolsDTO usersRequestsToolsDTO);
    UsersRequestsToolsDTO usersRequestsToolsEntityToUsersRequestsToolsDTO(UsersRequestsToolsEntity usersRequestsToolsEntity);
}
