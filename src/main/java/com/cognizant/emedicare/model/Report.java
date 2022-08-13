package com.cognizant.emedicare.model;

import java.sql.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="report")
public class Report {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long rid;
	private Date rdate;
	private String eprescription;

	
	
	@ManyToOne(cascade = CascadeType.ALL,fetch=FetchType.LAZY)
	@JoinColumn(name="pid")
	private Patient patient;
	
	@ManyToOne
	@JoinColumn(name="did")
	private Doctor doctor;
	
	
	public Report() {
		super();
	}

	public long getRid() {
		return rid;
	}

	public void setRid(long rid) {
		this.rid = rid;
	}

	public Date getRdate() {
		return rdate;
	}

	public void setRdate(Date rdate) {
		this.rdate = rdate;
	}

	public String getEprescription() {
		return eprescription;
	}

	public void setEprescription(String eprescription) {
		this.eprescription = eprescription;
	}

	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	public Doctor getDoctor() {
		return doctor;
	}

	public void setDoctor(Doctor doctor) {
		this.doctor = doctor;
	}

	public Report(long rid, Date rdate, String eprescription, Patient patient, Doctor doctor) {
		super();
		this.rid = rid;
		this.rdate = rdate;
		this.eprescription = eprescription;
		this.patient = patient;
		this.doctor = doctor;
	}

	@Override
	public String toString() {
		return "Report [rid=" + rid + ", rdate=" + rdate + ", eprescription=" + eprescription + ", patient=" + patient
				+ ", doctor=" + doctor + "]";
	}
	
	
}
