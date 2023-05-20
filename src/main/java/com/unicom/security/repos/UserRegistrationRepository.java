package com.unicom.security.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.unicom.security.models.RequestStatus;

import com.unicom.security.models.UserRegistrationRequest;

@Repository
public interface UserRegistrationRepository extends JpaRepository<UserRegistrationRequest, Long> {
    List<UserRegistrationRequest> findByStatus(RequestStatus status);



    UserRegistrationRequest findByIdAndStatus(Long id, String status);

}
