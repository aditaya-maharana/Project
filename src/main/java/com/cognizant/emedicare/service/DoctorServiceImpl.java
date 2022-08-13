package com.cognizant.emedicare.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cognizant.emedicare.exception.DoctorException;
import com.cognizant.emedicare.exception.UserException;
import com.cognizant.emedicare.model.Appointment;
import com.cognizant.emedicare.model.Doctor;
import com.cognizant.emedicare.model.Patient;
import com.cognizant.emedicare.model.Report;
import com.cognizant.emedicare.repo.DoctorDao;
import com.cognizant.emedicare.repo.UserDao;


@Service
public class DoctorServiceImpl implements IDoctorService {

	
	@Autowired
	private UserDao dao;
	
	@Autowired
	private DoctorDao doctorDao;
	
	@Override
	public boolean registerDoctor(Doctor doctor) throws DoctorException {
		return dao.registerDoctor(doctor);
	}

	@Override
	public List<Doctor> verifyDoctorLogin(Doctor w) throws UserException {
		return dao.verifyDoctorLogin(w);
	}

	@Override
	public List<Doctor> viewDoctorDetails(long did) throws DoctorException{
		return doctorDao.viewDoctorDetails(did);
	}

	@Override
	public boolean editDoctor(Doctor d,long did) throws DoctorException {
		return doctorDao.editDoctor(d,did);
	}

	@Override
	public List<Appointment> viewAppointmentForDoctor(long did) throws DoctorException {
		return doctorDao.viewAppointmentForDoctor(did);
	}

	@Override
	public boolean deleteAppointmentForDoctor(long appointmentid) throws DoctorException {
		return doctorDao.deleteAppointmentForDoctor(appointmentid);
	}


	@Override
	public boolean giveReport(Report r, long did, long pid) throws DoctorException {
		return doctorDao.giveReport(r, did, pid);
	}

	@Override
	public boolean appointmentStatus(long appointmentid) throws DoctorException {
		return doctorDao.appointmentStatus(appointmentid);
	}


}
