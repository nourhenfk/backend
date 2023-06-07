package com.unicom.security.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.unicom.security.models.LeaveRequest;

public interface LeaveRequestRepository extends JpaRepository<LeaveRequest, Long> {

}
