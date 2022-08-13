package com.cognizant.emedicare.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cognizant.emedicare.exception.PatientException;
import com.cognizant.emedicare.exception.UserException;
import com.cognizant.emedicare.model.Appointment;
import com.cognizant.emedicare.model.Doctor;
import com.cognizant.emedicare.model.Feedback;
import com.cognizant.emedicare.model.Patient;
import com.cognizant.emedicare.model.Report;
import com.cognizant.emedicare.repo.PatientDao;
import com.cognizant.emedicare.repo.UserDao;

@Service
public class PatientServiceImpl implements IPatientService {
	
	@Autowired
	private PatientDao patientDao;
	
	@Autowired
	private UserDao dao;
	
	@Override
	public boolean registerPatient(Patient patient) throws PatientException{
		return dao.registerPatient(patient);
	}

	@Override
	public List<Patient> verifyPatientLogin(Patient w) throws UserException {
		return dao.verifyPatientLogin(w);
	}

	@Override
	public List<Patient> viewPatientDetails(long pid) throws PatientException {
		return patientDao.viewPatientDetails(pid);
	}

	@Override
	public boolean editPatient(Patient p,long pid) throws PatientException {
		return patientDao.editPatient(p,pid);
	}

	@Override
	public List<Doctor> viewDoctor() throws PatientException {
		return patientDao.viewDoctor();
	}
	
	@Override
	public List<Appointment> viewAppointmentForPatient(long pid) throws PatientException {
		return patientDao.viewAppointmentForPatient(pid);
	}

	@Override
	public boolean deleteAppointmentForPatient(long appointmentid) throws PatientException {
		return patientDao.deleteAppointmentForPatient(appointmentid);
	}

	@Override
	public boolean bookAppointment(Appointment a,long did,long pid) throws PatientException {
		return patientDao.bookAppointment(a,did,pid);
	}

	@Override
	public List<Report> viewReport(long pid) throws PatientException {
		return patientDao.viewReport(pid);
	}

	@Override
	public List<Feedback> viewFeedback() throws PatientException {
		return patientDao.viewFeedback();
	}


	@Override
	public boolean giveFeedback(Feedback f, long pid) throws PatientException {
		return patientDao.giveFeedback(f, pid);
	}

	

}

