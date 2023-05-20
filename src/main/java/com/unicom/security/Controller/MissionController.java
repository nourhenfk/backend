package com.unicom.security.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.unicom.security.models.Employee;
import com.unicom.security.models.Mission;
import com.unicom.security.repos.EmployeeRepository;
import com.unicom.security.service.MissionService;

@RestController
@RequestMapping("/api/v1/auth/missions")
public class MissionController {

    @Autowired
    private MissionService missionService;
    
  

    @GetMapping("/AllMissions")
    public List<Mission> getAllMissions() {
        return missionService.getAllMissions();
    }

    @GetMapping("/getMissionById/{id}")
    public Mission getMissionById(@PathVariable Long id) {
        return missionService.getMissionById(id);
    }

    @PostMapping("/addMission")
    public Mission addMission(@RequestBody Mission mission) {
        return missionService.addMission(mission);
    }

    @PutMapping("/updateMission/{id}")
    public Mission updateMission(@PathVariable Long id, @RequestBody Mission mission) {
        return missionService.updateMission(id, mission);
    }

    @DeleteMapping("/deleteMission/{id}")
    public void deleteMission(@PathVariable Long id) {
        missionService.deleteMission(id);
    }
    
  

}

