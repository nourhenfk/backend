package com.unicom.security.models;


import java.util.ArrayList;

import java.util.List;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.unicom.security.user.User;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
 
@Entity
@Table(name="employee")
@JsonIgnoreProperties({"hibernateLazyInitializer"})
public class Employee {

@OneToOne(cascade = CascadeType.ALL)
@JoinColumn(name = "user_id", referencedColumnName = "id")
private User user;

	@Id
	@GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
private Long id ;
	
private String firstname; 
private String lastname;
private String email;
private Integer tel ;
private String adress;

@ManyToMany(cascade = CascadeType.ALL)
@JoinTable(name = "employee_formation",
    joinColumns = @JoinColumn(name = "employee_id", referencedColumnName = "id"),
    inverseJoinColumns = @JoinColumn(name = "formation_id", referencedColumnName = "id"))
private List<Formation> formations = new ArrayList<>();

@OneToMany(mappedBy = "employee")
private List<Mission> missions;
 


public User getUser() {
	return user;
}
public void setUser(User user) {
	System.out.println("setUser");
	this.user = user; 
}
public Employee() {
	super();
}


public Employee(Long id, String firstname, String lastname, String email, Integer tel, String adress,User user) {
	super();
	this.id = id;
	this.firstname = firstname;
	this.lastname = lastname;
	this.email = email;
	this.tel = tel;
	this.adress = adress;
	System.out.println("this constructor");
	this.user=user;

}
public Long getId() {
	return id;
}
public void setId(Long id) {
	this.id = id;
}
public String getFirstname() {
	return firstname;
}
public void setFirstname(String firstname) {
	System.out.println("setFirstname");
	this.firstname = firstname;
}
public String getLastname() {
	return lastname;
}
public void setLastname(String lastname) {
	this.lastname = lastname;
}
public String getEmail() {
	return email;
}
public void setEmail(String email) {
	this.email = email;
}
public Integer getTel() {
	return tel;
}
public void setTel(Integer tel) {
	this.tel = tel;
}
public String getAdress() {
	return adress;
}
public void setAdress(String adress) {
	this.adress = adress;
}

@JsonIgnore

public List<Mission> getMissions() {
    return missions;
}

public void setMissions(List<Mission> missions) {
    this.missions = missions;
}

public void addMission(Mission mission) {
    if (this.missions == null) {
        this.missions = new ArrayList<>();
    }
    this.missions.add(mission);
    mission.setEmployee(this);
}

@Override
public String toString() {
	return "Employee [user=" + user + ", id=" + id + ", firstname=" + firstname + ", lastname=" + lastname + ", email="
			+ email + ", tel=" + tel + ", adress=" + adress + ", formations=" + formations + ", missions=" + missions
			+ "]";
}
public void removeMission(Mission mission) {
    if (this.missions != null) {
        this.missions.remove(mission);
        mission.setEmployee(null);
    }
}

 

}
