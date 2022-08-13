package com.cognizant.emedicare.service;

import java.util.List;

import com.cognizant.emedicare.exception.DoctorException;
import com.cognizant.emedicare.exception.UserException;
import com.cognizant.emedicare.model.Appointment;
import com.cognizant.emedicare.model.Doctor;
import com.cognizant.emedicare.model.Report;

public interface IDoctorService {
	public boolean registerDoctor(Doctor doctor) throws DoctorException;
	public List<Doctor> verifyDoctorLogin(Doctor w) throws UserException;
	public List<Doctor> viewDoctorDetails(long did) throws DoctorException;
	public boolean editDoctor(Doctor d,long did) throws DoctorException;
	
	public List<Appointment> viewAppointmentForDoctor(long did) throws DoctorException;
	public boolean deleteAppointmentForDoctor(long appointmentid) throws DoctorException;
	public boolean giveReport(Report r,long did,long pid) throws DoctorException;
	public boolean appointmentStatus(long appointmentid) throws DoctorException;
}
