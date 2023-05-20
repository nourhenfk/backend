package com.unicom.security.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.unicom.security.user.User;

@Entity
public class Pointage {
    @Id
    @GeneratedValue
    private Integer id;
    
    private LocalDateTime arrivalTime;
    private LocalDateTime departureTime;
    
    @ManyToOne
    private User user;
    
    
    
    
	public Pointage(Integer id, LocalDateTime arrivalTime, LocalDateTime departureTime, User user) {
		super();
		this.id = id;
		this.arrivalTime = arrivalTime;
		this.departureTime = departureTime;
		this.user = user;
	}
	
	@JsonIgnore
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Pointage() {
		super();
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public LocalDateTime getArrivalTime() {
		return arrivalTime;
	}
	public void setArrivalTime(LocalDateTime arrivalTime) {
		this.arrivalTime = arrivalTime;
	}
	public LocalDateTime getDepartureTime() {
		return departureTime;
	}
	public void setDepartureTime(LocalDateTime departureTime) {
		this.departureTime = departureTime;
	}
    
    
}

