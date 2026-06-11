package com.bloodbank.service;

import com.bloodbank.entity.BloodInventory;
import com.bloodbank.entity.BloodRequest;
import com.bloodbank.entity.Hospital;
import com.bloodbank.entity.Patient;
import com.bloodbank.repository.BloodRequestRepository;
import com.bloodbank.repository.HospitalRepository;
import com.bloodbank.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BloodRequestServiceImpl {

    @Autowired
    private BloodRequestRepository bloodRequestRepository;

    @Autowired
    private BloodInventoryServiceImpl inventoryService;

    @Autowired
    private HospitalRepository hospitalRepository;

    @Autowired
    private PatientRepository patientRepository;

    public BloodRequest createRequest(BloodRequest request) {

        // Fetch full hospital details by id
        Hospital hospital = hospitalRepository.findById(request.getHospital().getId())
                .orElseThrow(() -> new RuntimeException("Hospital not found with id: "
                        + request.getHospital().getId()));
        request.setHospital(hospital);

        // Fetch full patient details by id (patient is optional)
        if (request.getPatient() != null && request.getPatient().getId() != null) {
            Patient patient = patientRepository.findById(request.getPatient().getId())
                    .orElseThrow(() -> new RuntimeException("Patient not found with id: "
                            + request.getPatient().getId()));
            request.setPatient(patient);
        }

        return bloodRequestRepository.save(request);
    }

    public List<BloodRequest> getAllRequests() {
        return bloodRequestRepository.findAll();
    }

    public BloodRequest getRequestById(Long id) {
        return bloodRequestRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Blood request not found with id: " + id));
    }

    public List<BloodRequest> getRequestsByHospital(Long hospitalId) {
        return bloodRequestRepository.findByHospitalId(hospitalId);
    }

    public List<BloodRequest> getRequestsByStatus(BloodRequest.RequestStatus status) {
        return bloodRequestRepository.findByStatus(status);
    }

    // ADMIN/STAFF approves - only checks if enough units are available
    public BloodRequest approveRequest(Long id) {
        BloodRequest request = getRequestById(id);

        if (request.getStatus() != BloodRequest.RequestStatus.PENDING) {
            throw new RuntimeException("Only PENDING requests can be approved.");
        }

        // Check if enough units are available in inventory
        BloodInventory inventory = inventoryService.getInventoryByBloodGroup(request.getBloodGroup());
        if (inventory.getAvailableUnits() < request.getUnitsRequired()) {
            throw new RuntimeException("Insufficient units for blood group: "
                    + request.getBloodGroup()
                    + ". Available: " + inventory.getAvailableUnits()
                    + ", Required: " + request.getUnitsRequired());
        }

        request.setStatus(BloodRequest.RequestStatus.APPROVED);
        return bloodRequestRepository.save(request);
    }

    // ADMIN/STAFF rejects a request
    public BloodRequest rejectRequest(Long id, String remarks) {
        BloodRequest request = getRequestById(id);

        if (request.getStatus() != BloodRequest.RequestStatus.PENDING) {
            throw new RuntimeException("Only PENDING requests can be rejected.");
        }

        request.setStatus(BloodRequest.RequestStatus.REJECTED);
        request.setRemarks(remarks);
        return bloodRequestRepository.save(request);
    }

    // Mark as fulfilled - deducts units from inventory
    public BloodRequest fulfillRequest(Long id) {
        BloodRequest request = getRequestById(id);

        if (request.getStatus() != BloodRequest.RequestStatus.APPROVED) {
            throw new RuntimeException("Only APPROVED requests can be fulfilled.");
        }

        // Deduct from inventory
        inventoryService.deductUnits(request.getBloodGroup(), request.getUnitsRequired());

        request.setStatus(BloodRequest.RequestStatus.FULFILLED);
        return bloodRequestRepository.save(request);
    }

    public void deleteRequest(Long id) {
        bloodRequestRepository.deleteById(id);
    }
}