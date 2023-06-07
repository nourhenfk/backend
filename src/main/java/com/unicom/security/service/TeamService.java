package com.unicom.security.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.unicom.security.models.Department;
import com.unicom.security.models.Team;
import com.unicom.security.repos.DepartmentRepository;
import com.unicom.security.repos.TeamRepository;
import com.unicom.security.user.User;

@Service
public class TeamService {
	  private final TeamRepository teamRepository;

	    @Autowired
	    public TeamService(TeamRepository teamRepository) {
	        this.teamRepository = teamRepository;
	    }

	    public List<Team> getAllTeams() {
	        return teamRepository.findAll();
	    }

	    public Optional<Team> getTeamById(Long id) {
	        return teamRepository.findById(id);
	    }

	    public Team createTeam(Team team) {
	        return teamRepository.save(team);
	    }

	    public Team updateTeam(Long id, Team updatedTeam) {
	    	Team team = teamRepository.findById(id)
	                .orElseThrow(() -> new IllegalArgumentException("Invalid department ID: " + id));

	    	team.setName(updatedTeam.getName());
	    	team.setDepartment(updatedTeam.getDepartment());
	     	team.setUsers(updatedTeam.getUsers());

	        return teamRepository.save(team);
	    }

	    public void deleteTeam(Long id) {
	    	teamRepository.deleteById(id);
	    }
	    public Team getTeamByUser(User user) {
	        return teamRepository.findByUsers(user);
	    }
}
