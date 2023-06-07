package com.unicom.security.user;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.util.Collection;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;

import lombok.NoArgsConstructor;


import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.unicom.security.models.Department;
import com.unicom.security.models.Employee;
import com.unicom.security.models.LeaveRequest;
import com.unicom.security.models.Manager;

import com.unicom.security.models.Pointage;

import com.unicom.security.models.RequestStatus;
import com.unicom.security.models.Team;
import com.unicom.security.token.Token;


@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "_user")
public class User implements UserDetails {

  @Id
  @GeneratedValue
  private Integer id;
  @OneToOne(mappedBy = "user")
  private Employee employee;
  @OneToOne(mappedBy = "user")
  private Manager manager;
  
  private String firstname;
  private String lastname;
  private String email;
  private String password;
  

  @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
  private List<LeaveRequest> leaveRequests;

  
  @OneToMany(mappedBy = "user")
  private List<Pointage> pointages;

 
  
  @ManyToOne
  @JoinColumn(name = "department_id")
  private Department department;
  
  @ManyToOne
  @JoinColumn(name = "team_id")
  private Team team;
  
  @Enumerated(EnumType.STRING)
  private Role role = Role.Employee;
  

  @Enumerated(EnumType.STRING)
  private RequestStatus status;

  

  @OneToMany(mappedBy = "user")
  private List<Token> tokens;

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return List.of(new SimpleGrantedAuthority(role.name()));
  }

  @Override
  public String getPassword() {
    return password;
  }

 
  public String getEmail() {
    return email;
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return true;
  }

public String getRole() {
	// TODO Auto-generated method stub
	
	return this.role.toString();
}

public Integer getId() {
	// TODO Auto-generated method stub
	return id;
}

public RequestStatus getStatus() {
	return status;
}

public void setStatus(RequestStatus status) {
	this.status = status;
}

@Override
public String getUsername() {
	// TODO Auto-generated method stub
	return null;
}

@JsonIgnore

public Employee getEmployee() {
	return employee;
}

public void setEmployee(Employee employee) {
	this.employee = employee;
}

public Manager getManager() {
	return manager;
}

public void setManager(Manager manager) {
	this.manager = manager;
}

public void setPassword(String password)
{
	this.password =password;
}

public void setEmail(String email) {
	this.email = email;
}

@JsonIgnore
public List<Pointage> getPointages() {
	return pointages;
}

public void setPointages(List<Pointage> pointages) {
	this.pointages = pointages;
}

@JsonIgnore
public List<LeaveRequest> getLeaveRequests() {
    return leaveRequests;
}

public void setLeaveRequests(List<LeaveRequest> leaveRequests) {
    this.leaveRequests = leaveRequests;
}

public Department getDepartment() {
	return department;
}

public void setDepartment(Department department) {
	this.department = department;
}

public Team getTeam() {
	return team;
}

public void setTeam(Team team) {
	this.team = team;
}




@JsonIgnore
public List<Token> getTokens() {
	return tokens;
}

public void setTokens(List<Token> tokens) {
	this.tokens = tokens;
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


public void setId(Integer id) {
	this.id = id;
}

public void setRole(Role role) {
	this.role = role;
}


}
