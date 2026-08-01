package com.a08r.pick_location.models.mapper;

import com.a08r.pick_location.models.dto.RepaireStatusDTO;
import com.a08r.pick_location.models.repair.RepaireStatusEntity;

public interface IRepaireStatusMapper {
    RepaireStatusEntity repaireStatusDTOToRepaireStatusEntity(RepaireStatusDTO repaireStatusDTO);
    RepaireStatusDTO repaireStatusEntityToRepaireStatusDTO(RepaireStatusEntity repaireStatusEntity);
}
