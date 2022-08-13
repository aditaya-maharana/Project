package com.cognizant.emedicare.repo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.cognizant.emedicare.exception.DoctorException;
import com.cognizant.emedicare.exception.PatientException;
import com.cognizant.emedicare.model.Doctor;
import com.cognizant.emedicare.model.Feedback;
import com.cognizant.emedicare.model.Patient;
import com.cognizant.emedicare.repo.PatientDao.DoctorResult;
import com.cognizant.emedicare.repo.PatientDao.FeedbackRowmapper;

@Component
public class AdminDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	

	public List<Patient> viewPatient() throws PatientException{
		List<Patient> p = new ArrayList<Patient>();
		try {
			p = jdbcTemplate.query("SELECT pid,pname,pemail,pphonenumber,page,paddress,psugar,pregdate FROM patient", new PatientResult());
		} catch (Exception e) {
			throw new PatientException("No Patient is available, try after Sometimes");
		}
		return p;
	}
	public class PatientResult implements RowMapper<Patient> {
		
		public Patient mapRow(ResultSet rs,int rowNum) throws SQLException
		{
			Patient patient = new Patient();
			patient.setPid(rs.getLong(1));
			patient.setPname(rs.getString(2));
			patient.setPemail(rs.getString(3));
			patient.setPphonenumber(rs.getString(4));
			
			patient.setPage(rs.getInt(5));
			patient.setPaddress(rs.getString(6));
			patient.setPsugar(rs.getString(7));
			patient.setPregdate(rs.getDate(8));
			return patient;
		}
	
	}

	
	public List<Doctor> viewDoctor() throws DoctorException{
		List<Doctor> d = new ArrayList<Doctor>();
		try {
			d = jdbcTemplate.query("SELECT did,dname,demail,dphonenumber,dspecialization,dqualification"
					+ ",dhospitalname,dfees,daddress,status FROM doctor", new DoctorResult());
		} catch (Exception e) {
			throw new DoctorException("No Doctor is available, try after Sometimes");
		}
		return d;
	}
	
	
	
	public class DoctorResult implements RowMapper<Doctor> {
			
			public Doctor mapRow(ResultSet rs,int rowNum) throws SQLException
			{
				Doctor doctor = new Doctor();
				doctor.setDid(rs.getLong(1));
				doctor.setDname(rs.getString(2));
				doctor.setDemail(rs.getString(3));
				doctor.setDphonenumber(rs.getString(4));
				doctor.setDspecialization(rs.getString(5));
				doctor.setDqualification(rs.getString(6));
				doctor.setDhospitalname(rs.getString(7));
				doctor.setDfees(rs.getFloat(8));
				doctor.setDaddress(rs.getString(9));
				doctor.setStatus(rs.getString(10));
				return doctor;
			}
		
		}
	
	public boolean approveDoctor(long did) throws DoctorException {

		boolean isEdited = false;
		int status = jdbcTemplate.update(
				"update doctor set status = 'Verified' where did = ?",did);

		try {
			if (status != 0) {
				isEdited = true;
			}
		} catch (Exception e) {

			throw new DoctorException("Invalid doctorId");
		}

		return isEdited;
	}


	
	
	public List<Feedback> viewFeedback() throws PatientException{
		List<Feedback> d = new ArrayList<Feedback>();
		try {
			d = jdbcTemplate.query("SELECT f.d_name,f.feedback,p.pid,p.pname,p.pemail FROM feedback f join patient p on f.pid=p.pid", new FeedbackRowmapper());
		} catch (Exception e) {
			throw new PatientException("No feedback");
		}
		return d;
	}
	
	
	public boolean deactivateDoctor(long did) throws DoctorException{
		boolean isRemoved = false;
		int status = jdbcTemplate.update("delete from doctor where did=? ",did);
		try {
			if(status != 0) {
				isRemoved = true;
			}
		}catch(Exception e) {
			throw new DoctorException("Doctor details not removed Successfully");
		}
		return isRemoved;
	}
	
	
	
	public class FeedbackRowmapper implements RowMapper<Feedback>{
		public Feedback mapRow(ResultSet rs,int rowNum) throws SQLException
		{
			Feedback f = new Feedback();
			f.setdName(rs.getString(1));
			f.setFeedback(rs.getString(2));
			Patient p = new Patient();
			p.setPid(rs.getLong(3));
			p.setPname(rs.getString(4));
			p.setPemail(rs.getString(5));
			f.setPatient(p);
			return f;
		
		}
	}
}
