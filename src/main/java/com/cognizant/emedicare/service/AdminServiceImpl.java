package com.cognizant.emedicare.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cognizant.emedicare.exception.DoctorException;
import com.cognizant.emedicare.exception.PatientException;
import com.cognizant.emedicare.exception.UserException;
import com.cognizant.emedicare.model.Doctor;
import com.cognizant.emedicare.model.Feedback;
import com.cognizant.emedicare.model.Patient;
import com.cognizant.emedicare.repo.AdminDao;


@Service
public class AdminServiceImpl implements IAdminService {

	@Autowired
	private AdminDao dao;
	@Override
	public boolean verifyAdminLogin() throws UserException {
		// TODO Auto-generated method stub
		return false;
	}

	

	@Override
	public List<Feedback> viewFeedback() throws PatientException {
		return dao.viewFeedback();
	}

	@Override
	public boolean deactivateDoctor(long did) throws DoctorException {
		return dao.deactivateDoctor(did);
	}



	@Override
	public List<Doctor> viewDoctor() throws DoctorException {
		// TODO Auto-generated method stub
		return dao.viewDoctor();
	}

	@Override
	public List<Patient> viewPatient() throws PatientException {
		// TODO Auto-generated method stub
		return dao.viewPatient();
	}



	@Override
	public boolean approveDoctor(long did) throws DoctorException {
		return dao.approveDoctor(did);
	}

	

}
