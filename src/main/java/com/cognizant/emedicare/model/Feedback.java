package com.cognizant.emedicare.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="feedback")
public class Feedback {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long feedbackid;
	private String feedback;
	private String dName;
	
	@ManyToOne
	@JoinColumn(name="pid")
	private Patient patient;
	
	public Feedback() {
		super();
	}

	public long getFeedbackid() {
		return feedbackid;
	}

	public void setFeedbackid(long feedbackid) {
		this.feedbackid = feedbackid;
	}

	public String getFeedback() {
		return feedback;
	}

	public void setFeedback(String feedback) {
		this.feedback = feedback;
	}

	public String getdName() {
		return dName;
	}

	public void setdName(String dName) {
		this.dName = dName;
	}

	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	public Feedback(long feedbackid, String feedback, String dName, Patient patient) {
		super();
		this.feedbackid = feedbackid;
		this.feedback = feedback;
		this.dName = dName;
		this.patient = patient;
	}

	@Override
	public String toString() {
		return "Feedback [feedbackid=" + feedbackid + ", feedback=" + feedback + ", dName=" + dName + ", patient="
				+ patient + "]";
	}

}
