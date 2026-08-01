package com.a08r.pick_location.services;

import com.a08r.pick_location.models.dto.ComplaintTypeDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IComplaintTypeServices {
    ResponseEntity<List<ComplaintTypeDTO>> getAll();
    ResponseEntity<ComplaintTypeDTO> findById(Long id);
    ResponseEntity<ComplaintTypeDTO> create(ComplaintTypeDTO complaintTypeDTO);
    ResponseEntity<ComplaintTypeDTO> update(Long id, ComplaintTypeDTO complaintTypeDTO);
    ResponseEntity<String> remove(Long id);
}
