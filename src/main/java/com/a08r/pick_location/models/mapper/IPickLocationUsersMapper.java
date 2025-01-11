package com.a08r.pick_location.models.mapper;

import com.a08r.pick_location.models.dto.PickLocationDTO;
import com.a08r.pick_location.models.dto.PickLocationUsersDTO;
import com.a08r.pick_location.models.location.PickLocationEntity;
import com.a08r.pick_location.models.users.PickLocationUsersEntity;

public interface IPickLocationUsersMapper {
    PickLocationUsersEntity pickLocationUsersDTOToPickLocationUsersEntity(PickLocationUsersDTO pickLocationUsersDTO);
    PickLocationUsersDTO pickLocationUsersEntityToPickLocationUsersDTO(PickLocationUsersEntity pickLocationUsersEntity);
}
