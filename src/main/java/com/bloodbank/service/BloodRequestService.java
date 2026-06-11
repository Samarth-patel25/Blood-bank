package com.bloodbank.service;

import com.bloodbank.entity.BloodRequest;

import java.util.List;

public interface BloodRequestService {

    BloodRequest createRequest(BloodRequest request);
    List<BloodRequest> getAllRequests();
    BloodRequest getRequestById(Long id);
    List<BloodRequest> getRequestsByHospital(Long hospitalId);
    List<BloodRequest> getRequestsByStatus(BloodRequest.RequestStatus status);
    BloodRequest approveRequest(Long id);
    BloodRequest rejectRequest(Long id, String remarks);
    BloodRequest fulfillRequest(Long id);
    void deleteRequest(Long id);
}
