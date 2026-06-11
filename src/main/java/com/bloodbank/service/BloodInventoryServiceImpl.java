package com.bloodbank.service;

import com.bloodbank.entity.BloodInventory;
import com.bloodbank.repository.BloodInventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BloodInventoryServiceImpl {

    @Autowired
    private BloodInventoryRepository inventoryRepository;

    public BloodInventory addInventory(BloodInventory inventory) {
        return inventoryRepository.save(inventory);
    }

    public List<BloodInventory> getAllInventory() {
        return inventoryRepository.findAll();
    }

    public BloodInventory getInventoryById(Long id) {
        return inventoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Inventory record not found with id: " + id));
    }

    public BloodInventory getInventoryByBloodGroup(String bloodGroup) {
        return inventoryRepository.findByBloodGroup(bloodGroup)
                .orElseThrow(() -> new RuntimeException("No inventory found for blood group: " + bloodGroup));
    }

    public BloodInventory updateUnits(String bloodGroup, int units) {
        BloodInventory inventory = getInventoryByBloodGroup(bloodGroup);
        inventory.setAvailableUnits(inventory.getAvailableUnits() + units);
        return inventoryRepository.save(inventory);
    }

    public BloodInventory deductUnits(String bloodGroup, int units) {
        BloodInventory inventory = getInventoryByBloodGroup(bloodGroup);
        if (inventory.getAvailableUnits() < units) {
            throw new RuntimeException("Insufficient blood units for group: " + bloodGroup);
        }
        inventory.setAvailableUnits(inventory.getAvailableUnits() - units);
        return inventoryRepository.save(inventory);
    }

    public BloodInventory updateInventory(Long id, BloodInventory updated) {
        BloodInventory existing = getInventoryById(id);
        existing.setAvailableUnits(updated.getAvailableUnits());
        existing.setReservedUnits(updated.getReservedUnits());
        return inventoryRepository.save(existing);
    }

    public void deleteInventory(Long id) {
        inventoryRepository.deleteById(id);
    }
}
