package com.unicom.security.Controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.unicom.security.models.Department;
import com.unicom.security.service.DepartmentService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/auth/departments")
public class DepartmentController {

    private final DepartmentService departmentService;

    @Autowired
    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping("/getAllDepartments")
    public List<Department> getAllDepartments() {
        return departmentService.getAllDepartments();
    }

    @GetMapping("/getDepartmentById/{id}")
    public ResponseEntity<Department> getDepartmentById(@PathVariable("id") Long id) {
        Optional<Department> department = departmentService.getDepartmentById(id);
        return department.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("createDepartment")
    public ResponseEntity<Department> createDepartment(@RequestBody Department department) {
        Department createdDepartment = departmentService.createDepartment(department);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdDepartment);
    }

    @PutMapping("/updateDepartment/{id}")
    public ResponseEntity<Department> updateDepartment(
            @PathVariable("id") Long id,
            @RequestBody Department updatedDepartment
    ) {
        Department department = departmentService.updateDepartment(id, updatedDepartment);
        return ResponseEntity.ok(department);
    }

    @DeleteMapping("/deleteDepartment/{id}")
    public ResponseEntity<Void> deleteDepartment(@PathVariable("id") Long id) {
        departmentService.deleteDepartment(id);
        return ResponseEntity.noContent().build();
    }
}

