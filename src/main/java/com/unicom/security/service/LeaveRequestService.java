package com.unicom.security.service;

import org.springframework.stereotype.Service;

import com.unicom.security.models.LeaveRequest;
import com.unicom.security.models.RequestStatus;
import com.unicom.security.repos.LeaveRequestRepository;
import com.unicom.security.user.User;
import com.unicom.security.user.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
public class LeaveRequestService {

	  private final LeaveRequestRepository leaveRequestRepository;
	    private final UserRepository userRepository;

	    public LeaveRequestService(LeaveRequestRepository leaveRequestRepository, UserRepository userRepository) {
	        this.leaveRequestRepository = leaveRequestRepository;
	        this.userRepository = userRepository;
	    }

	    public List<LeaveRequest> getAllLeaveRequests() {
	        return leaveRequestRepository.findAll();
	    }

	    public LeaveRequest getLeaveRequestById(Long leaveRequestId) {
	        return leaveRequestRepository.findById(leaveRequestId)
	                .orElseThrow();
	    }

	    public LeaveRequest createLeaveRequest(Integer userId, LeaveRequest leaveRequest, List<byte[]> documents) {
	        Optional<User> userOptional = userRepository.findById(userId);
	        User user = userOptional.orElseThrow();

	        leaveRequest.setUser(user);
	        leaveRequest.setStatus(RequestStatus.PENDING);
	        leaveRequest.setDocuments(documents);

	        return leaveRequestRepository.save(leaveRequest);
	    }

    public LeaveRequest updateLeaveRequest(Long leaveRequestId, LeaveRequest updatedLeaveRequest) {
        LeaveRequest leaveRequest = getLeaveRequestById(leaveRequestId);

        leaveRequest.setStartDate(updatedLeaveRequest.getStartDate());
        leaveRequest.setEndDate(updatedLeaveRequest.getEndDate());
        leaveRequest.setReason(updatedLeaveRequest.getReason());
        // Set other properties as needed

        return leaveRequestRepository.save(leaveRequest);
    }

    public void deleteLeaveRequest(Long leaveRequestId) {
        LeaveRequest leaveRequest = getLeaveRequestById(leaveRequestId);
        leaveRequestRepository.delete(leaveRequest);
    }

    public void approveLeaveRequest(Long leaveRequestId) {
        LeaveRequest leaveRequest = getLeaveRequestById(leaveRequestId);
        leaveRequest.setStatus(RequestStatus.APPROVED);
        leaveRequestRepository.save(leaveRequest);
    }

    public void rejectLeaveRequest(Long leaveRequestId) {
        LeaveRequest leaveRequest = getLeaveRequestById(leaveRequestId);
        leaveRequest.setStatus(RequestStatus.REJECTED);
        leaveRequestRepository.save(leaveRequest);
    }

    public void uploadDocuments(Long leaveRequestId, List<byte[]> documents) {
        LeaveRequest leaveRequest = getLeaveRequestById(leaveRequestId);
        leaveRequest.setDocuments(documents);
        leaveRequestRepository.save(leaveRequest);
    }

}
