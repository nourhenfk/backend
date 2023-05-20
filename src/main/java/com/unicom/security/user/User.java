package com.unicom.security.user;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.util.Collection;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.unicom.security.models.Employee;
import com.unicom.security.models.Manager;
import com.unicom.security.models.Pointage;
import com.unicom.security.models.RequestStatus;
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
  
  @OneToMany(mappedBy = "user")
  private List<Pointage> pointages;

  


  @Enumerated(EnumType.STRING)
  private Role role;
  

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

}
