package com.a08r.pick_location.models.mapper;

import com.a08r.pick_location.models.dto.RiskStatusDTO;
import com.a08r.pick_location.models.risk.RiskStatusEntity;

public class RiskStatusMapperImpl implements IRiskStatusMapper {

    @Override
    public RiskStatusEntity riskStatusDTOToRiskStatusEntity(RiskStatusDTO riskStatusDTO) {
        if (riskStatusDTO == null) {
            throw new NullPointerException("riskStatusDTO should not be null");
        }
        RiskStatusEntity riskStatusEntity = new RiskStatusEntity();
        riskStatusEntity.setId(riskStatusDTO.getId());
        riskStatusEntity.setRiskName(riskStatusDTO.getRiskName());
        return riskStatusEntity;
    }

    @Override
    public RiskStatusDTO riskStatusEntityToRiskStatusDTO(RiskStatusEntity riskStatusEntity) {
        if (riskStatusEntity == null) {
            throw new NullPointerException("riskStatusEntity should not be null");
        }
        RiskStatusDTO riskStatusDTO = new RiskStatusDTO();
        riskStatusDTO.setId(riskStatusEntity.getId());
        riskStatusDTO.setRiskName(riskStatusEntity.getRiskName());
        return riskStatusDTO;
    }
}
