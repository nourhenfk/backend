package com.unicom.security.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.unicom.security.models.RequestStatus;
import com.unicom.security.exceptions.RessourceNotFoundException;

import com.unicom.security.models.UserRegistrationRequest;
import com.unicom.security.repos.UserRegistrationRepository;

@Service
public class UserRegistrationService {

    @Autowired
    private UserRegistrationRepository userRegistrationRepository;

    public UserRegistrationRequest submitRegistrationRequest(UserRegistrationRequest registrationRequest) {
        registrationRequest.setStatus(RequestStatus.PENDING);
        registrationRequest.setRole("USER");
        return userRegistrationRepository.save(registrationRequest);
    }

    public List<UserRegistrationRequest> getPendingRegistrationRequests() {
        return userRegistrationRepository.findByStatus(RequestStatus.PENDING);
    }

    
    public UserRegistrationRequest approveRegistrationRequest(Long requestId) {
        UserRegistrationRequest registrationRequest = userRegistrationRepository.findById(requestId)
                .orElseThrow(() -> new RessourceNotFoundException("Registration request not found with ID: " + requestId));

        registrationRequest.setStatus(RequestStatus.APPROVED);
        UserRegistrationRequest approvedRequest = userRegistrationRepository.save(registrationRequest);
        System.out.println("Registration request approved with ID: " + approvedRequest.getId());
        return approvedRequest;
    } 




    public void rejectRegistrationRequest(Long requestId) {
        UserRegistrationRequest registrationRequest = userRegistrationRepository.findById(requestId)
                .orElseThrow(() -> new RessourceNotFoundException("User registration request not found"));

        registrationRequest.setStatus(RequestStatus.REJECTED);
        userRegistrationRepository.save(registrationRequest);
    }



}
