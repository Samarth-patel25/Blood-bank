package com.bloodbank.service;

import com.bloodbank.entity.Patient;

import java.util.List;

public interface PatientService {

    Patient addPatient(Patient patient);
    List<Patient> getAllPatients();
    Patient getPatientById(Long id);
    List<Patient> getPatientsByBloodGroup(String bloodGroup);
    List<Patient> getPatientsByHospital(Long hospitalId);
    Patient updatePatient(Long id, Patient updated);
    void deletePatient(Long id);
}
