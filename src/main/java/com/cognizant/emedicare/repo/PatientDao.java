package com.cognizant.emedicare.repo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.cognizant.emedicare.exception.DoctorException;
import com.cognizant.emedicare.exception.PatientException;
import com.cognizant.emedicare.model.Appointment;
import com.cognizant.emedicare.model.Doctor;
import com.cognizant.emedicare.model.Feedback;
import com.cognizant.emedicare.model.Patient;
import com.cognizant.emedicare.model.Report;
import com.cognizant.emedicare.repo.DoctorDao.DoctorDetailsRowmapper;

@Component
public class PatientDao {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;


	public List<Patient> viewPatientDetails(long pid) throws PatientException {
		List<Patient> item = new ArrayList<>();
		try {
			item = jdbcTemplate.query(
					"select pid,pname,pemail,page,pphonenumber,paddress,pregdate,psugar from patient where pid=?",
					new PatientDetailsRowmapper(), pid);
			System.out.println(item);
		} catch (Exception e) {
			throw new PatientException("Invalid patientId");
		}

		return item;
	}

	public boolean editPatient(Patient p,long pid) throws PatientException {

		boolean isEdited = false;
		int status = jdbcTemplate.update(
				"update patient set pname=?,pemail=?,ppassword=?,page=?,pphonenumber=?,"
						+ "paddress=?,psugar=? where pid = ?",p.getPname(),p.getPemail(),p.getPpassword(),p.getPage(),p.getPphonenumber(),
						p.getPaddress(),p.getPsugar(),pid);

		try {
			if (status != 0) {
				isEdited = true;
			}
		} catch (Exception e) {

			throw new PatientException("Invalid patientId");
		}

		return isEdited;
	}
	
	
	public List<Doctor> viewDoctor() throws PatientException{
		List<Doctor> d = new ArrayList<Doctor>();
		try {
			d = jdbcTemplate.query("SELECT did,dname,demail,dphonenumber,dspecialization,dqualification"
					+ ",dhospitalname,dfees,daddress FROM doctor where status = 'Verified' ", new DoctorResult());
		} catch (Exception e) {
			throw new PatientException("No Doctor is available, try after Sometimes");
		}
		return d;
	}
	
	public List<Appointment> viewAppointmentForPatient(long pid) throws PatientException{
		List<Appointment> item = new ArrayList<>();
		try {
			item = jdbcTemplate.query("select a.appointmentid,a.appointmentdate,a.appointmenttime,d.did,d.dname,d.daddress,d.demail\r\n"
					+ ",d.dhospitalname,d.dqualification,d.dspecialization,d.dphonenumber,d.dfees  \r\n"
					+ ",a.status from appointment a left join doctor d on a.did=d.did where a.pid=?;",
					new AppointmentPatientRowmapper(),pid);
			System.out.println(item);
		}catch(Exception e) {
			throw new PatientException("You don't have any appointment");
		}
		return item;
	}
	
	public boolean deleteAppointmentForPatient(long appointmentid) throws PatientException{
		boolean isDeleted = false;
		int status = jdbcTemplate.update("delete from appointment where  appointmentid=?",appointmentid);
		try {
			if(status != 0) {
				isDeleted = true;
			}
		}catch(Exception e) {
			throw new PatientException("Appointment Not Deleted Successfully");
		}
		return isDeleted;
	}
	
	public boolean bookAppointment(Appointment a,long did,long pid) throws PatientException {

		boolean isEdited = false;
		int status = jdbcTemplate.update(
				"insert into appointment (appointmentdate,appointmenttime,did,pid,status) values (?,?,?,?,'Pending')",a.getAppointmentdate()
				,a.getAppointmenttime(),did,pid);

		try {
			if (status != 0) {
				isEdited = true;
			}
		} catch (Exception e) {

			throw new PatientException("Invalid patientId");
		}

		return isEdited;
	}
	
	public List<Report> viewReport(long pid) throws PatientException {
		List<Report> item = new ArrayList<>();
		try {
			item = jdbcTemplate.query(
					"select r.rdate,r.eprescription,d.did,d.dname,d.demail,d.dphonenumber,d.dhospitalname,d.dspecialization from report r  join doctor d on r.did = d.did where r.pid=? ",
					new ReportRowmapper(), pid);
			System.out.println(item);
		} catch (Exception e) {
			throw new PatientException("You don't have any Doctor's Report");
		}

		return item;
	}
	
	public List<Feedback> viewFeedback() throws PatientException{
		List<Feedback> item = new ArrayList<>();
		try {
			item = jdbcTemplate.query("select feedback,d_name from feedback ",
					new FeedbackRowmapper());
			System.out.println(item);
		}catch(Exception e) {
			throw new PatientException("You don't have any feedback");
		}
		return item;
	}
	
	public boolean giveFeedback(Feedback f,long pid) throws PatientException {

		boolean isSubmitted = false;
		int status = jdbcTemplate.update(
				"insert into feedback (feedback,d_name,pid)values (?,?,?)",f.getFeedback(),f.getdName(),pid);

		try {
			if (status != 0) {
				isSubmitted = true;
			}
		} catch (Exception e) {

			throw new PatientException("Invalid patientId");
		}

		return isSubmitted;
	}
	
	public class AppointmentPatientRowmapper implements RowMapper<Appointment>{
		public Appointment mapRow(ResultSet rs,int rowNum) throws SQLException
		{
			Appointment appointment = new Appointment();
			appointment.setAppointmentid(rs.getLong(1));
			appointment.setAppointmentdate(rs.getDate(2));
			appointment.setAppointmenttime(rs.getString(3));
			Doctor d = new Doctor();
			d.setDid(rs.getLong(4));
			d.setDname(rs.getString(5));
			d.setDaddress(rs.getString(6));
			d.setDemail(rs.getString(7));
			d.setDhospitalname(rs.getString(8));
			d.setDqualification(rs.getString(9));
			d.setDspecialization(rs.getString(10));
			d.setDphonenumber(rs.getString(11));
			d.setDfees(rs.getFloat(12));
		    appointment.setStatus(rs.getString(13));
			appointment.setDoctor(d);
			return appointment;
		}
	}

	public class ReportRowmapper implements RowMapper<Report>{
		public Report mapRow(ResultSet rs,int rowNum) throws SQLException
		{
			Report report = new Report();
			report.setRdate(rs.getDate(1));
			report.setEprescription(rs.getString(2));
			Doctor d = new Doctor();
			d.setDid(rs.getLong(3));
			d.setDname(rs.getString(4));
			d.setDemail(rs.getString(5));
			d.setDphonenumber(rs.getString(6));
			d.setDhospitalname(rs.getString(7));
			d.setDspecialization(rs.getString(8));
			
			report.setDoctor(d);
			return report;
		}
	}
	public class FeedbackRowmapper implements RowMapper<Feedback>{
		public Feedback mapRow(ResultSet rs,int rowNum) throws SQLException
		{
			Feedback f = new Feedback();
			f.setFeedback(rs.getString(1));
			f.setdName(rs.getString(2));
			return f;
		}
	}
	public class PatientDetailsRowmapper implements RowMapper<Patient> {
	
		public Patient mapRow(ResultSet rs,int rowNum) throws SQLException
		{
			Patient patient = new Patient();
			patient.setPid(rs.getLong(1));
			patient.setPname(rs.getString(2));
			patient.setPemail(rs.getString(3));
			patient.setPage(rs.getInt(4));
			patient.setPphonenumber(rs.getString(5));
			patient.setPaddress(rs.getString(6));
			patient.setPregdate(rs.getDate(7));
			patient.setPsugar(rs.getString(8));
			return patient;
		}
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
			return doctor;
		}
	
	}
	

}
