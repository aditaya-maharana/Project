package com.cognizant.emedicare.repo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.cognizant.emedicare.exception.DoctorException;
import com.cognizant.emedicare.exception.PatientException;
import com.cognizant.emedicare.exception.UserException;
import com.cognizant.emedicare.model.Doctor;
import com.cognizant.emedicare.model.Patient;
import com.cognizant.emedicare.model.Report;

@Component
public class UserDao {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public boolean registerDoctor(Doctor d) throws DoctorException {
		
		boolean isadded = false;
		try {
			if (d.getDemail() != null && d.getDpassword() != null) {
				int status = jdbcTemplate.update(
						"insert into doctor (dname,demail,dpassword,dphonenumber,dspecialization,dqualification,dhospitalname,dfees,daddress)values (?,?,?,?,?,?,?,?,?)",
						d.getDname(), d.getDemail(),d.getDpassword(), d.getDphonenumber(), d.getDspecialization(), d.getDqualification(),
						 d.getDhospitalname(), d.getDfees(), d.getDaddress());
				System.out.println(status);
					if (status!=0) {
						isadded = true;
					}
			}
		}catch (Exception e) {
			throw new DoctorException("Try Again");
		}

		return isadded;
	}
	
	public boolean registerPatient(Patient p) throws PatientException {
		
		boolean isadded = false;
		try {
			if(p.getPemail() != null && p.getPpassword() != null) {
				int status = jdbcTemplate.update(
						"insert into patient (paddress,page,pemail,pname,ppassword,pphonenumber,pregdate,psugar)\r\n"
						+ "values (?,?,?,?,?,?,?,?)",p.getPaddress(),p.getPage(),p.getPemail(),p.getPname(),p.getPpassword(),p.getPphonenumber(),p.getPregdate(),p.getPsugar()
						);
				
					if (status != 0) {
						isadded = true;
					}
			}
		
		}catch (Exception e) {
			throw new PatientException("Try Again");
		}

		return isadded;
	}

	
	public List<Patient> verifyPatientLogin(Patient patient) throws UserException
	{
		try
		{
			List<Patient> check=jdbcTemplate.query("select pid,pname,pemail from Patient where pemail=? and ppassword=?",new PatientRowMapper(),patient.getPemail(),patient.getPpassword());
			System.out.println(check);
			if(check.isEmpty())
			  throw new UserException("Invalid User Credentails");
			else
				return check;
		}
		catch(DataAccessException e)
		{
			throw new UserException("Required to fill Emailid or password");
		}
	}
	
	public List<Doctor> verifyDoctorLogin(Doctor doctor) throws UserException
	{
		try
		{
			List<Doctor> check=jdbcTemplate.query("select did,dname,demail from Doctor where demail=? and dpassword=?",new DoctorRowMapper(),doctor.getDemail(),doctor.getDpassword());
			System.out.println(check);
			if(check.isEmpty())
			  throw new UserException("Invalid User Credentails");
			else
				return check;
		}
		catch(DataAccessException e)
		{
			throw new UserException("Required to fill Emailid or password");
		}
	}
	
	public class PatientRowMapper implements RowMapper<Patient> {
		public Patient mapRow(ResultSet rs, int rowNum) throws SQLException {
			Patient patient = new Patient();
			patient.setPid(rs.getLong(1));
			patient.setPname(rs.getString(2));
			patient.setPemail(rs.getString(3));
			return patient;
		}
	}

	public class DoctorRowMapper implements RowMapper<Doctor> {
		public Doctor mapRow(ResultSet rs, int rowNum) throws SQLException {
			Doctor doctor = new Doctor();
			doctor.setDid(rs.getLong(1));
			doctor.setDname(rs.getString(2));
			doctor.setDemail(rs.getString(3));
			return doctor;
		}
	}
}
