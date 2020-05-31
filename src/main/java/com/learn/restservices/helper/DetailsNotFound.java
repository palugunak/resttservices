package com.learn.restservices.helper;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.persistence.EntityNotFoundException;

import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

//@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class DetailsNotFound extends ResponseEntityExceptionHandler {
  
   //other exception handlers
  
   @ExceptionHandler(EntityNotFoundException.class)
   protected ResponseEntity<Object> handleEntityNotFound(
           EntityNotFoundException ex) {
       ApiError apiError = new ApiError(HttpStatus.NOT_FOUND);
       return buildResponseEntity(apiError);
   }

private ResponseEntity<Object> buildResponseEntity(ApiError apiError) {
	// TODO Auto-generated method stub
	return null;
}
}