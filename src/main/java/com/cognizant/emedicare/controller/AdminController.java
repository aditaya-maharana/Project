package com.cognizant.emedicare.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cognizant.emedicare.exception.DoctorException;
import com.cognizant.emedicare.exception.PatientException;
import com.cognizant.emedicare.exception.UserException;
import com.cognizant.emedicare.model.Admin;
import com.cognizant.emedicare.model.Doctor;
import com.cognizant.emedicare.model.Feedback;
import com.cognizant.emedicare.model.Patient;
import com.cognizant.emedicare.service.IAdminService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	private IAdminService service;
	
	
	//In this section admin can able to login
	@PostMapping("/login")
	public ResponseEntity<String> validate( @RequestBody Admin a) throws UserException  {
		
		System.out.println(a.getMail());
		System.out.println(a.getPassword());
		if(a.getMail().equals("admin@gmail.com") && a.getPassword().equals("admin")) {
			return new ResponseEntity<String>("User is Valid", HttpStatus.OK);
		}
		else {
			
			return new ResponseEntity<String>("User is invalid", HttpStatus.NOT_FOUND);
		}
			
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@GetMapping("/doctor")
	public ResponseEntity<Object> viewDoctor() throws DoctorException{
		List<Doctor> item = service.viewDoctor();
		if(!item.isEmpty()) {
			return new ResponseEntity(item,HttpStatus.OK);
		}
		else {
			return new ResponseEntity("No Doctor Available", HttpStatus.NOT_FOUND);
		}
	}
		
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@PutMapping("/approve/{did}")
	public ResponseEntity<Object> approveDoctor(@PathVariable Long did) throws DoctorException{
		boolean isEdited = service.approveDoctor(did);
		if(isEdited) {
			return new ResponseEntity("Doctor Verified Successfully",HttpStatus.OK);
		}else {
			return new ResponseEntity("Doctor Not Verified successfully", HttpStatus.NOT_FOUND);
		}
	}
	
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@GetMapping("/patient")
	public ResponseEntity<Object> viewPatient() throws PatientException{
		List<Patient> item =service.viewPatient();
		if(!item.isEmpty()) {
			return new ResponseEntity(item,HttpStatus.OK);
		}
		else {
			return new ResponseEntity("No Patient Available", HttpStatus.NOT_FOUND);
		}
	}

	
	//In this section admin can view feedback
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@GetMapping("/feedback")
	public ResponseEntity<Object> viewFeedback() throws PatientException{
		List<Feedback> item = service.viewFeedback();
		if(!item.isEmpty()) {
			return new ResponseEntity(item,HttpStatus.OK);
		}
		else {
			return new ResponseEntity("Please refresh your page", HttpStatus.NOT_FOUND);
		}
	}
	
	//In this section admin can deactivate doctor
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@DeleteMapping("/deactivateDoctor/{did}")
	public ResponseEntity<Object> deactivateDoctor(@PathVariable Long did) throws DoctorException{
		boolean isRemoved = service.deactivateDoctor(did);
		if(isRemoved) {
			return new ResponseEntity<Object>("Doctor details removed Successfully",HttpStatus.OK);
		}
		else {
			return new ResponseEntity("Doctor details not removed Successfully", HttpStatus.NOT_FOUND);
		}
	}
	
}
