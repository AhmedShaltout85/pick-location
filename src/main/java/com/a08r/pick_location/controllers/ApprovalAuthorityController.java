package com.a08r.pick_location.controllers;

import com.a08r.pick_location.models.dto.ApprovalAuthorityDTO;
import com.a08r.pick_location.services.IApprovalAuthorityServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/v1/approval-authorities")
@CrossOrigin(origins = "*")
public class ApprovalAuthorityController {

    private final IApprovalAuthorityServices iApprovalAuthorityServices;

    @Autowired
    public ApprovalAuthorityController(IApprovalAuthorityServices iApprovalAuthorityServices) {
        this.iApprovalAuthorityServices = iApprovalAuthorityServices;
    }

    @GetMapping("/all")
    public ResponseEntity<List<ApprovalAuthorityDTO>> getAll() {
        return this.iApprovalAuthorityServices.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApprovalAuthorityDTO> findById(@PathVariable Long id) {
        return this.iApprovalAuthorityServices.findById(id);
    }

    @PostMapping("/create")
    public ResponseEntity<ApprovalAuthorityDTO> create(@RequestBody ApprovalAuthorityDTO approvalAuthorityDTO) {
        return this.iApprovalAuthorityServices.create(approvalAuthorityDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApprovalAuthorityDTO> update(@PathVariable Long id, @RequestBody ApprovalAuthorityDTO approvalAuthorityDTO) {
        return this.iApprovalAuthorityServices.update(id, approvalAuthorityDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> remove(@PathVariable Long id) {
        return this.iApprovalAuthorityServices.remove(id);
    }
}
