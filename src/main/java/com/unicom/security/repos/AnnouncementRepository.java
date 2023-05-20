package com.unicom.security.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.unicom.security.models.Announcement;


public interface AnnouncementRepository extends JpaRepository<Announcement, Long> {

}
