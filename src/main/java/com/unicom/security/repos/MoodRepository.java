package com.unicom.security.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.unicom.security.models.Mood;

public interface MoodRepository extends JpaRepository<Mood, Long> {

}

