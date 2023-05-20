package com.unicom.security.Controller;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.unicom.security.exceptions.RessourceNotFoundException;
import com.unicom.security.models.Employee;
import com.unicom.security.models.RequestStatus;
import com.unicom.security.repos.EmployeeRepository;
import com.unicom.security.user.UserRepository;

import jakarta.servlet.http.HttpServletRequest;


@RestController
//@CrossOrigin(origins = "*",allowedHeaders="*")
@RequestMapping("/api/v1/auth/employ")

public class EmployeeController {
	
	 
 
	@Autowired
	private EmployeeRepository employeeRepo;
	
	@Autowired
	  private PasswordEncoder passwordEncoder;
     
	@GetMapping("/listEmployee")
	public List<Employee> getAllEmployee(){
		return employeeRepo.findAll();
	}
	
	@PostMapping("/addEmployee")
	public Employee AddEmployee( @RequestBody Employee employee) {
		
		employee.getUser().setPassword( this.passwordEncoder.encode(employee.getUser().getPassword()));
		employee.getUser().setStatus(RequestStatus.APPROVED);
		return employeeRepo.save(employee);
	}
	@GetMapping("/getById/{id}")
    public ResponseEntity <Employee> getById(@PathVariable Long id) {
    	Employee employee =employeeRepo.findById(id).orElseThrow(() -> new RessourceNotFoundException("employee does not exist with this id "));
		return ResponseEntity.ok(employee);
    }
    
    @PutMapping("/updateEmployee/{id}")
    public ResponseEntity <Employee> updateEmployee (@PathVariable Long id,@RequestBody  Employee employeeDetails) {
    	
    	Employee employee=employeeRepo.findById(id).orElseThrow(() -> new RessourceNotFoundException("employee does not exist with this id "));
    	employee.setFirstname(employeeDetails.getFirstname());
    	employee.setLastname(employeeDetails.getLastname());
    	employee.setEmail(employeeDetails.getEmail());
    	employee.setTel(employeeDetails.getTel());
    	employee.setAdress(employeeDetails.getAdress());
    	this.employeeRepo.findWithUser(id).getUser().setEmail(employeeDetails.getEmail());
		Employee updateEmployee =employeeRepo.save(employee);
		return ResponseEntity.ok(updateEmployee);
    }
    @DeleteMapping("/deleteEmployee/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteEmployee(@PathVariable Long id){
    	Employee employee=employeeRepo.findById(id)
    			.orElseThrow(() -> new RessourceNotFoundException("employee does not exist with this id "));
    	employeeRepo.delete(employee);
    	 Map<String,Boolean> response =new HashMap<>();
    	 response.put("deleted", Boolean.TRUE);
    	 return ResponseEntity.ok(response);
    }
  
}
