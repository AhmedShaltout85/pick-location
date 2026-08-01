package com.a08r.pick_location.repositores;

import com.a08r.pick_location.models.approval.ApprovalAuthorityEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IApprovalAuthorityRepository extends JpaRepository<ApprovalAuthorityEntity, Long> {
    Optional<ApprovalAuthorityEntity> findByApprovalName(String approvalName);
}
