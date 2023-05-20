package com.unicom.security.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.unicom.security.models.Mood;
import com.unicom.security.service.MoodService;

@RestController
@RequestMapping("/api/v1/auth/moods")
public class MoodController {

    @Autowired
    private MoodService moodService;

    @GetMapping
    public ResponseEntity<List<Mood>> getAllMoods() {
        List<Mood> moods = moodService.getAllMoods();
        return new ResponseEntity<>(moods, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Mood> saveMood(@RequestBody Mood mood) {
        Mood savedMood = moodService.saveMood(mood);
        return new ResponseEntity<>(savedMood, HttpStatus.CREATED);
    }
}
