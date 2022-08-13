package com.cognizant.emedicare.service;

import java.util.List;

import com.cognizant.emedicare.exception.PatientException;
import com.cognizant.emedicare.exception.UserException;
import com.cognizant.emedicare.model.Appointment;
import com.cognizant.emedicare.model.Doctor;
import com.cognizant.emedicare.model.Feedback;
import com.cognizant.emedicare.model.Patient;
import com.cognizant.emedicare.model.Report;

public interface IPatientService {
	public boolean registerPatient(Patient patient) throws PatientException;
	public List<Patient> verifyPatientLogin(Patient w) throws UserException ;
	public List<Patient> viewPatientDetails(long pid) throws PatientException;
	public List<Doctor> viewDoctor() throws PatientException;
	public boolean editPatient(Patient p,long pid) throws PatientException;
	
	public boolean bookAppointment(Appointment a,long did,long pid) throws PatientException;
	public List<Appointment> viewAppointmentForPatient(long pid) throws PatientException;
	public boolean deleteAppointmentForPatient(long appointmentid) throws PatientException;
	
	public List<Report> viewReport(long pid) throws PatientException;
	
	public List<Feedback> viewFeedback() throws PatientException;
	public boolean giveFeedback(Feedback f,long pid) throws PatientException;
}
