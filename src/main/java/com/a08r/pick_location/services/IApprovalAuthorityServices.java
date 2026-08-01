package com.a08r.pick_location.services;

import com.a08r.pick_location.models.dto.ApprovalAuthorityDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IApprovalAuthorityServices {
    ResponseEntity<List<ApprovalAuthorityDTO>> getAll();
    ResponseEntity<ApprovalAuthorityDTO> findById(Long id);
    ResponseEntity<ApprovalAuthorityDTO> create(ApprovalAuthorityDTO approvalAuthorityDTO);
    ResponseEntity<ApprovalAuthorityDTO> update(Long id, ApprovalAuthorityDTO approvalAuthorityDTO);
    ResponseEntity<String> remove(Long id);
}
