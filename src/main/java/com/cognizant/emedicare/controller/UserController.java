package com.cognizant.emedicare.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cognizant.emedicare.exception.DoctorException;
import com.cognizant.emedicare.exception.PatientException;
import com.cognizant.emedicare.exception.UserException;
import com.cognizant.emedicare.model.Doctor;
import com.cognizant.emedicare.model.Patient;
import com.cognizant.emedicare.service.IDoctorService;
import com.cognizant.emedicare.service.IPatientService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api")
public class UserController {

	@Autowired
	private IPatientService patientService;
	
	@Autowired
	private IDoctorService doctorService;
	
	//To check whether this rest api is working or not
	@GetMapping("/home")
	public String view() {
		return "Hey User!!";
	}
	
	//In this section patient can able to register
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@PostMapping("/patientSignup")
	public ResponseEntity<Object> registerPatient(@RequestBody Patient p) throws PatientException{
		boolean isAdded = patientService.registerPatient(p);
		if(isAdded) {
			return new ResponseEntity("Patient details added successfully",HttpStatus.OK);
		}else {
			return new ResponseEntity("Register Again", HttpStatus.NOT_FOUND);
		}
	}
	//In this section doctor can able to register
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@PostMapping("/doctorSignup")
	public ResponseEntity<Object> registerDoctor(@RequestBody Doctor d) throws DoctorException{
		boolean isAdded = doctorService.registerDoctor(d);
		if(isAdded) {
			return new ResponseEntity("Doctor details added successfully",HttpStatus.OK);
		}else {
			return new ResponseEntity("Register Again", HttpStatus.NOT_FOUND);
		}
	}
	
	
	//In this section patient can able to login
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@PostMapping("/patientLogin")
	public ResponseEntity<Object> validate(@RequestBody Patient login) throws UserException  {
		
		List<Patient> status = patientService.verifyPatientLogin(login);
		if(!status.isEmpty())
			return new ResponseEntity(status, HttpStatus.OK);
		else {
			
			return new ResponseEntity("User is invalid", HttpStatus.NOT_FOUND);
		}
			
	}
	
	
	//In this section doctor can able to login
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@PostMapping("/doctorLogin")
	public ResponseEntity<Object> validate(@RequestBody Doctor login) throws UserException  {
		
		List<Doctor> status = doctorService.verifyDoctorLogin(login);
		if(!status.isEmpty())
			return new ResponseEntity(status, HttpStatus.OK);
		else {
			
			return new ResponseEntity("User is invalid", HttpStatus.NOT_FOUND);
		}
			
	}
	
	@ExceptionHandler(UserException.class)
	public ResponseEntity<Object> Exception(Exception ex)
	{
		System.out.println(ex);
		return new ResponseEntity<Object>(ex.getMessage(),HttpStatus.NOT_FOUND);
	}
	
}

