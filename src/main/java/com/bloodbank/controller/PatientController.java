package com.bloodbank.controller;

import com.bloodbank.entity.Patient;
import com.bloodbank.service.PatientService;
import com.bloodbank.service.PatientServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/patients")
public class PatientController {

    @Autowired
    private PatientServiceImpl patientService;


    @PostMapping
    public ResponseEntity<Patient> addPatient(@RequestBody Patient patient) {
        return ResponseEntity.ok(patientService.addPatient(patient));
    }

    @GetMapping
    public ResponseEntity<List<Patient>> getAllPatients() {
        return ResponseEntity.ok(patientService.getAllPatients());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Patient> getPatientById(@PathVariable Long id) {
        return ResponseEntity.ok(patientService.getPatientById(id));
    }

    @GetMapping("/bloodgroup/{group}")
    public ResponseEntity<List<Patient>> getByBloodGroup(@PathVariable String group) {
        return ResponseEntity.ok(patientService.getPatientsByBloodGroup(group));
    }

    @GetMapping("/hospital/{hospitalId}")
    public ResponseEntity<List<Patient>> getByHospital(@PathVariable Long hospitalId) {
        return ResponseEntity.ok(patientService.getPatientsByHospital(hospitalId));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Patient> updatePatient(@PathVariable Long id, @RequestBody Patient patient) {
        return ResponseEntity.ok(patientService.updatePatient(id, patient));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePatient(@PathVariable Long id) {
        patientService.deletePatient(id);
        return ResponseEntity.ok("Patient deleted successfully.");
    }
}
