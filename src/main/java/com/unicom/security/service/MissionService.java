package com.unicom.security.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.unicom.security.models.Mission;
import com.unicom.security.repos.MissionRepository;

@Service
public class MissionService {

    @Autowired
    private MissionRepository missionRepository;

    public List<Mission> getAllMissions() {
        return missionRepository.findAll();
    }

    public Mission getMissionById(Long id) {
        return missionRepository.findById(id)
                .orElseThrow();
    }

    public Mission addMission(Mission mission) {
        return missionRepository.save(mission);
    }

    public Mission updateMission(Long id, Mission mission) {
        Mission existingMission = missionRepository.findById(id)
                .orElseThrow();

        existingMission.setTitre(mission.getTitre());
        existingMission.setDescription(mission.getDescription());
        existingMission.setDateDebut(mission.getDateDebut());
        existingMission.setDateFin(mission.getDateFin());
        existingMission.setTempsEstime(mission.getTempsEstime());
        existingMission.setTempsPasse(mission.getTempsPasse());
        existingMission.setStatus(mission.getStatus());
        existingMission.setEmployee(mission.getEmployee());

        return missionRepository.save(existingMission);
    }

    public void deleteMission(Long id) {
        missionRepository.deleteById(id);
    }
}
