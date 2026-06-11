package com.bloodbank.controller;

import com.bloodbank.entity.BloodInventory;
import com.bloodbank.service.BloodInventoryService;
import com.bloodbank.service.BloodInventoryServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/inventory")
public class BloodInventoryController {

    @Autowired
    private BloodInventoryServiceImpl inventoryService;

    // ADMIN or STAFF
    @PostMapping
    public ResponseEntity<BloodInventory> addInventory(@RequestBody BloodInventory inventory) {
        return ResponseEntity.ok(inventoryService.addInventory(inventory));
    }

    @GetMapping
    public ResponseEntity<List<BloodInventory>> getAllInventory() {
        return ResponseEntity.ok(inventoryService.getAllInventory());
    }

    @GetMapping("/{id}")
    public ResponseEntity<BloodInventory> getById(@PathVariable Long id) {
        return ResponseEntity.ok(inventoryService.getInventoryById(id));
    }

    @GetMapping("/bloodgroup/{group}")
    public ResponseEntity<BloodInventory> getByBloodGroup(@PathVariable String group) {
        return ResponseEntity.ok(inventoryService.getInventoryByBloodGroup(group));
    }

    // PUT /api/inventory/add/{group}?units=5 - Add units after donation
    @PutMapping("/add/{group}")
    public ResponseEntity<BloodInventory> addUnits(@PathVariable String group,
                                                   @RequestParam int units) {
        return ResponseEntity.ok(inventoryService.updateUnits(group, units));
    }

    // PUT /api/inventory/deduct/{group}?units=2 - Deduct units
    @PutMapping("/deduct/{group}")
    public ResponseEntity<BloodInventory> deductUnits(@PathVariable String group,
                                                      @RequestParam int units) {
        return ResponseEntity.ok(inventoryService.deductUnits(group, units));
    }

    @PutMapping("/{id}")
    public ResponseEntity<BloodInventory> updateInventory(@PathVariable Long id,
                                                          @RequestBody BloodInventory inventory) {
        return ResponseEntity.ok(inventoryService.updateInventory(id, inventory));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteInventory(@PathVariable Long id) {
        inventoryService.deleteInventory(id);
        return ResponseEntity.ok("Inventory record deleted.");
    }
}