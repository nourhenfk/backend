package com.unicom.security.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.unicom.security.models.Department;

public interface DepartmentRepository extends JpaRepository<Department, Long> {

}
