package com.unicom.security.models;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.web.multipart.MultipartFile;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.unicom.security.user.User;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
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
private String civility;
private Integer cin;
private String skills;
private Integer bankAccount;
private Date dateOfBirth;


private byte[]  picture;
private String education;




@ManyToMany(mappedBy = "participants")
private List<Formation> formations = new ArrayList<>();


@OneToMany(mappedBy = "employee")
private List<Mission> missions;



public Employee() {
	super();
}



public Employee(User user, Long id, String firstname, String lastname, String email, Integer tel, String adress,
		String civility, Integer cin, String skills, Integer bankAccount, Date dateOfBirth, byte[] picture,
		String education, List<Formation> formations, List<Mission> missions) {
	super();
	this.user = user;
	this.id = id;
	this.firstname = firstname;
	this.lastname = lastname;
	this.email = email;
	this.tel = tel;
	this.adress = adress;
	this.civility = civility;
	this.cin = cin;
	this.skills = skills;
	this.bankAccount = bankAccount;
	this.dateOfBirth = dateOfBirth;
	this.picture = picture;
	this.education = education;
	this.formations = formations;
	this.missions = missions;
}


@JsonIgnore
public User getUser() {
	return user;
}
public void setUser(User user) {
	System.out.println("setUser");
	this.user = user; 
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
	return "Employee [ id=" + id + ", firstname=" + firstname + ", lastname=" + lastname + ", email="
			+ email + ", tel=" + tel + ", adress=" + adress + "]";
}
public void removeMission(Mission mission) {
    if (this.missions != null) {
        this.missions.remove(mission);
        mission.setEmployee(null);
    }
}
@JsonIgnore
public List<Mission> getAssignedMissions() {
    return missions;
}

public String getCivility() {
	return civility;
}
public void setCivility(String civility) {
	this.civility = civility;
}

public Integer getCin() {
	return cin;
}
public void setCin(Integer cin) {
	this.cin = cin;
}



public String getSkills() {
	return skills;
}



public void setSkills(String skills) {
	this.skills = skills;
}



public Integer getBankAccount() {
	return bankAccount;
}



public void setBankAccount(Integer bankAccount) {
	this.bankAccount = bankAccount;
}



public Date getDateOfBirth() {
	return dateOfBirth;
}
public void setDateOfBirth(Date dateOfBirth) {
	this.dateOfBirth = dateOfBirth;
}
public byte[] getPicture() {
	return picture;
}
public void setPicture(byte[] picture) {
	this.picture = picture;
}
@JsonIgnore
public List<Formation> getFormations() {
	return formations;
}
public void setFormations(List<Formation> formations) {
	this.formations = formations;
}



public String getEducation() {
	return education;
}



public void setEducation(String education) {
	this.education = education;
}
 

}
