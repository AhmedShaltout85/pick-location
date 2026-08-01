package com.a08r.pick_location.controllers;

import com.a08r.pick_location.models.dto.ComplaintTypeDTO;
import com.a08r.pick_location.services.IComplaintTypeServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/v1/complaint-types")
@CrossOrigin(origins = "*")
public class ComplaintTypeController {

    private final IComplaintTypeServices iComplaintTypeServices;

    @Autowired
    public ComplaintTypeController(IComplaintTypeServices iComplaintTypeServices) {
        this.iComplaintTypeServices = iComplaintTypeServices;
    }

    @GetMapping("/all")
    public ResponseEntity<List<ComplaintTypeDTO>> getAll() {
        return this.iComplaintTypeServices.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ComplaintTypeDTO> findById(@PathVariable Long id) {
        return this.iComplaintTypeServices.findById(id);
    }

    @PostMapping("/create")
    public ResponseEntity<ComplaintTypeDTO> create(@RequestBody ComplaintTypeDTO complaintTypeDTO) {
        return this.iComplaintTypeServices.create(complaintTypeDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ComplaintTypeDTO> update(@PathVariable Long id, @RequestBody ComplaintTypeDTO complaintTypeDTO) {
        return this.iComplaintTypeServices.update(id, complaintTypeDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> remove(@PathVariable Long id) {
        return this.iComplaintTypeServices.remove(id);
    }
}
