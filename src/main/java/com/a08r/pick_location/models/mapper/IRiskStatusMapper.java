package com.a08r.pick_location.models.mapper;

import com.a08r.pick_location.models.dto.RiskStatusDTO;
import com.a08r.pick_location.models.risk.RiskStatusEntity;

public interface IRiskStatusMapper {
    RiskStatusEntity riskStatusDTOToRiskStatusEntity(RiskStatusDTO riskStatusDTO);
    RiskStatusDTO riskStatusEntityToRiskStatusDTO(RiskStatusEntity riskStatusEntity);
}
