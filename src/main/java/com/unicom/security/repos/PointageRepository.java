package com.unicom.security.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.unicom.security.models.Pointage;
import com.unicom.security.user.User;

import org.springframework.stereotype.Repository;

@Repository
public interface PointageRepository extends JpaRepository<Pointage, Integer> {
    List<Pointage> findByUser(User user);
    
    @Query("SELECT p FROM Pointage p WHERE p.user = :user ORDER BY p.arrivalTime DESC")
    List<Pointage> findLatestPointageByUser(@Param("user") User user);
    
    @Query("SELECT p FROM Pointage p WHERE p.user = :user AND DATE(p.arrivalTime) = CURDATE()")
    List<Pointage> findTodayPointageByUser(@Param("user") User user);
}

