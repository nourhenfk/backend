package com.unicom.security.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.unicom.security.models.Mood;
import com.unicom.security.repos.MoodRepository;

@Service
public class MoodService {

    @Autowired
    private MoodRepository moodRepository;

    public List<Mood> getAllMoods() {
        return moodRepository.findAll();
    }

    public Mood saveMood(Mood mood) {
        return moodRepository.save(mood);
    }
}

