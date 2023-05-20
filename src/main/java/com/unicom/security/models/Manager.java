package com.unicom.security.models;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.unicom.security.user.User;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="manager")
public class Manager {
	@Id
	@GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
private Long id ;
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "user_id", referencedColumnName = "id")
	private User user;
private String firstname;
private String lastname;
private String email;
private Integer tel ;
private String adress;
public Manager() {
}

public User getUser() {
	return user;
}

public Manager(Long id, String firstname, String lastname, String email, Integer tel, String adress) {
	super();
	this.id = id;
	this.firstname = firstname;
	this.lastname = lastname;
	this.email = email;
	this.tel = tel;
	this.adress = adress;
}

public void setUser(User user) {
	System.out.println("setUser invoked");
	this.user = user;
}

public Manager(Long id, String firstname, String lastname, String email, Integer tel, String adress,User user) {
	super();
	this.id = id;
	this.firstname = firstname;
	this.lastname = lastname;
	this.email = email;
	this.tel = tel;
	this.adress = adress;
	this.user=user;
}


@Override
public String toString() {
	return "ResponsableRH [ firstname=" + firstname + ", lastname=" + lastname + ", email=" + email
			+ ", tel=" + tel + ", adresse=" + adress + "]";
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




}
