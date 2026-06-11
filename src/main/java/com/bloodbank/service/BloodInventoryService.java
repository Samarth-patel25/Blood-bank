package com.bloodbank.service;

import com.bloodbank.entity.BloodInventory;

import java.util.List;

public interface BloodInventoryService {

    BloodInventory addInventory(BloodInventory inventory);
    List<BloodInventory> getAllInventory();
    BloodInventory getInventoryById(Long id);
    BloodInventory getInventoryByBloodGroup(String bloodGroup);
    BloodInventory updateUnits(String bloodGroup, int units);
    BloodInventory deductUnits(String bloodGroup, int units);
    BloodInventory updateInventory(Long id, BloodInventory updated);
    void deleteInventory(Long id);
}
