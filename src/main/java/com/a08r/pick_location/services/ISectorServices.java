package com.a08r.pick_location.services;

import com.a08r.pick_location.models.dto.SectorDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ISectorServices {
    ResponseEntity<List<SectorDTO>> getAll();
    ResponseEntity<SectorDTO> findById(Long id);
    ResponseEntity<SectorDTO> create(SectorDTO sectorDTO);
    ResponseEntity<SectorDTO> update(Long id, SectorDTO sectorDTO);
    ResponseEntity<String> remove(Long id);
}
