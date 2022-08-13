package com.cognizant.emedicare.service;

import java.util.List;

import com.cognizant.emedicare.exception.DoctorException;
import com.cognizant.emedicare.exception.PatientException;
import com.cognizant.emedicare.exception.UserException;
import com.cognizant.emedicare.model.Doctor;
import com.cognizant.emedicare.model.Feedback;
import com.cognizant.emedicare.model.Patient;

public interface IAdminService {
	public boolean verifyAdminLogin() throws UserException;
	public List<Doctor> viewDoctor() throws DoctorException;
	public List<Patient> viewPatient() throws PatientException;
	public List<Feedback> viewFeedback() throws PatientException;
	public boolean deactivateDoctor(long did) throws DoctorException;
	public boolean approveDoctor(long did) throws DoctorException;
}
