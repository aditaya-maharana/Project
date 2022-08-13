package com.cognizant.emedicare.model;


import java.sql.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "patient")
public class Patient {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long pid;
	private String pname;
	private String pemail;
	
	private String ppassword;
	@Column(length = 100)
	private String paddress;
	private int page;
	private String psugar;
	private Date pregdate;
	@Column(unique = true)
	private String pphonenumber;
	
	
	public Patient() {
		super();
	}
	
	
	@OneToMany(mappedBy = "patient",cascade = CascadeType.ALL,fetch=FetchType.LAZY)
	private List<Appointment> appointment;

	@OneToMany(mappedBy = "patient",cascade = CascadeType.ALL,fetch=FetchType.LAZY)
	private List<Report> report;


	@OneToMany(mappedBy = "patient",cascade = CascadeType.ALL,fetch=FetchType.LAZY)
	private List<Feedback> feedback;
	
	public long getPid() {
		return pid;
	}

	public void setPid(long pid) {
		this.pid = pid;
	}

	public String getPname() {
		return pname;
	}

	public void setPname(String pname) {
		this.pname = pname;
	}

	public String getPemail() {
		return pemail;
	}

	public void setPemail(String pemail) {
		this.pemail = pemail;
	}

	public String getPpassword() {
		return ppassword;
	}

	public void setPpassword(String ppassword) {
		this.ppassword = ppassword;
	}

	public String getPaddress() {
		return paddress;
	}

	public void setPaddress(String paddress) {
		this.paddress = paddress;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public String getPsugar() {
		return psugar;
	}

	public void setPsugar(String psugar) {
		this.psugar = psugar;
	}

	public Date getPregdate() {
		return pregdate;
	}

	public void setPregdate(Date pregdate) {
		this.pregdate = pregdate;
	}

	public String getPphonenumber() {
		return pphonenumber;
	}

	public void setPphonenumber(String pphonenumber) {
		this.pphonenumber = pphonenumber;
	}

	public List<Appointment> getAppointment() {
		return appointment;
	}

	public void setAppointment(List<Appointment> appointment) {
		this.appointment = appointment;
	}

	public List<Report> getReport() {
		return report;
	}

	public void setReport(List<Report> report) {
		this.report = report;
	}

	@Override
	public String toString() {
		return "Patient [pid=" + pid + ", pname=" + pname + ", pemail=" + pemail + ", ppassword=" + ppassword
				+ ", paddress=" + paddress + ", page=" + page + ", psugar=" + psugar + ", pregdate=" + pregdate
				+ ", pphonenumber=" + pphonenumber + ", appointment=" + appointment + ", report=" + report + "]";
	}

	public Patient(long pid, String pname, String pemail, String ppassword, String paddress, int page, String psugar,
			Date pregdate, String pphonenumber, List<Appointment> appointment, List<Report> report) {
		super();
		this.pid = pid;
		this.pname = pname;
		this.pemail = pemail;
		this.ppassword = ppassword;
		this.paddress = paddress;
		this.page = page;
		this.psugar = psugar;
		this.pregdate = pregdate;
		this.pphonenumber = pphonenumber;
		this.appointment = appointment;
		this.report = report;
	}


	


	
}
