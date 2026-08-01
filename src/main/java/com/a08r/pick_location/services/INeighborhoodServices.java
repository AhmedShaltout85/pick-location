package com.a08r.pick_location.services;

import com.a08r.pick_location.models.dto.NeighborhoodDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface INeighborhoodServices {
    ResponseEntity<List<NeighborhoodDTO>> getAll();
    ResponseEntity<NeighborhoodDTO> findById(Long id);
    ResponseEntity<NeighborhoodDTO> create(NeighborhoodDTO neighborhoodDTO);
    ResponseEntity<NeighborhoodDTO> update(Long id, NeighborhoodDTO neighborhoodDTO);
    ResponseEntity<String> remove(Long id);
}
