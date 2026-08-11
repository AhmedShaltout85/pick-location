package com.a08r.pick_location.repositores;

import com.a08r.pick_location.models.complaints.ComplaintEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IComplaintRepository extends JpaRepository<ComplaintEntity, Long> {
    List<ComplaintEntity> findByNeighborhood(String neighborhood);
    List<ComplaintEntity> findByComplaintStatus(String complaintStatus);
    List<ComplaintEntity> findByCurrentUsername(String currentUsername);
    List<ComplaintEntity> findByIsDeleted(int isDeleted);
    List<ComplaintEntity> findByIsFinished(int isFinished);
    List<ComplaintEntity> findByNeighborhoodAndComplaintStatus(String neighborhood, String complaintStatus);
    List<ComplaintEntity> findByCurrentUsernameAndIsDeleted(String currentUsername, int isDeleted);
}
