package com.unicom.security.repos;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.unicom.security.models.Employee;



public interface EmployeeRepository  extends JpaRepository<Employee,Long>{

	@Query("select e from Employee e join fetch e.user where e.id = :id")
	public Employee findWithUser( Long id); 
	 public Employee getById(Long id);
	 @Query("SELECT e FROM Employee e LEFT JOIN FETCH e.missions WHERE e.id = :id")
	    Optional<Employee> findWithMissionsById(Long id);
}
