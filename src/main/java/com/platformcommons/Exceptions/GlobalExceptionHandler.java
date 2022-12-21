package com.platformcommons.Exceptions;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
	public class GlobalExceptionHandler{


		
	@ExceptionHandler(AdminException.class)
	public ResponseEntity<MyCustomError> AdminExceptionHandler(AdminException ae,WebRequest wrq) {
	
		System.out.println("inside AdminExistsHandlerException method...");
		MyCustomError adminException = new MyCustomError();
		
		adminException.setLdt(LocalDateTime.now());
		adminException.setMessage(ae.getMessage());
		adminException.setDetails(wrq.getDescription(false));
	
	return new ResponseEntity<>(adminException,HttpStatus.NOT_ACCEPTABLE);
	}
	
	@ExceptionHandler(StudentException.class)
	public ResponseEntity<MyCustomError> AdminExistsHandler(StudentException se,WebRequest wrq) {
	
		System.out.println("inside StudentHandlerException method...");
		MyCustomError studentExceptiom = new MyCustomError();
		
		studentExceptiom.setLdt(LocalDateTime.now());
		studentExceptiom.setMessage(se.getMessage());
		studentExceptiom.setDetails(wrq.getDescription(false));
	
	return new ResponseEntity<>(studentExceptiom,HttpStatus.NOT_ACCEPTABLE);
	}
	
	@ExceptionHandler(CourseException.class)
	public ResponseEntity<MyCustomError> CourseExceptionHandler(CourseException ce,WebRequest wrq) {
	
		System.out.println("inside CourseExceptionHandler method...");
		MyCustomError courseException = new MyCustomError();
		
		courseException.setLdt(LocalDateTime.now());
		courseException.setMessage(ce.getMessage());
		courseException.setDetails(wrq.getDescription(false));
	
	return new ResponseEntity<>(courseException,HttpStatus.NOT_ACCEPTABLE);
	}
		
		@ExceptionHandler(MethodArgumentNotValidException.class)
		public ResponseEntity<MyCustomError> validatorHandler(MethodArgumentNotValidException ie) {
		
			System.out.println("inside ValidatorException method...");
			MyCustomError validatorError = new MyCustomError();
			
			validatorError.setLdt(LocalDateTime.now());
			validatorError.setMessage(ie.getMessage());
			validatorError.setDetails(ie.getBindingResult().getFieldError().getDefaultMessage());
		
		return new ResponseEntity<>(validatorError,HttpStatus.NOT_ACCEPTABLE);
		}
		
		@ExceptionHandler(Exception.class)
		public ResponseEntity<MyCustomError> otherExceptionHandler(Exception e,WebRequest wrq){
			
			System.out.println("inside  Exception method...");
			MyCustomError allOtherExceptions = new MyCustomError();
			allOtherExceptions.setLdt(LocalDateTime.now());
			allOtherExceptions.setMessage(e.getMessage());
			allOtherExceptions.setDetails(wrq.getDescription(false));
			
			return new ResponseEntity<>(allOtherExceptions,HttpStatus.NOT_FOUND);
		}
	

	
}

