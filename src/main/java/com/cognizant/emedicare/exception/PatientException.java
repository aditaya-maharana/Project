package com.cognizant.emedicare.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;

@ControllerAdvice
public class PatientException extends Exception {

	public PatientException() {
		super();
	}
	public PatientException(String s) {
		super(s);
	}
}
