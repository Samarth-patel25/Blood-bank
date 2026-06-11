package com.bloodbank.controller;

import com.bloodbank.entity.Donor;
import com.bloodbank.service.DonorService;
import com.bloodbank.service.DonorServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/donors")
public class DonorController {

    @Autowired
    private DonorServiceImpl donorService;

    @PostMapping("/register")
    public ResponseEntity<Donor> register(@RequestBody Donor donor) {
        return ResponseEntity.ok(donorService.registerDonor(donor));
    }

    @GetMapping
    public ResponseEntity<List<Donor>> getAllDonors() {
        return ResponseEntity.ok(donorService.getAllDonors());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Donor> getDonorById(@PathVariable Long id) {
        return ResponseEntity.ok(donorService.getDonorById(id));
    }

    @GetMapping("/bloodgroup/{group}")
    public ResponseEntity<List<Donor>> getByBloodGroup(@PathVariable String group) {
        return ResponseEntity.ok(donorService.getDonorsByBloodGroup(group));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Donor> updateDonor(@PathVariable Long id, @RequestBody Donor donor) {
        return ResponseEntity.ok(donorService.updateDonor(id, donor));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteDonor(@PathVariable Long id) {
        donorService.deleteDonor(id);
        return ResponseEntity.ok("Donor deleted successfully.");
    }
}
