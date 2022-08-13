package com.cognizant.emedicare.model;

import java.sql.Date;
import java.sql.Time;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonFormatTypes;

@Entity
@Table(name="appointment")
public class Appointment {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long appointmentid;
	private Date appointmentdate;
	private String status; 
	
	private String appointmenttime;
	
	@ManyToOne
	@JoinColumn(name="pid")
	private Patient patient;
	
	
	@ManyToOne
	@JoinColumn(name="did")
	private Doctor doctor;
	
	public Appointment() {
		super();
	}

	public long getAppointmentid() {
		return appointmentid;
	}

	public void setAppointmentid(long appointmentid) {
		this.appointmentid = appointmentid;
	}

	public Date getAppointmentdate() {
		return appointmentdate;
	}

	public void setAppointmentdate(Date appointmentdate) {
		this.appointmentdate = appointmentdate;
	}

	public String getAppointmenttime() {
		return appointmenttime;
	}

	public void setAppointmenttime(String appointmenttime) {
		this.appointmenttime = appointmenttime;
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

	@Override
	public String toString() {
		return "Appointment [appointmentid=" + appointmentid + ", appointmentdate=" + appointmentdate
				+ ", appointmenttime=" + appointmenttime +", status="+status + ", patient=" + patient + ", doctor=" + doctor + "]";
	}

	public Appointment(long appointmentid, Date appointmentdate, String appointmenttime,String status, Patient patient, Doctor doctor) {
		super();
		this.appointmentid = appointmentid;
		this.appointmentdate = appointmentdate;
		this.appointmenttime = appointmenttime;
		this.status = status;
		this.patient = patient;
		this.doctor = doctor;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}



}
