package com.unicom.security.user;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

import com.unicom.security.models.RequestStatus;

public interface UserRepository extends JpaRepository<User, Integer> {

  Optional<User> findByEmail(String email);

Optional<User> findByEmailAndStatus(String email, RequestStatus approved);

Optional<User> findByIdAndStatus(long id, RequestStatus status);
List<User> findByStatus( RequestStatus approved);

}
