package com.unicom.security.Controller;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.unicom.security.models.RequestStatus;
import com.unicom.security.models.UserRegistrationRequest;
import com.unicom.security.service.UserRegistrationService;
import com.unicom.security.user.User;
import com.unicom.security.user.UserRepository;


@RestController
@CrossOrigin(origins = "http://localhost:8080")

@RequestMapping("/api/v1/auth")
public class UserRegistrationController { 

    @Autowired
    private UserRegistrationService userRegistrationService;
    
    @Autowired
    private UserRepository userRepository;

    @PostMapping("/submit")
    public ResponseEntity<?> submitRegistrationRequest(@RequestBody UserRegistrationRequest registrationRequest) {
        UserRegistrationRequest savedRequest = userRegistrationService.submitRegistrationRequest(registrationRequest);
        return ResponseEntity.ok(savedRequest);
    }

     
    @GetMapping("/pending-requests")
    public ResponseEntity<?> getPendingRegistrationRequests() {
        List<User> pendingRequests = userRepository.findByStatus(RequestStatus.PENDING);
        return ResponseEntity.ok(pendingRequests);
    }

 
    @PostMapping("/approve/{requestId}")
    public ResponseEntity<?> approveRegistrationRequest(@PathVariable int requestId) {
    	User user = userRepository.findById(requestId).orElseThrow();
    	user.setStatus(RequestStatus.APPROVED);
    	userRepository.save(user);
        return ResponseEntity.ok(user);
    }

   
    @PostMapping("/reject/{requestId}")
    public ResponseEntity<?> rejectRegistrationRequest(@PathVariable int requestId) {
    	User user = userRepository.findById(requestId).orElseThrow();
    	user.setStatus(RequestStatus.REJECTED);
    	userRepository.save(user);
        return ResponseEntity.ok(user);
    }
}

