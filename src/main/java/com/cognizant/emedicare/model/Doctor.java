package com.cognizant.emedicare.model;

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

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name="doctor")
public class Doctor {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long did;
	private String dname;
	@Column(unique = true)
	private String demail;
	private String dpassword;
	private String daddress;
	private String dqualification;
	private String dspecialization;
	private String dhospitalname;
	private float dfees;
	@Column(unique = true)
	private String dphonenumber;
	private String status;

	
	
	@OneToMany(mappedBy = "doctor",cascade = CascadeType.ALL,fetch=FetchType.LAZY)
	private List<Appointment> appointment;
	
	@OneToMany(mappedBy = "doctor",cascade = CascadeType.ALL,fetch=FetchType.LAZY)
	private List<Report> report;
	
	public Doctor() {
		super();
	}

	public long getDid() {
		return did;
	}

	public void setDid(long did) {
		this.did = did;
	}

	public String getDname() {
		return dname;
	}

	public void setDname(String dname) {
		this.dname = dname;
	}

	public String getDemail() {
		return demail;
	}

	public void setDemail(String demail) {
		this.demail = demail;
	}
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getDpassword() {
		return dpassword;
	}

	public void setDpassword(String dpassword) {
		this.dpassword = dpassword;
	}

	public String getDaddress() {
		return daddress;
	}

	public void setDaddress(String daddress) {
		this.daddress = daddress;
	}

	public String getDqualification() {
		return dqualification;
	}

	public void setDqualification(String dqualification) {
		this.dqualification = dqualification;
	}

	public String getDspecialization() {
		return dspecialization;
	}

	public void setDspecialization(String dspecialization) {
		this.dspecialization = dspecialization;
	}

	public String getDhospitalname() {
		return dhospitalname;
	}

	public void setDhospitalname(String dhospitalname) {
		this.dhospitalname = dhospitalname;
	}

	public float getDfees() {
		return dfees;
	}

	public void setDfees(float dfees) {
		this.dfees = dfees;
	}

	public String getDphonenumber() {
		return dphonenumber;
	}

	public void setDphonenumber(String dphonenumber) {
		this.dphonenumber = dphonenumber;
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
		return "Doctor [did=" + did + ", dname=" + dname + ", demail=" + demail + ", dpassword=" + dpassword
				+ ", daddress=" + daddress + ", dqualification=" + dqualification + ", dspecialization="
				+ dspecialization + ", dhospitalname=" + dhospitalname + ", dfees=" + dfees + ", dphonenumber="
				+ dphonenumber + ", status="+status+", appointment=" + appointment + ", report=" + report + "]";
	}

	public Doctor(long did, String dname, String demail, String dpassword, String daddress, String dqualification,
			String dspecialization, String dhospitalname, float dfees, String dphonenumber,String status,
			List<Appointment> appointment, List<Report> report) {
		super();
		this.did = did;
		this.dname = dname;
		this.demail = demail;
		this.dpassword = dpassword;
		this.daddress = daddress;
		this.dqualification = dqualification;
		this.dspecialization = dspecialization;
		this.dhospitalname = dhospitalname;
		this.dfees = dfees;
		this.dphonenumber = dphonenumber;
		this.status = status;
		this.appointment = appointment;
		this.report = report;
	}

}
