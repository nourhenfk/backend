package com.unicom.security.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.unicom.security.models.Manager;



public interface ManagerRepository extends JpaRepository<Manager,Long> {

	@Query("select m from Manager m join fetch m.user where m.id = :id")
	public Manager findWithUser( Long id);

}
