package com.unicom.security.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.unicom.security.models.Announcement;
import com.unicom.security.models.Formation;
import com.unicom.security.repos.AnnouncementRepository;
import com.unicom.security.repos.FormationRepository;

@Service
public class AnnouncementService {

	 @Autowired
	    private AnnouncementRepository announcementRepository;

	    public List<Announcement> getAllAnnouncement() {
	        return announcementRepository.findAll();
	    }

	    public Announcement getAnnouncementById(Long id) {
	        return announcementRepository.findById(id)
	                .orElseThrow();
	    }

	    public Announcement addAnnouncement(Announcement announcement) {
	        return announcementRepository.save(announcement);
	    }

	    public Announcement updateAnnouncement(Long id, Announcement announcement) {
	    	Announcement existingAnnouncement = announcementRepository.findById(id)
	                .orElseThrow();

	    	existingAnnouncement.setType(announcement.getType());
	    	existingAnnouncement.setImportance(announcement.getImportance());
	    	existingAnnouncement.setPublicationDate(announcement.getPublicationDate());
	    	existingAnnouncement.setSubject(announcement.getSubject());
	    	existingAnnouncement.setText(announcement.getText());
	    	existingAnnouncement.setStatus(announcement.getStatus());
	    	

	        return announcementRepository.save(existingAnnouncement);
	    }

	    public void deleteAnnouncement(Long id) {
	    	announcementRepository.deleteById(id);
	    }
}
