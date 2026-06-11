package com.bloodbank.service;

import com.bloodbank.entity.Patient;
import com.bloodbank.repository.PatientRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatientServiceImpl implements PatientService{

    @Autowired
    private PatientRepository patientRepository;

    @Override
    @Transactional
    public Patient addPatient(Patient patient) {
        patientRepository.save(patient);
        return patient;
    }

    @Override
    public List<Patient> getAllPatients() {
        return patientRepository.findAll();
    }

    @Override
    public Patient getPatientById(Long id) {
        return patientRepository.findById(id).orElse(null);
    }

    @Override
    public List<Patient> getPatientsByBloodGroup(String bloodGroup) {
        return patientRepository.findByBloodGroup(bloodGroup);
    }

    @Override
    public List<Patient> getPatientsByHospital(Long hospitalId) {
        return patientRepository.findByHospitalId(hospitalId);
    }

    @Override
    @Transactional
    public Patient updatePatient(Long id, Patient updated) {
        Patient existing = getPatientById(id);
        existing.setName(updated.getName());
        existing.setAge(updated.getAge());
        existing.setBloodGroup(updated.getBloodGroup());
        existing.setDisease(updated.getDisease());
        existing.setContact(updated.getContact());
        existing.setHospital(updated.getHospital());
        return patientRepository.save(existing);
    }

    @Override
    @Transactional
    public void deletePatient(Long id) {
        patientRepository.deleteById(id);
    }
}
