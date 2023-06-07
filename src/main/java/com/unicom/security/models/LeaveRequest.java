package com.unicom.security.models;

import java.util.Date;
import java.util.List;

import com.unicom.security.user.User;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name = "leave_request")
public class LeaveRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Temporal(TemporalType.DATE)
    private Date startDate;

    @Temporal(TemporalType.DATE)
    private Date endDate;

    @Lob
    @ElementCollection
    private List<byte[]> documents;

    private String reason;

    @Enumerated(EnumType.STRING)
    private RequestStatus status;

  

    public LeaveRequest() {
    }

    public LeaveRequest(User user, Date startDate, Date endDate, List<byte[]> documents, String reason) {
        this.user = user;
        this.startDate = startDate;
        this.endDate = endDate;
        this.documents = documents;
        this.reason = reason;
        this.status = RequestStatus.PENDING; // Default status is set to "Pending"
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public List<byte[]> getDocuments() {
		return documents;
	}

	public void setDocuments(List<byte[]> documents) {
		this.documents = documents;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public RequestStatus getStatus() {
		return status;
	}

	public void setStatus(RequestStatus status) {
		this.status = status;
	}

	
  
 

}

