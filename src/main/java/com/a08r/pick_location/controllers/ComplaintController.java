package com.a08r.pick_location.controllers;

import com.a08r.pick_location.models.dto.ComplaintDTO;
import com.a08r.pick_location.services.IComplaintServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/v1/complaints")
@CrossOrigin(origins = "*")
public class ComplaintController {

    private final IComplaintServices iComplaintServices;

    @Autowired
    public ComplaintController(IComplaintServices iComplaintServices) {
        this.iComplaintServices = iComplaintServices;
    }

    @GetMapping("/all")
    public ResponseEntity<List<ComplaintDTO>> getAll() {
        return this.iComplaintServices.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ComplaintDTO> findById(@PathVariable Long id) {
        return this.iComplaintServices.findById(id);
    }

    @GetMapping("/neighborhood/{neighborhood}")
    public ResponseEntity<List<ComplaintDTO>> findByNeighborhood(@PathVariable String neighborhood) {
        return this.iComplaintServices.findByNeighborhood(neighborhood);
    }

    @GetMapping("/status/{complaintStatus}")
    public ResponseEntity<List<ComplaintDTO>> findByComplaintStatus(@PathVariable String complaintStatus) {
        return this.iComplaintServices.findByComplaintStatus(complaintStatus);
    }

    @GetMapping("/user/{currentUsername}")
    public ResponseEntity<List<ComplaintDTO>> findByCurrentUsername(@PathVariable String currentUsername) {
        return this.iComplaintServices.findByCurrentUsername(currentUsername);
    }

    @GetMapping("/finished/{isFinished}")
    public ResponseEntity<List<ComplaintDTO>> findByIsFinished(@PathVariable int isFinished) {
        return this.iComplaintServices.findByIsFinished(isFinished);
    }

    @GetMapping("/complaint-type/{complaintType}")
    public ResponseEntity<List<ComplaintDTO>> findByComplaintType(@PathVariable String complaintType) {
        return this.iComplaintServices.findByComplaintType(complaintType);
    }

    @GetMapping("/sector-name/{sectorName}")
    public ResponseEntity<List<ComplaintDTO>> findBySectorName(@PathVariable String sectorName) {
        return this.iComplaintServices.findBySectorName(sectorName);
    }

    @PostMapping("/create")
    public ResponseEntity<ComplaintDTO> create(@RequestBody ComplaintDTO complaintDTO) {
        return this.iComplaintServices.create(complaintDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ComplaintDTO> update(@PathVariable Long id, @RequestBody ComplaintDTO complaintDTO) {
        return this.iComplaintServices.update(id, complaintDTO);
    }

    @PutMapping("/{id}/repeat-complaint-number")
    public ResponseEntity<ComplaintDTO> updateRepeatComplaintNumber(@PathVariable Long id,
                                                                     @RequestBody ComplaintDTO complaintDTO) {
        return this.iComplaintServices.updateRepeatComplaintNumber(id, complaintDTO);
    }

    @PutMapping("/{id}/urgency-number")
    public ResponseEntity<ComplaintDTO> updateUrgencyNumber(@PathVariable Long id,
                                                             @RequestBody ComplaintDTO complaintDTO) {
        return this.iComplaintServices.updateUrgencyNumber(id, complaintDTO);
    }

    @PutMapping("/{id}/recipient")
    public ResponseEntity<ComplaintDTO> updateRecipient(@PathVariable Long id,
                                                         @RequestBody ComplaintDTO complaintDTO) {
        return this.iComplaintServices.updateRecipient(id, complaintDTO);
    }

    @PutMapping("/{id}/tracked")
    public ResponseEntity<ComplaintDTO> updateTracked(@PathVariable Long id,
                                                       @RequestBody ComplaintDTO complaintDTO) {
        return this.iComplaintServices.updateTracked(id, complaintDTO);
    }

    @PutMapping("/{id}/status-flags")
    public ResponseEntity<ComplaintDTO> updateStatusFlags(@PathVariable Long id,
                                                           @RequestBody ComplaintDTO complaintDTO) {
        return this.iComplaintServices.updateStatusFlags(id, complaintDTO);
    }

    @PutMapping("/{id}/approval-authority")
    public ResponseEntity<ComplaintDTO> updateApprovalAuthority(@PathVariable Long id,
                                                                 @RequestBody ComplaintDTO complaintDTO) {
        return this.iComplaintServices.updateApprovalAuthority(id, complaintDTO);
    }

    @PutMapping("/{id}/finish")
    public ResponseEntity<ComplaintDTO> finish(@PathVariable Long id) {
        return this.iComplaintServices.finish(id);
    }

    @PutMapping("/{id}/delete")
    public ResponseEntity<ComplaintDTO> softDelete(@PathVariable Long id) {
        return this.iComplaintServices.softDelete(id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> remove(@PathVariable Long id) {
        return this.iComplaintServices.remove(id);
    }
}
