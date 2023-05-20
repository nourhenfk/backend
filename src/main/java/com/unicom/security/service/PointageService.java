package com.unicom.security.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.unicom.security.models.Pointage;
import com.unicom.security.repos.PointageRepository;
import com.unicom.security.user.User;

@Service
public class PointageService {
    private final PointageRepository pointageRepository;

    public PointageService(PointageRepository pointageRepository) {
        this.pointageRepository = pointageRepository;
    }

    public void pointer(User user) {
        Pointage pointage = new Pointage();
        pointage.setArrivalTime(LocalDateTime.now());
        pointage.setUser(user);

        pointageRepository.save(pointage);
    }

    public void terminerPointage(User user) {
        Pointage pointage = pointageRepository.findLatestPointageByUser(user).get(0);
        if (pointage != null && pointage.getDepartureTime() == null) {
            pointage.setDepartureTime(LocalDateTime.now());
            pointageRepository.save(pointage);
        }
    }

    public List<Pointage> getAllPointagesByUser(User user) {
        return pointageRepository.findByUser(user);
    }
}
