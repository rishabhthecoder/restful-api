package com.rest.webservices.restfullwebservices.user.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@SuppressWarnings("serial")
@ResponseStatus(HttpStatus.NOT_FOUND)
public class UserNotFoundException extends RuntimeException {
	//private String message;

	public UserNotFoundException(String message) {
		super(message);
			}
	

}
