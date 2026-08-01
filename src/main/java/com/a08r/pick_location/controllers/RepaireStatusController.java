package com.a08r.pick_location.controllers;

import com.a08r.pick_location.models.dto.RepaireStatusDTO;
import com.a08r.pick_location.services.IRepaireStatusServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/v1/repaire-status")
@CrossOrigin(origins = "*")
public class RepaireStatusController {

    private final IRepaireStatusServices iRepaireStatusServices;

    @Autowired
    public RepaireStatusController(IRepaireStatusServices iRepaireStatusServices) {
        this.iRepaireStatusServices = iRepaireStatusServices;
    }

    @GetMapping("/all")
    public ResponseEntity<List<RepaireStatusDTO>> getAll() {
        return this.iRepaireStatusServices.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<RepaireStatusDTO> findById(@PathVariable Long id) {
        return this.iRepaireStatusServices.findById(id);
    }

    @PostMapping("/create")
    public ResponseEntity<RepaireStatusDTO> create(@RequestBody RepaireStatusDTO repaireStatusDTO) {
        return this.iRepaireStatusServices.create(repaireStatusDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<RepaireStatusDTO> update(@PathVariable Long id, @RequestBody RepaireStatusDTO repaireStatusDTO) {
        return this.iRepaireStatusServices.update(id, repaireStatusDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> remove(@PathVariable Long id) {
        return this.iRepaireStatusServices.remove(id);
    }
}
