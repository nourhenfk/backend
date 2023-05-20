package com.unicom.security.models;


import java.time.LocalDate;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
 
@Entity
@Table(name="mission")
public class Mission {
	@Id
	@GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private Long id ; 
    private String titre;
    private String description;
    private LocalDate dateDebut;
    private LocalDate dateFin;
    private Double tempsEstime;
    private Double tempsPasse;
    private String status;
    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;


    public Mission(Long id ,String titre, String description, LocalDate dateDebut, LocalDate dateFin, Double tempsEstime,
            Double tempsPasse, String status, Employee employee) {
        super();
        this.id=id;
        this.titre = titre;
        this.description = description;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.tempsEstime = tempsEstime;
        this.tempsPasse = tempsPasse;
        this.status = status;
        this.employee = employee;
    }
    
    
    
    public Mission() {
		super();
	}



	public Long getId() {
		return id;
	}



	public void setId(Long id) {
		this.id = id;
	}



	public String getTitre() {
        return titre;
    }
    public void setTitre(String titre) {
        this.titre = titre;
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
    public Double getTempsEstime() {
        return tempsEstime;
    }
    public void setTempsEstime(Double tempsEstime) {
        this.tempsEstime = tempsEstime;
    }
    public Double getTempsPasse() {
        return tempsPasse;
    }
    public void setTempsPasse(Double tempsPasse) {
        this.tempsPasse = tempsPasse;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public Employee getEmployee() {
        return employee;
    }
    public void setEmployee(Employee employee) {
        this.employee = employee;
    }
}

