package com.cognizant.emedicare.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cognizant.emedicare.exception.DoctorException;
import com.cognizant.emedicare.model.Appointment;
import com.cognizant.emedicare.model.Doctor;
import com.cognizant.emedicare.model.Report;
import com.cognizant.emedicare.service.IDoctorService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/doctor")
public class DoctorController {

	@Autowired
	private IDoctorService doctorService;
	
	@GetMapping("/home")
	public String view() {
		return "Hey User!!";
	}
	
	//In this section doctor can able to see his/her details
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@GetMapping("/viewDetails/{did}")
	public ResponseEntity<Object> viewDoctorDetails(@PathVariable Long did) throws DoctorException{
		List<Doctor> item = doctorService.viewDoctorDetails(did);
		if(!item.isEmpty()) {
			return new ResponseEntity(item,HttpStatus.OK);
		}
		else {
			return new ResponseEntity("Doctor Id doesn't exist", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	//In this section doctor can able to edit his/her details
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@PutMapping("/editDetails/{did}")
	public ResponseEntity<Object> editDoctor(@RequestBody Doctor doctor,@PathVariable Long did) throws DoctorException{
		boolean isEdited = doctorService.editDoctor(doctor,did);
		if(isEdited) {
			return new ResponseEntity(doctor,HttpStatus.OK);
		}else {
			return new ResponseEntity("Data not updated successfully", HttpStatus.NOT_FOUND);
		}
	}
	
	//In this section doctor can able to see his/her appointments
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@GetMapping("/viewAppointmentForDoctor/{did}")
	public ResponseEntity<Object> viewAppointmentForDoctor(@PathVariable Long did) throws DoctorException{
		List<Appointment> item = doctorService.viewAppointmentForDoctor(did);
		if(!item.isEmpty()) {
			return new ResponseEntity(item,HttpStatus.OK);
		}
		else {
			return new ResponseEntity("Doctor Id doesn't exist", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@PutMapping("/appointmentStatus/{appointmentid}")
	public ResponseEntity<Object> appointmentStatus(@PathVariable Long appointmentid) throws DoctorException{
		boolean isEdited = doctorService.appointmentStatus(appointmentid);
		if(isEdited) {
			return new ResponseEntity("Status updated successfully",HttpStatus.OK);
		}else {
			return new ResponseEntity("Data not updated successfully", HttpStatus.NOT_FOUND);
		}
	}
	
	//In this section doctor can able to delete his/her appointment
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@DeleteMapping("/deleteAppointmentForDoctor/{appointmentid}")
	public ResponseEntity<Object> deleteAppointmentForDoctor(@PathVariable Long appointmentid) throws DoctorException{
		boolean isDeleted = doctorService.deleteAppointmentForDoctor(appointmentid);
		if(isDeleted) {
			return new ResponseEntity<Object>("Data Deleted Successfully",HttpStatus.OK);
		}
		else {
			return new ResponseEntity("Appointment not deleted successfully", HttpStatus.NOT_FOUND);
		}
	}
	
	//In this section doctor can able to edit his/her details
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@PostMapping("/giveReport/{did}/{pid}")
	public ResponseEntity<Object> giveReport(@RequestBody Report r, @PathVariable Long did,@PathVariable Long pid) throws DoctorException{
		boolean isEdited = doctorService.giveReport(r,did,pid);
		if(isEdited) {
			return new ResponseEntity("Report given successfully",HttpStatus.OK);
		}else {
			return new ResponseEntity("Data not updated successfully", HttpStatus.NOT_FOUND);
		}
	}
	
	
	@ExceptionHandler(DoctorException.class)
	public ResponseEntity<Object> Exception(Exception ex)
	{
		System.out.println(ex);
		return new ResponseEntity<Object>(ex.getMessage(),HttpStatus.NOT_FOUND);
	}
	
}

