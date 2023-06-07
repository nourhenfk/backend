package com.unicom.security.Controller;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import com.unicom.security.models.LeaveRequest;
import com.unicom.security.service.LeaveRequestService;
import com.unicom.security.user.User;

import org.springframework.validation.annotation.Validated;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/v1/auth/leaverequests")
public class LeaveRequestController {

    private final LeaveRequestService leaveRequestService;

    public LeaveRequestController(LeaveRequestService leaveRequestService) {
        this.leaveRequestService = leaveRequestService;
    }

    @GetMapping
    public ResponseEntity<List<LeaveRequest>> getAllLeaveRequests() {
        List<LeaveRequest> leaveRequests = leaveRequestService.getAllLeaveRequests();
        return ResponseEntity.ok(leaveRequests);
    }

    @GetMapping("/getLeaveRequestById/{id}")
    public ResponseEntity<LeaveRequest> getLeaveRequestById(@PathVariable("id") Long leaveRequestId) {
        LeaveRequest leaveRequest = leaveRequestService.getLeaveRequestById(leaveRequestId);
        return ResponseEntity.ok(leaveRequest);
    }

    @PostMapping("/addLeaveRequest")
    public ResponseEntity<LeaveRequest> createLeaveRequest(
            Principal principal,
            @Validated @RequestBody LeaveRequest leaveRequest,
            @RequestParam(value = "documents", required = false) MultipartFile[] documents) throws IOException {
        Integer userId = getUserIdFromPrincipal(principal);
        List<byte[]> documentBytes = convertMultipartFilesToBytes(documents);
        LeaveRequest createdLeaveRequest = leaveRequestService.createLeaveRequest(userId, leaveRequest, documentBytes);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdLeaveRequest);
    }
    private Integer getUserIdFromPrincipal(Principal principal) {
        if (principal instanceof UsernamePasswordAuthenticationToken) {
            UsernamePasswordAuthenticationToken authenticationToken = (UsernamePasswordAuthenticationToken) principal;
            Object principalObject = authenticationToken.getPrincipal();
            
            if (principalObject instanceof User) {
                User user = (User) principalObject;
                return user.getId();
            }
        }
        
        throw new IllegalArgumentException("Invalid principal object or user ID not found");
    }


    @PutMapping("/updateLeaveRequest/{id}")
    public ResponseEntity<LeaveRequest> updateLeaveRequest(
            @PathVariable("id") Long leaveRequestId,
            @Validated @RequestBody LeaveRequest updatedLeaveRequest) {
        LeaveRequest leaveRequest = leaveRequestService.updateLeaveRequest(leaveRequestId, updatedLeaveRequest);
        return ResponseEntity.ok(leaveRequest);
    }

    @DeleteMapping("/deleteLeaveRequest/{id}")
    public ResponseEntity<Void> deleteLeaveRequest(@PathVariable("id") Long leaveRequestId) {
        leaveRequestService.deleteLeaveRequest(leaveRequestId);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{id}/approve")
    @PreAuthorize("hasRole('MANAGER')")
    public ResponseEntity<Void> approveLeaveRequest(@PathVariable("id") Long leaveRequestId) {
        leaveRequestService.approveLeaveRequest(leaveRequestId);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{id}/reject")
    @PreAuthorize("hasRole('MANAGER')")
    public ResponseEntity<Void> rejectLeaveRequest(@PathVariable("id") Long leaveRequestId) {
        leaveRequestService.rejectLeaveRequest(leaveRequestId);
        return ResponseEntity.noContent().build();
    }

    private List<byte[]> convertMultipartFilesToBytes(MultipartFile[] multipartFiles) throws IOException {
        List<byte[]> documentBytes = new ArrayList<>();
        if (multipartFiles != null) {
            for (MultipartFile file : multipartFiles) {
                documentBytes.add(file.getBytes());
            }
        }
        return documentBytes;
    }
   



    @GetMapping("/getLeaveRequestDetails/{id}")
    public ResponseEntity<LeaveRequest> getLeaveRequestDetails(@PathVariable("id") Long leaveRequestId) {
        LeaveRequest leaveRequest = leaveRequestService.getLeaveRequestById(leaveRequestId);
        return ResponseEntity.ok(leaveRequest);
    }

}


