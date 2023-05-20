package com.unicom.security.models;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.OneToMany;

@Entity
public class FormateurExterne {
 @Id
 @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
	private Long id ;
	private String prenom;
	private String nom ;
	private int phone;
	private String company ;
	@Lob
    @Column(name = "photo", nullable = true, length = 1048576)
    private byte[] photo;
    @Column(name = "photo_url")
    private String photoUrl;
	
	
	@OneToMany(mappedBy = "formateurEx")
	private List<Formation> formations;
	 
	
	
	public FormateurExterne() {
		super();
	}
	
	public FormateurExterne(Long id, String prenom, String nom, int phone, String company, byte[] photo,
			String photoUrl, List<Formation> formations) {
		super();
		this.id = id;
		this.prenom = prenom;
		this.nom = nom;
		this.phone = phone;
		this.company = company;
		this.photo = photo;
		this.photoUrl = photoUrl;
		this.formations = formations;
	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public int getPhone() {
		return phone;
	}
	public void setPhone(int phone) {
		this.phone = phone;
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}

	public byte[] getPhoto() {
		return photo;
	}

	public void setPhoto(byte[] photo) {
		this.photo = photo;
	}

	public String getPhotoUrl() {
		return photoUrl;
	}

	public void setPhotoUrl(String photoUrl) {
		this.photoUrl = photoUrl;
	}

	public List<Formation> getFormations() {
		return formations;
	}

	public void setFormations(List<Formation> formations) {
		this.formations = formations;
	}
	
	
	
}
