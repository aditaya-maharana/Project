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

import com.cognizant.emedicare.exception.PatientException;
import com.cognizant.emedicare.model.Appointment;
import com.cognizant.emedicare.model.Doctor;
import com.cognizant.emedicare.model.Feedback;
import com.cognizant.emedicare.model.Patient;
import com.cognizant.emedicare.model.Report;
import com.cognizant.emedicare.service.IPatientService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/patient")
public class PaientController {

	@Autowired
	private IPatientService patientService;
	
	@GetMapping("/home")
	public String view() {
		return "Hey User!!";
	}
	
	//In this section patient can able to see his/her details
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@GetMapping("/viewDetails/{pid}")
	public ResponseEntity<Object> viewPatientDetails(@PathVariable Long pid) throws PatientException{
		List<Patient> item = patientService.viewPatientDetails(pid);
		if(!item.isEmpty()) {
			return new ResponseEntity(item,HttpStatus.OK);
		}
		else {
			return new ResponseEntity("Patient Id doesn't exist", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	//In this section patient can able to edit his/her details
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@PutMapping("/editDetails/{pid}")
	public ResponseEntity<Object> editPatient(@RequestBody Patient patient,@PathVariable Long pid) throws PatientException{
		boolean isEdited = patientService.editPatient(patient,pid);
		if(isEdited) {
			return new ResponseEntity(patient,HttpStatus.OK);
		}else {
			return new ResponseEntity("Data not updated successfully", HttpStatus.NOT_FOUND);
		}
	}
	
	//In this section patient can able to view doctors list
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@GetMapping("/doctor")
	public ResponseEntity<Object> viewDoctor() throws PatientException{
		List<Doctor> item = patientService.viewDoctor();
		if(!item.isEmpty()) {
			return new ResponseEntity(item,HttpStatus.OK);
		}
		else {
			return new ResponseEntity("No Doctor Available", HttpStatus.NOT_FOUND);
		}
	}
		
	//In this section patient can able to edit his/her details
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@PostMapping("/bookAppointment/{did}/{pid}")
	public ResponseEntity<Object> bookAppointment(@RequestBody Appointment appointment,@PathVariable Long did,@PathVariable Long pid) throws PatientException{
		boolean isEdited = patientService.bookAppointment(appointment,did,pid);
		if(isEdited) {
			return new ResponseEntity(appointment,HttpStatus.OK);
		}else {
			return new ResponseEntity("Data not updated successfully", HttpStatus.NOT_FOUND);
		}
	}
	
	//In this section patient can able to see his/her appointments
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@GetMapping("/viewAppointmentForPatient/{pid}")
	public ResponseEntity<Object> viewAppointmentForPatient(@PathVariable Long pid) throws PatientException{
		List<Appointment> item = patientService.viewAppointmentForPatient(pid);
		if(!item.isEmpty()) {
			return new ResponseEntity(item,HttpStatus.OK);
		}
		else {
			return new ResponseEntity("No Appointment done yet, click appointments to make one", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	//In this section patient can able to delete his/her appointment
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@DeleteMapping("/deleteAppointmentForPatient/{appointmentid}")
	public ResponseEntity<Object> deleteAppointmentForPatient(@PathVariable Long appointmentid) throws PatientException{
		boolean isDeleted = patientService.deleteAppointmentForPatient(appointmentid);
		if(isDeleted) {
			return new ResponseEntity<Object>("Appointment Deleted Successfully",HttpStatus.OK);
		}
		else {
			return new ResponseEntity("Appointment not deleted successfully", HttpStatus.NOT_FOUND);
		}
	}
	
	//In this section patient can able to see his/her appointments
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@GetMapping("/viewReport/{pid}")
	public ResponseEntity<Object> viewReport(@PathVariable Long pid) throws PatientException{
		List<Report> item = patientService.viewReport(pid);
		if(!item.isEmpty()) {
			return new ResponseEntity(item,HttpStatus.OK);
		}
		else {
			return new ResponseEntity("No Report exist", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
		
	//In this section patient can able to see his/her feedback
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@GetMapping("/viewFeedback")
	public ResponseEntity<Object> viewFeedback() throws PatientException{
		List<Feedback> item = patientService.viewFeedback();
		if(!item.isEmpty()) {
			return new ResponseEntity(item,HttpStatus.OK);
		}
		else {
			return new ResponseEntity("No Feedbacks given", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	//In this section patient can able to edit his/her details
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@PostMapping("/giveFeedback/{pid}")
	public ResponseEntity<Object> giveFeedback(@RequestBody Feedback f,@PathVariable Long pid) throws PatientException{
		boolean isEdited = patientService.giveFeedback(f,pid);
		if(isEdited) {
			return new ResponseEntity(f,HttpStatus.OK);
		}else {
			return new ResponseEntity("Data not updated successfully", HttpStatus.NOT_FOUND);
		}
	}
	
	
	
	@ExceptionHandler(PatientException.class)
	public ResponseEntity<Object> Exception(Exception ex)
	{
		System.out.println(ex);
		return new ResponseEntity<Object>(ex.getMessage(),HttpStatus.NOT_FOUND);
	}
	
	

}
