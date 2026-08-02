package com.a08r.pick_location.controllers;

import com.a08r.pick_location.models.dto.RiskStatusDTO;
import com.a08r.pick_location.services.IRiskStatusServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/v1/risk-status")
@CrossOrigin(origins = "*")
public class RiskStatusController {

    private final IRiskStatusServices iRiskStatusServices;

    @Autowired
    public RiskStatusController(IRiskStatusServices iRiskStatusServices) {
        this.iRiskStatusServices = iRiskStatusServices;
    }

    @GetMapping("/all")
    public ResponseEntity<List<RiskStatusDTO>> getAll() {
        return this.iRiskStatusServices.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<RiskStatusDTO> findById(@PathVariable Long id) {
        return this.iRiskStatusServices.findById(id);
    }

    @PostMapping("/create")
    public ResponseEntity<RiskStatusDTO> create(@RequestBody RiskStatusDTO riskStatusDTO) {
        return this.iRiskStatusServices.create(riskStatusDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<RiskStatusDTO> update(@PathVariable Long id, @RequestBody RiskStatusDTO riskStatusDTO) {
        return this.iRiskStatusServices.update(id, riskStatusDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> remove(@PathVariable Long id) {
        return this.iRiskStatusServices.remove(id);
    }
}
