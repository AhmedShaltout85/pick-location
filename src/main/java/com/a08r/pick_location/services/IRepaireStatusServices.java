package com.a08r.pick_location.services;

import com.a08r.pick_location.models.dto.RepaireStatusDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IRepaireStatusServices {
    ResponseEntity<List<RepaireStatusDTO>> getAll();
    ResponseEntity<RepaireStatusDTO> findById(Long id);
    ResponseEntity<RepaireStatusDTO> create(RepaireStatusDTO repaireStatusDTO);
    ResponseEntity<RepaireStatusDTO> update(Long id, RepaireStatusDTO repaireStatusDTO);
    ResponseEntity<String> remove(Long id);
}
