package com.cognizant.emedicare.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;

@ControllerAdvice
public class UserException extends RuntimeException{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public UserException(String s){
		super(s);
	}
	public UserException(){
		super();
	}
	
}
