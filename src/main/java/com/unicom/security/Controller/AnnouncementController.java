package com.unicom.security.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.unicom.security.models.Announcement;
import com.unicom.security.service.AnnouncementService;


@RestController
@RequestMapping("/api/v1/auth/announcements")
public class AnnouncementController {
	
	 @Autowired
	    private AnnouncementService announcementService;
	    
	  

	    @GetMapping("/AllAnnouncement")
	    public List<Announcement> getAllAnnouncements() {
	        return announcementService.getAllAnnouncement();
	    }

	    @GetMapping("/getAnnouncementById/{id}")
	    public Announcement getAnnouncementById(@PathVariable Long id) {
	        return announcementService.getAnnouncementById(id);
	    }

	    @PostMapping("/addAnnouncement")
	    public Announcement addAnnouncement(@RequestBody Announcement announcement) {
	        return announcementService.addAnnouncement(announcement);
	    }

	    @PutMapping("/updateAnnouncement/{id}")
	    public Announcement updateAnnouncement(@PathVariable Long id, @RequestBody Announcement announcement) {
	        return announcementService.updateAnnouncement(id, announcement);
	    }

	    @DeleteMapping("/deleteAnnouncement/{id}")
	    public void deleteAnnouncement(@PathVariable Long id) {
	    	announcementService.deleteAnnouncement(id);
	    }
	    
}
