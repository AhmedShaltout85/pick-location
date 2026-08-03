package com.a08r.pick_location.controllers;

import com.a08r.pick_location.models.dto.SectorDTO;
import com.a08r.pick_location.services.ISectorServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/v1/sectors")
@CrossOrigin(origins = "*")
public class SectorController {

    private final ISectorServices iSectorServices;

    @Autowired
    public SectorController(ISectorServices iSectorServices) {
        this.iSectorServices = iSectorServices;
    }

    @GetMapping("/all")
    public ResponseEntity<List<SectorDTO>> getAll() {
        return this.iSectorServices.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<SectorDTO> findById(@PathVariable Long id) {
        return this.iSectorServices.findById(id);
    }

    @PostMapping("/create")
    public ResponseEntity<SectorDTO> create(@RequestBody SectorDTO sectorDTO) {
        return this.iSectorServices.create(sectorDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SectorDTO> update(@PathVariable Long id, @RequestBody SectorDTO sectorDTO) {
        return this.iSectorServices.update(id, sectorDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> remove(@PathVariable Long id) {
        return this.iSectorServices.remove(id);
    }
}
