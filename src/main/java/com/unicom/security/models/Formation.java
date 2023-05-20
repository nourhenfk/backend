package com.unicom.security.models;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
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
	private String documents;
	  @ManyToOne
	    @JoinColumn(name = "formateurEx_id")
	    private FormateurExterne formateurEx;
	   
	  @ManyToOne
	    @JoinColumn(name = "formateurIn_id")
	    private FormateurInterne formateurIn;
	  
	 
	  @ManyToMany(mappedBy = "formations")
	    private List<Employee> participants = new ArrayList<>();
	
	
	public Formation() {
		super();
	}

	
	public Formation(Long id, String titre, String description, LocalDate dateDebut, LocalDate dateFin, Status status,
			String documents, FormateurExterne formateurEx, FormateurInterne formateurIn, List<Employee> participants) {
		super();
		this.id = id;
		this.titre = titre;
		this.description = description;
		this.dateDebut = dateDebut;
		this.dateFin = dateFin;
		this.status = status;
		this.documents = documents;
		this.formateurEx = formateurEx;
		this.formateurIn = formateurIn;
		this.participants = participants;
	}


	public String getDocuments() {
		return documents;
	}


	public void setDocuments(String documents) {
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
	public FormateurInterne getFormateurIn() {
		return formateurIn;
	}
	public void setFormateurIn(FormateurInterne formateurIn) {
		this.formateurIn = formateurIn;
	}
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
