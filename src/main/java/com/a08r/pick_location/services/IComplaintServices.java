package com.a08r.pick_location.services;

import com.a08r.pick_location.models.dto.ComplaintDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IComplaintServices {
    ResponseEntity<List<ComplaintDTO>> getAll();
    ResponseEntity<ComplaintDTO> findById(Long id);
    ResponseEntity<List<ComplaintDTO>> findByNeighborhood(String neighborhood);
    ResponseEntity<List<ComplaintDTO>> findByComplaintStatus(String complaintStatus);
    ResponseEntity<List<ComplaintDTO>> findByCurrentUsername(String currentUsername);
    ResponseEntity<List<ComplaintDTO>> findByIsFinished(int isFinished);
    ResponseEntity<List<ComplaintDTO>> findByComplaintType(String complaintType);
    ResponseEntity<List<ComplaintDTO>> findBySectorName(String sectorName);
    ResponseEntity<ComplaintDTO> create(ComplaintDTO complaintDTO);
    ResponseEntity<ComplaintDTO> update(Long id, ComplaintDTO complaintDTO);
    ResponseEntity<ComplaintDTO> finish(Long id);
    ResponseEntity<ComplaintDTO> updateRepeatComplaintNumber(Long id, ComplaintDTO complaintDTO);
    ResponseEntity<ComplaintDTO> updateUrgencyNumber(Long id, ComplaintDTO complaintDTO);
    ResponseEntity<ComplaintDTO> updateRecipient(Long id, ComplaintDTO complaintDTO);
    ResponseEntity<ComplaintDTO> updateTracked(Long id, ComplaintDTO complaintDTO);
    ResponseEntity<ComplaintDTO> updateStatusFlags(Long id, ComplaintDTO complaintDTO);
    ResponseEntity<ComplaintDTO> updateApprovalAuthority(Long id, ComplaintDTO complaintDTO);
    ResponseEntity<ComplaintDTO> updateRecipientDestination(Long id, ComplaintDTO complaintDTO);
    ResponseEntity<ComplaintDTO> softDelete(Long id);
    ResponseEntity<String> remove(Long id);
}
