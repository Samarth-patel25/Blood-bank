package com.bloodbank.service;

import com.bloodbank.entity.Donor;

import java.util.List;

public interface DonorService {

    Donor registerDonor(Donor donor);
    List<Donor> getAllDonors();
    Donor getDonorById(Long id);
    List<Donor> getDonorsByBloodGroup(String bloodGroup);
    Donor updateDonor(Long id, Donor updatedDonor);
    void deleteDonor(Long id);
}
