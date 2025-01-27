package com.a08r.pick_location.models.mapper;

import com.a08r.pick_location.models.dto.PickLocationHandasahDTO;
import com.a08r.pick_location.models.dto.PickLocationUsersDTO;
import com.a08r.pick_location.models.handasah.PickLocationHandasahEntity;
import com.a08r.pick_location.models.users.PickLocationUsersEntity;

public interface IPickLocationHandasahMapper {
    PickLocationHandasahEntity pickLocationHandasahDTOToPickLocationHandasahEntity(PickLocationHandasahDTO pickLocationHandasahDTO);
    PickLocationHandasahDTO pickLocationHandasahEntityToPickLocationHandasahDTO(PickLocationHandasahEntity pickLocationHandasahEntity);
}
