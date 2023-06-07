package com.unicom.security.repos;

import org.springframework.data.jpa.repository.JpaRepository;


import com.unicom.security.models.Team;
import com.unicom.security.user.User;

public interface TeamRepository extends JpaRepository<Team, Long>{
	Team findByUsers(User user);
}
