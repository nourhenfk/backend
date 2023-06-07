package com.unicom.security.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.unicom.security.auth.AuthenticationService;
import com.unicom.security.models.Department;
import com.unicom.security.models.Team;
import com.unicom.security.service.DepartmentService;
import com.unicom.security.service.TeamService;
import com.unicom.security.user.User;

@RestController
@RequestMapping("/api/v1/auth/teams")
public class TeamController {

    private final TeamService teamService;
    private final AuthenticationService authService;

    @Autowired
    public TeamController(TeamService teamService,AuthenticationService authService) {
        this.teamService = teamService;
        this.authService=authService;
    }

    @GetMapping("/getAllTeams")
    public List<Team> getAllTeams() {
        return teamService.getAllTeams();
    }

    @GetMapping("/getTeamById/{id}")
    public ResponseEntity<Team> getTeamById(@PathVariable("id") Long id) {
        Optional<Team> team = teamService.getTeamById(id);
        return team.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("createTeam")
    public ResponseEntity<Team> createTeam(@RequestBody Team team) {
        Team createdTeam = teamService.createTeam(team);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdTeam);
    }

    @PutMapping("/updateTeam/{id}")
    public ResponseEntity<Team> updateTeam(
            @PathVariable("id") Long id,
            @RequestBody Team updatedTeam
    ) {
        Team team = teamService.updateTeam(id, updatedTeam);
        return ResponseEntity.ok(team);
    }

    @DeleteMapping("/deleteTeam/{id}")
    public ResponseEntity<Void> deleteTeam(@PathVariable("id") Long id) {
    	teamService.deleteTeam(id);
        return ResponseEntity.noContent().build();
    }
    @GetMapping("/getTeamOfAuthenticatedUser")
    public ResponseEntity<Team> getTeamOfAuthenticatedUser() {
        User authenticatedUser = authService.getAuthenticatedUser();
        if (authenticatedUser != null) {
            Team team = teamService.getTeamByUser(authenticatedUser);
            if (team != null) {
                return ResponseEntity.ok(team);
            } else {
                return ResponseEntity.notFound().build();
            }
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

  
}


