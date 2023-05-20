package com.unicom.security.models;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;


@Entity
public class FormateurInterne extends Employee {
    
    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private Long id;

    private String specialite;
    
    
    @OneToMany(mappedBy = "formateurIn")
    private List<Formation> formations;
     
    public FormateurInterne() {
        super();
    }

   

    public FormateurInterne(Long id, String specialite, List<Formation> formations) {
		super();
		this.id = id;
		this.specialite = specialite;
		this.formations = formations;
	}



	public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSpecialite() {
        return specialite;
    }

    public void setSpecialite(String specialite) {
        this.specialite = specialite;
    }
}
