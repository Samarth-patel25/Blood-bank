package com.bloodbank.service;
import com.bloodbank.entity.Hospital;
import java.util.List;

public interface HospitalService {

    Hospital registerHospital(Hospital hospital);
    List<Hospital> getAllHospitals();
    Hospital getHospitalById(Long id);
    Hospital updateHospital(Long id, Hospital updated);
    void deleteHospital(Long id);
}
