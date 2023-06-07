package com.unicom.security.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import com.unicom.security.models.Pointage;
import com.unicom.security.service.PointageService;
import com.unicom.security.user.User;
import com.unicom.security.user.UserRepository;

@RestController
@RequestMapping("/api/v1/auth/pointages")
public class PointageController {
    private final PointageService pointageService;
    private final UserRepository userRepository;

    public PointageController(PointageService pointageService, UserRepository userRepository) {
        this.pointageService = pointageService;
        this.userRepository = userRepository;
    }

    private Integer getUserIdFromAuthentication() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        String username = ((User) authentication.getPrincipal()).getEmail();

        // Retrieve the user ID from your UserRepository based on the username
        Optional<User> optionalUser = userRepository.findByEmail(username);
        if (optionalUser.isPresent()) {
            return optionalUser.get().getId();
        } else {
            throw new RuntimeException("User not found");
        }
    }

    @PostMapping("/pointer")
    public ResponseEntity<String> pointer() {
        Integer userId = getUserIdFromAuthentication();
        Optional<User> optionalUser = userRepository.findById(userId);
        if (optionalUser.isPresent()) {
            pointageService.pointer(optionalUser.get());
            return ResponseEntity.status(HttpStatus.CREATED).body("Pointage added successfully.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found.");
        }
    }

    @PostMapping("/terminer-pointage")
    public ResponseEntity<String> terminerPointage() {
        Integer userId = getUserIdFromAuthentication();
        Optional<User> optionalUser = userRepository.findById(userId);
        if (optionalUser.isPresent()) {
            pointageService.terminerPointage(optionalUser.get());
            return ResponseEntity.ok("Pointage completed successfully.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found.");
        }
    }

    @GetMapping("/todayPointage")
    public ResponseEntity<Pointage> getTodayPointage() {
        Integer userId = getUserIdFromAuthentication();
        Optional<User> optUser = userRepository.findById(userId);
        if (optUser.isPresent()) {
            Pointage todayPointage = pointageService.getTodayPointage(optUser.get());
            if (todayPointage != null) {
                calculateAndSetProductionHours(todayPointage); // Calculate and set production hours
                return ResponseEntity.ok(todayPointage);
            } else {
                return ResponseEntity.ok(new Pointage());
            }
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @GetMapping("/getAllPointagesByUser")
    public ResponseEntity<List<Pointage>> getAllPointagesByUser() {
        Integer userId = getUserIdFromAuthentication();
        Optional<User> optionalUser = userRepository.findById(userId);
        if (optionalUser.isPresent()) {
            List<Pointage> pointages = pointageService.getAllPointagesByUser(optionalUser.get());
            pointages.forEach(this::calculateAndSetProductionHours); 
            return ResponseEntity.ok(pointages);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }
    
    @GetMapping("/getAllPointages")
    public ResponseEntity<List<Pointage>> getAllPointages() {
        List<Pointage> pointages = pointageService.getAllPointages();
        pointages.forEach(this::calculateAndSetProductionHours); 
        return ResponseEntity.ok(pointages);
    }

   
    private void calculateAndSetProductionHours(Pointage pointage) {
        long productionHours = pointage.getProductionHours();
        pointage.setProductionHours(productionHours);
    }}
