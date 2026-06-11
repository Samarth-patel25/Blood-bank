package com.bloodbank.controller;

import com.bloodbank.entity.BloodRequest;
import com.bloodbank.entity.BloodRequest.RequestStatus;
import com.bloodbank.service.BloodRequestService;
import com.bloodbank.service.BloodRequestServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/requests")
public class BloodRequestController {

    @Autowired
    private BloodRequestServiceImpl bloodRequestService;

    @PostMapping
    public ResponseEntity<BloodRequest> createRequest(@RequestBody BloodRequest request) {
        return ResponseEntity.ok(bloodRequestService.createRequest(request));
    }

    @GetMapping
    public ResponseEntity<List<BloodRequest>> getAllRequests() {
        return ResponseEntity.ok(bloodRequestService.getAllRequests());
    }

    @GetMapping("/{id}")
    public ResponseEntity<BloodRequest> getById(@PathVariable Long id) {
        return ResponseEntity.ok(bloodRequestService.getRequestById(id));
    }

    @GetMapping("/hospital/{hospitalId}")
    public ResponseEntity<List<BloodRequest>> getByHospital(@PathVariable Long hospitalId) {
        return ResponseEntity.ok(bloodRequestService.getRequestsByHospital(hospitalId));
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<List<BloodRequest>> getByStatus(@PathVariable RequestStatus status) {
        return ResponseEntity.ok(bloodRequestService.getRequestsByStatus(status));
    }

    @PutMapping("/{id}/approve")
    public ResponseEntity<BloodRequest> approveRequest(@PathVariable Long id) {
        return ResponseEntity.ok(bloodRequestService.approveRequest(id));
    }

    @PutMapping("/{id}/reject")
    public ResponseEntity<BloodRequest> rejectRequest(@PathVariable Long id,
                                                      @RequestParam(required = false) String remarks) {
        return ResponseEntity.ok(bloodRequestService.rejectRequest(id, remarks));
    }

    @PutMapping("/{id}/fulfill")
    public ResponseEntity<BloodRequest> fulfillRequest(@PathVariable Long id) {
        return ResponseEntity.ok(bloodRequestService.fulfillRequest(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteRequest(@PathVariable Long id) {
        bloodRequestService.deleteRequest(id);
        return ResponseEntity.ok("Blood request deleted.");
    }
}