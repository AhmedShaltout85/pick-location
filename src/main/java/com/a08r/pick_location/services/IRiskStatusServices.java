package com.a08r.pick_location.services;

import com.a08r.pick_location.models.dto.RiskStatusDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IRiskStatusServices {
    ResponseEntity<List<RiskStatusDTO>> getAll();
    ResponseEntity<RiskStatusDTO> findById(Long id);
    ResponseEntity<RiskStatusDTO> create(RiskStatusDTO riskStatusDTO);
    ResponseEntity<RiskStatusDTO> update(Long id, RiskStatusDTO riskStatusDTO);
    ResponseEntity<String> remove(Long id);
}
