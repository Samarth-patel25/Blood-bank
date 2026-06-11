package com.bloodbank.repository;

import com.bloodbank.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface PatientRepository extends JpaRepository<Patient, Long> {
    List<Patient> findByBloodGroup(String bloodGroup);
    List<Patient> findByHospitalId(Long hospitalId);
}
