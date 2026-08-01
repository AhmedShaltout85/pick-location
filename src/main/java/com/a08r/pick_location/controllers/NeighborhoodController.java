package com.a08r.pick_location.controllers;

import com.a08r.pick_location.models.dto.NeighborhoodDTO;
import com.a08r.pick_location.services.INeighborhoodServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/v1/neighborhoods")
@CrossOrigin(origins = "*")
public class NeighborhoodController {

    private final INeighborhoodServices iNeighborhoodServices;

    @Autowired
    public NeighborhoodController(INeighborhoodServices iNeighborhoodServices) {
        this.iNeighborhoodServices = iNeighborhoodServices;
    }

    @GetMapping("/all")
    public ResponseEntity<List<NeighborhoodDTO>> getAll() {
        return this.iNeighborhoodServices.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<NeighborhoodDTO> findById(@PathVariable Long id) {
        return this.iNeighborhoodServices.findById(id);
    }

    @PostMapping("/create")
    public ResponseEntity<NeighborhoodDTO> create(@RequestBody NeighborhoodDTO neighborhoodDTO) {
        return this.iNeighborhoodServices.create(neighborhoodDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<NeighborhoodDTO> update(@PathVariable Long id, @RequestBody NeighborhoodDTO neighborhoodDTO) {
        return this.iNeighborhoodServices.update(id, neighborhoodDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> remove(@PathVariable Long id) {
        return this.iNeighborhoodServices.remove(id);
    }
}
