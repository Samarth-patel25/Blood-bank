package com.bloodbank.service;

import com.bloodbank.entity.Donor;
import com.bloodbank.entity.Hospital;
import com.bloodbank.entity.User;
import com.bloodbank.repository.DonorRepository;
import com.bloodbank.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DonorServiceImpl {

    @Autowired
    private DonorRepository donorRepository;

    @Autowired
    private UserRepository userRepository;

    public Donor registerDonor(Donor donor) {
        User user = userRepository.findById(donor.getUser().getId())
                .orElseThrow(() -> new RuntimeException("user not found with id: "
                        + donor.getUser().getId()));
        donor.setUser(user);
        return donorRepository.save(donor);
    }

    public List<Donor> getAllDonors() {
        return donorRepository.findAll();
    }

    public Donor getDonorById(Long id) {
        return donorRepository.findById(id)
                .orElse(null);
    }

    public List<Donor> getDonorsByBloodGroup(String bloodGroup) {
        return donorRepository.findByBloodGroup(bloodGroup);
    }

    public Donor updateDonor(Long id, Donor updatedDonor) {
        Donor existing = getDonorById(id);
        existing.setName(updatedDonor.getName());
        existing.setAge(updatedDonor.getAge());
        existing.setContact(updatedDonor.getContact());
        existing.setAddress(updatedDonor.getAddress());
        return donorRepository.save(existing);
    }

    public void deleteDonor(Long id) {
        donorRepository.deleteById(id);
    }
}
