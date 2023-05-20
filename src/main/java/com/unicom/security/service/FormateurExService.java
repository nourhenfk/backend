package com.unicom.security.service;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

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
	        formateur.setPhotoUrl(formateurDetails.getPhotoUrl());

	        final FormateurExterne updatedFormateur = formateurExRepository.save(formateur);
	        return updatedFormateur;
	    }

	    public void deleteFormateur(Long id) {
	    	formateurExRepository.deleteById(id);
	    }
	    
	    
	  
	    public FormateurExterne updatePhotoById(Long id, byte[] photo) {
	        Optional<FormateurExterne> existingUserOptional = formateurExRepository.findById(id);

	        if (existingUserOptional.isPresent()) {
	        	FormateurExterne existingUser = existingUserOptional.get();

	            // generate a unique URL for the photo
	            String photoUrl = "https://example.com/photos/" + UUID.randomUUID().toString();

	            // set the photo URL and save the user entity
	            existingUser.setPhotoUrl(photoUrl);
	            existingUser.setPhoto(photo);
	            return formateurExRepository.save(existingUser);
	        } else {
	            throw new RuntimeException("User not found");
	        }
	    }
	}

