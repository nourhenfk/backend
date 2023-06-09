package com.unicom.security.Controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.unicom.security.exceptions.RessourceNotFoundException;
import com.unicom.security.models.Manager;
import com.unicom.security.models.RequestStatus;
import com.unicom.security.repos.ManagerRepository;

@RestController
//@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/api/v1/auth")
public class ManagerController {

	@Autowired
	private ManagerRepository managerRepo;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	@GetMapping("/listManager")
	public List<Manager> getAllManager() {
		return managerRepo.findAll();
	}

	@PostMapping("/addManager")
	public Manager AddManager(@RequestBody Manager manager) {
		System.out.println(manager);
		manager.getUser().setPassword(this.passwordEncoder.encode(manager.getUser().getPassword()));
		manager.getUser().setStatus(RequestStatus.APPROVED);
		return managerRepo.save(manager);
	}

	@GetMapping("/getById/{id}")
	public ResponseEntity<Manager> getById(@PathVariable Long id) {
		Manager manager = managerRepo.findById(id)
				.orElseThrow(() -> new RessourceNotFoundException("manager does not exist with this id "));
		return ResponseEntity.ok(manager);
	}

	@PutMapping("/updateManager/{id}")
	public ResponseEntity<Manager> updateManager(@PathVariable Long id, @RequestBody Manager managerDetails) {

		Manager manager = managerRepo.findById(id)
				.orElseThrow(() -> new RessourceNotFoundException("manager does not exist with this id "));
		manager.setFirstname(managerDetails.getFirstname());
		manager.setLastname(managerDetails.getLastname());
		manager.setEmail(managerDetails.getEmail());
		manager.setTel(managerDetails.getTel());
		manager.setAdress(managerDetails.getAdress());
		manager.setCivility(managerDetails.getCivility());
		manager.setBankAccount(managerDetails.getBankAccount());
		manager.setCin(managerDetails.getCin());
		manager.setDateOfBirth(managerDetails.getDateOfBirth());
		manager.setEducation(managerDetails.getEducation());
		manager.setSkills(managerDetails.getSkills());
		this.managerRepo.findWithUser(id).getUser().setEmail(managerDetails.getEmail());
		Manager updatedManager = managerRepo.save(manager);
		return ResponseEntity.ok(updatedManager);
	}

	@DeleteMapping("/deleteManager/{id}")
	public ResponseEntity<Map<String, Boolean>> deleteManager(@PathVariable Long id) {
		Manager manager = managerRepo.findById(id)
				.orElseThrow(() -> new RessourceNotFoundException("manager does not exist with this id "));
		managerRepo.delete(manager);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return ResponseEntity.ok(response);
	}

	@PostMapping("/uploadPicture/{id}")
	public ResponseEntity<String> uploadPicture(@PathVariable Long id, @RequestParam("picture") MultipartFile picture) {
		Manager manager = managerRepo.findById(id)
				.orElseThrow(() -> new RessourceNotFoundException("manager does not exist with this id "));
		try {
			manager.setPicture(picture.getBytes());
			managerRepo.save(manager);
			return ResponseEntity.ok("Picture uploaded successfully");
		} catch (Exception e) {
			return ResponseEntity.badRequest().body("Failed to upload picture");
		}
	}

}
