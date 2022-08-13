package com.cognizant.emedicare.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;

@ControllerAdvice
public class DoctorException extends Exception{
	
	public DoctorException() {
		super();
	}
	
	public DoctorException(String s) {
		super(s);
	}
}
