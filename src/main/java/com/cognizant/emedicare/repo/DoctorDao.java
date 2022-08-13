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
import com.cognizant.emedicare.model.Appointment;
import com.cognizant.emedicare.model.Doctor;
import com.cognizant.emedicare.model.Patient;
import com.cognizant.emedicare.model.Report;

@Component
public class DoctorDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public List<Doctor> viewDoctorDetails(long did) throws DoctorException {
		List<Doctor> item = new ArrayList<>();
		try {
			item = jdbcTemplate.query(
					"select did,dname,demail,dphonenumber,dspecialization,dqualification,dhospitalname,dfees,daddress,status from doctor where did=?",
					new DoctorDetailsRowmapper(), did);
			System.out.println(item);
		} catch (Exception e) {
			throw new DoctorException("Invalid doctorId");
		}

		return item;
	}

	public boolean editDoctor(Doctor d,long did) throws DoctorException {

		boolean isEdited = false;
		int status = jdbcTemplate.update(
				"update doctor set dname=?,demail=?,dpassword=?,daddress=?,dqualification=?,dspecialization=?,"
						+ "dhospitalname=?,dfees=?,dphonenumber=? where did = ?",
				d.getDname(), d.getDemail(), d.getDphonenumber(),d.getDpassword(), d.getDspecialization(), d.getDqualification(),
				 d.getDhospitalname(), d.getDfees(), d.getDaddress(), did);

		try {
			if (status != 0) {
				isEdited = true;
			}
		} catch (Exception e) {

			throw new DoctorException("Invalid doctorId");
		}

		return isEdited;
	}
	
	
	public List<Appointment> viewAppointmentForDoctor(long did) throws DoctorException{
		List<Appointment> item = new ArrayList<>();
		try {
			item = jdbcTemplate.query("select a.appointmentid,a.appointmentdate,a.appointmenttime,p.pid,p.pname,p.pemail,p.page"
					+ ",p.pphonenumber,p.psugar,a.status from appointment a join patient p on a.pid=p.pid where a.did=?",
					new AppointmentDoctorRowmapper(),did);
			System.out.println(item);
		}catch(Exception e) {
			throw new DoctorException("You don't have any appointment");
		}
		return item;
	}
	
	public boolean deleteAppointmentForDoctor(long appointmentid) throws DoctorException{
		boolean isDeleted = false;
		int status = jdbcTemplate.update("delete from appointment where appointmentid=?",appointmentid);
		try {
			if(status != 0) {
				isDeleted = true;
			}
		}catch(Exception e) {
			throw new DoctorException("Appointment Not Deleted Successfully");
		}
		return isDeleted;
	}
	
	public boolean appointmentStatus(long appointmentid) throws DoctorException {

		boolean isEdited = false;
		int status = jdbcTemplate.update(
				"update appointment set status = 'Completed' where appointmentid = ?",appointmentid);

		try {
			if (status != 0) {
				isEdited = true;
			}
		} catch (Exception e) {

			throw new DoctorException("Invalid doctorId");
		}

		return isEdited;
	}
	
	public boolean giveReport(Report r,long did,long pid) throws DoctorException {
		
		boolean isEdited = false;
		int status = jdbcTemplate.update(
				"insert into report (rdate,eprescription,did,pid) values (?,?,?,?)",r.getRdate(),
				r.getEprescription(),did,pid);
		System.out.println(status);
		System.out.println(r.getEprescription());

		try {
			if (status != 0) {
				isEdited = true;
			}
		} catch (Exception e) {

			throw new DoctorException("Invalid patientId");
		}

		return isEdited;
	}
	
	
	public class AppointmentDoctorRowmapper implements RowMapper<Appointment>{
		public Appointment mapRow(ResultSet rs,int rowNum) throws SQLException
		{
			Appointment appointment = new Appointment();
			appointment.setAppointmentid(rs.getLong(1));
			appointment.setAppointmentdate(rs.getDate(2));
			appointment.setAppointmenttime(rs.getString(3));
			
			Patient p = new Patient();
			p.setPid(rs.getLong(4));
			p.setPname(rs.getString(5));
			p.setPemail(rs.getString(6));
			p.setPage(rs.getInt(7));
			p.setPphonenumber(rs.getString(8));
			p.setPsugar(rs.getString(9));
			appointment.setStatus(rs.getString(10));
			appointment.setPatient(p);
			return appointment;
		}
	}

	public class DoctorDetailsRowmapper implements RowMapper<Doctor> {
	
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


}
