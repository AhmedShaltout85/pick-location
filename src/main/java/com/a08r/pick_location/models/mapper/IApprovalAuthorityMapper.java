package com.a08r.pick_location.models.mapper;

import com.a08r.pick_location.models.dto.ApprovalAuthorityDTO;
import com.a08r.pick_location.models.approval.ApprovalAuthorityEntity;

public interface IApprovalAuthorityMapper {
    ApprovalAuthorityEntity approvalAuthorityDTOToApprovalAuthorityEntity(ApprovalAuthorityDTO approvalAuthorityDTO);
    ApprovalAuthorityDTO approvalAuthorityEntityToApprovalAuthorityDTO(ApprovalAuthorityEntity approvalAuthorityEntity);
}
