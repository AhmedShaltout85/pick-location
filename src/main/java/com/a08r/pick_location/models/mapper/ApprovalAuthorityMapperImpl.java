package com.a08r.pick_location.models.mapper;

import com.a08r.pick_location.models.dto.ApprovalAuthorityDTO;
import com.a08r.pick_location.models.approval.ApprovalAuthorityEntity;

public class ApprovalAuthorityMapperImpl implements IApprovalAuthorityMapper {

    @Override
    public ApprovalAuthorityEntity approvalAuthorityDTOToApprovalAuthorityEntity(ApprovalAuthorityDTO approvalAuthorityDTO) {
        if (approvalAuthorityDTO == null) {
            throw new NullPointerException("approvalAuthorityDTO should not be null");
        }
        ApprovalAuthorityEntity approvalAuthorityEntity = new ApprovalAuthorityEntity();
        approvalAuthorityEntity.setId(approvalAuthorityDTO.getId());
        approvalAuthorityEntity.setApprovalName(approvalAuthorityDTO.getApprovalName());
        return approvalAuthorityEntity;
    }

    @Override
    public ApprovalAuthorityDTO approvalAuthorityEntityToApprovalAuthorityDTO(ApprovalAuthorityEntity approvalAuthorityEntity) {
        if (approvalAuthorityEntity == null) {
            throw new NullPointerException("approvalAuthorityEntity should not be null");
        }
        ApprovalAuthorityDTO approvalAuthorityDTO = new ApprovalAuthorityDTO();
        approvalAuthorityDTO.setId(approvalAuthorityEntity.getId());
        approvalAuthorityDTO.setApprovalName(approvalAuthorityEntity.getApprovalName());
        return approvalAuthorityDTO;
    }
}
