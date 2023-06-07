package com.unicom.security.models;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;

@Entity
public class Formation {
	@Id
	@GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
	private Long id;
	private String titre;
	private String description;
	private LocalDate dateDebut;
	private  LocalDate dateFin;
	private Status status;
	private  List<byte[]> documents;
	  @ManyToOne
	    @JoinColumn(name = "formateurEx_id")
	    private FormateurExterne formateurEx;
	   
	  @ManyToOne
	    @JoinColumn(name = "employee_id")
	    private Employee employee;
	  
	 
  @ManyToMany(cascade= CascadeType.PERSIST, fetch=FetchType.EAGER)
  @JoinTable(name = "employee_formation",
  joinColumns = @JoinColumn(name = "formation_id", referencedColumnName = "id"),
  inverseJoinColumns = @JoinColumn(name = "employee_id", referencedColumnName = "id"))
	    private List<Employee> participants = new ArrayList<>();
	
	
	public Formation() {
		super();
	}

	
	public Formation(Long id, String titre, String description, LocalDate dateDebut, LocalDate dateFin, Status status,
			 List<byte[]> documents, FormateurExterne formateurEx, Employee employee, List<Employee> participants) {
		super();
		this.id = id;
		this.titre = titre;
		this.description = description;
		this.dateDebut = dateDebut;
		this.dateFin = dateFin;
		this.status = status;
		this.documents = documents;
		this.formateurEx = formateurEx;
		this.employee=employee;
		this.participants = participants;
	}


	public  List<byte[]> getDocuments() {
		return documents;
	}


	public void setDocuments( List<byte[]> documents) {
		this.documents = documents;
	}


	public String getTitre() {
		return titre;
	}

	public void setTitre(String titre) {
		this.titre = titre;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public LocalDate getDateDebut() {
		return dateDebut;
	}

	public void setDateDebut(LocalDate dateDebut) {
		this.dateDebut = dateDebut;
	}

	public LocalDate getDateFin() {
		return dateFin;
	}

	public void setDateFin(LocalDate dateFin) {
		this.dateFin = dateFin;
	}


	public Status getStatus() {
		return status;
	}
	public void setStatus(Status status) {
		this.status = status;
	}
	public FormateurExterne getFormateurEx() {
		return formateurEx;
	}

	public void setFormateurEx(FormateurExterne formateurEx) {
		this.formateurEx = formateurEx;
	}
	
	
	
	public Employee getEmployee() {
		return employee;
	}


	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	@JsonIgnore
	public List<Employee> getParticipants() {
		return participants;
	}
	public void setParticipants(List<Employee> participants) {
		this.participants = participants;
	}

	public enum Status {
        EN_COURS,
        TERMINEE,
        ANNULEE,
        EN_PAUSE
    }
}
