package com.unicom.security.service;
import java.util.List;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.unicom.security.models.FormateurExterne;

import com.unicom.security.repos.FormateurExRepository;

@Service
public class FormateurExService {
	 private FormateurExRepository formateurExRepository;

	    @Autowired
	    public FormateurExService(FormateurExRepository formateurExRepository) {
	        this.formateurExRepository = formateurExRepository;
	    }

	    public List<FormateurExterne> getAllFormateurs() {
	        return formateurExRepository.findAll();
	    }


	    public FormateurExterne getFormateurById(Long id) {
	        return formateurExRepository.findById(id)
	                .orElseThrow();
	    }

	    public FormateurExterne addFormateur(FormateurExterne formateur) {
	        return formateurExRepository.save(formateur);
	    }

	    public FormateurExterne updateFormateur(Long formateurId, FormateurExterne formateurDetails)
	            {
	        FormateurExterne formateur = formateurExRepository.findById(formateurId)
	                .orElseThrow();

	        formateur.setPrenom(formateurDetails.getPrenom());
	        formateur.setNom(formateurDetails.getNom());
	        formateur.setPhone(formateurDetails.getPhone());
	        formateur.setCompany(formateurDetails.getCompany());
	       

	        final FormateurExterne updatedFormateur = formateurExRepository.save(formateur);
	        return updatedFormateur;
	    }

	    public void deleteFormateur(Long id) {
	    	formateurExRepository.deleteById(id);
	    }
	}

