package com.bloodbank.service;

import com.bloodbank.entity.Hospital;
import com.bloodbank.repository.HospitalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HospitalServiceImpl implements HospitalService{

    @Autowired
    private HospitalRepository hospitalRepository;

    public Hospital registerHospital(Hospital hospital) {
        return hospitalRepository.save(hospital);
    }

    public List<Hospital> getAllHospitals() {
        return hospitalRepository.findAll();
    }

    public Hospital getHospitalById(Long id) {
        return hospitalRepository.findById(id)
                .orElse(null);
    }

    public Hospital updateHospital(Long id, Hospital updated) {

        Hospital existing = getHospitalById(id);
        existing.setName(updated.getName());
        existing.setAddress(updated.getAddress());
        existing.setContact(updated.getContact());
        existing.setEmail(updated.getEmail());
        existing.setLicenseNumber(updated.getLicenseNumber());
        return hospitalRepository.save(existing);
    }

    public void deleteHospital(Long id) {
        hospitalRepository.deleteById(id);
    }
}
