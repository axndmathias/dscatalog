package com.axnd.dscatalog.resources.exceptions;


import java.time.Instant;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.axnd.dscatalog.services.exceptions.DatabaseExecption;
import com.axnd.dscatalog.services.exceptions.ResourceNotFoundExecption;


@ControllerAdvice
public class ResourceExceptionHandler {

	@ExceptionHandler(ResourceNotFoundExecption.class)
	public ResponseEntity<StandardError> entityNotFound(ResourceNotFoundExecption e, HttpServletRequest request){
		HttpStatus status =  HttpStatus.NOT_FOUND;
		StandardError err = new StandardError();
		err.setTimestamp(Instant.now());
		err.setStatus(status.value());
		err.setError("Resource not found");
		err.setMessage(e.getMessage());
		err.setPath(request.getRequestURI());
		
		return ResponseEntity.status(status).body(err);
	}
	
	@ExceptionHandler(DatabaseExecption.class)
	public ResponseEntity<StandardError> entityNotFound(DatabaseExecption e, HttpServletRequest request){
		
		HttpStatus status =  HttpStatus.BAD_REQUEST;
		StandardError err = new StandardError();
		err.setTimestamp(Instant.now());
		err.setStatus(status.value());
		err.setError("Database exception");
		err.setMessage(e.getMessage());
		err.setPath(request.getRequestURI());
		
		return ResponseEntity.status(status).body(err);
	}
}
