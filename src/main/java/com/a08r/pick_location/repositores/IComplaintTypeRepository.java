package com.a08r.pick_location.repositores;

import com.a08r.pick_location.models.complaint.ComplaintTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IComplaintTypeRepository extends JpaRepository<ComplaintTypeEntity, Long> {
    Optional<ComplaintTypeEntity> findByComplaintName(String complaintName);
}
