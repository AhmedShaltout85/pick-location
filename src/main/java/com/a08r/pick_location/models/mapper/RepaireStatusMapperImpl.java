package com.a08r.pick_location.models.mapper;

import com.a08r.pick_location.models.dto.RepaireStatusDTO;
import com.a08r.pick_location.models.repair.RepaireStatusEntity;

public class RepaireStatusMapperImpl implements IRepaireStatusMapper {

    @Override
    public RepaireStatusEntity repaireStatusDTOToRepaireStatusEntity(RepaireStatusDTO repaireStatusDTO) {
        if (repaireStatusDTO == null) {
            throw new NullPointerException("repaireStatusDTO should not be null");
        }
        RepaireStatusEntity repaireStatusEntity = new RepaireStatusEntity();
        repaireStatusEntity.setId(repaireStatusDTO.getId());
        repaireStatusEntity.setRepaireType(repaireStatusDTO.getRepaireType());
        return repaireStatusEntity;
    }

    @Override
    public RepaireStatusDTO repaireStatusEntityToRepaireStatusDTO(RepaireStatusEntity repaireStatusEntity) {
        if (repaireStatusEntity == null) {
            throw new NullPointerException("repaireStatusEntity should not be null");
        }
        RepaireStatusDTO repaireStatusDTO = new RepaireStatusDTO();
        repaireStatusDTO.setId(repaireStatusEntity.getId());
        repaireStatusDTO.setRepaireType(repaireStatusEntity.getRepaireType());
        return repaireStatusDTO;
    }
}
