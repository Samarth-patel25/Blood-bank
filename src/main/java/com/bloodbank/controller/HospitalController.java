package com.bloodbank.controller;

import com.bloodbank.entity.Hospital;
import com.bloodbank.service.HospitalService;
import com.bloodbank.service.HospitalServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/hospitals")
public class HospitalController {

    @Autowired
    private HospitalServiceImpl hospitalService;

    @PostMapping("/register")
    public ResponseEntity<Hospital> register(@RequestBody Hospital hospital) {
        return ResponseEntity.ok(hospitalService.registerHospital(hospital));
    }

    @GetMapping
    public ResponseEntity<List<Hospital>> getAllHospitals() {
        return ResponseEntity.ok(hospitalService.getAllHospitals());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Hospital> getHospitalById(@PathVariable Long id) {
        return ResponseEntity.ok(hospitalService.getHospitalById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Hospital> updateHospital(@PathVariable Long id, @RequestBody Hospital hospital) {
        return ResponseEntity.ok(hospitalService.updateHospital(id, hospital));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteHospital(@PathVariable Long id) {
        hospitalService.deleteHospital(id);
        return ResponseEntity.ok("Hospital deleted successfully.");
    }
}
