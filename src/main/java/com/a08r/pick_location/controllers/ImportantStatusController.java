package com.a08r.pick_location.controllers;

import com.a08r.pick_location.models.dto.ImportantStatusDTO;
import com.a08r.pick_location.services.IImportantStatusServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/v1/important-status")
@CrossOrigin(origins = "*")
public class ImportantStatusController {

    private final IImportantStatusServices iImportantStatusServices;

    @Autowired
    public ImportantStatusController(IImportantStatusServices iImportantStatusServices) {
        this.iImportantStatusServices = iImportantStatusServices;
    }

    @GetMapping("/all")
    public ResponseEntity<List<ImportantStatusDTO>> getAll() {
        return this.iImportantStatusServices.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ImportantStatusDTO> findById(@PathVariable Long id) {
        return this.iImportantStatusServices.findById(id);
    }

    @PostMapping("/create")
    public ResponseEntity<ImportantStatusDTO> create(@RequestBody ImportantStatusDTO importantStatusDTO) {
        return this.iImportantStatusServices.create(importantStatusDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ImportantStatusDTO> update(@PathVariable Long id, @RequestBody ImportantStatusDTO importantStatusDTO) {
        return this.iImportantStatusServices.update(id, importantStatusDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> remove(@PathVariable Long id) {
        return this.iImportantStatusServices.remove(id);
    }
}
