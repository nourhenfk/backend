package com.unicom.security.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.unicom.security.models.Formation;
import com.unicom.security.models.Mission;
import com.unicom.security.repos.FormationRepository;
import com.unicom.security.repos.MissionRepository;

@Service
public class FormationService {

    @Autowired
    private FormationRepository formationRepository;

    public List<Formation> getAllFormations() {
        return formationRepository.findAll();
    }

    public Formation getFormationById(Long id) {
        return formationRepository.findById(id)
                .orElseThrow();
    }

    public Formation addFormation(Formation formation) {
        return formationRepository.save(formation);
    }

    public Formation updateFormation(Long id, Formation formation) {
    	Formation existingFormation = formationRepository.findById(id)
                .orElseThrow();

    	existingFormation.setTitre(formation.getTitre());
    	existingFormation.setDescription(formation.getDescription());
    	existingFormation.setDateDebut(formation.getDateDebut());
    	existingFormation.setDateFin(formation.getDateFin());
    	existingFormation.setParticipants(formation.getParticipants());
    	existingFormation.setFormateurEx(formation.getFormateurEx());
    	existingFormation.setStatus(formation.getStatus());
    	existingFormation.setDocuments(formation.getDocuments());
    	existingFormation.setFormateurIn(formation.getFormateurIn());

        return formationRepository.save(existingFormation);
    }

    public void deleteFormation(Long id) {
    	formationRepository.deleteById(id);
    }
}
