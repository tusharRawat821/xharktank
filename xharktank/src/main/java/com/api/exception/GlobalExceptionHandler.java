package com.api.exception;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.fasterxml.jackson.core.JsonProcessingException;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		logException(ex);

		List<String> errorMessages = ex.getBindingResult().getFieldErrors().stream().map(error -> {
			String fieldName = error.getField();
			String errMessage = error.getDefaultMessage();
			return "'" + fieldName + "'" + ": " + errMessage;
		}).collect(Collectors.toList());
		ErrorResponse errorResponse = buildErrorResponse(errorMessages);
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
	}
	
	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		logException(ex);
		
		JsonProcessingException jEx = (JsonProcessingException) ex.getCause(); 
		List<String> errorMessages = List.of(jEx.getMessage());
		ErrorResponse errorResponse = buildErrorResponse(errorMessages);
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
	}
	
	@ExceptionHandler(PitchNotFoundException.class)
	public ResponseEntity<ErrorResponse> handlePitchNotFoundException(PitchNotFoundException ex) {
		logException(ex);
		
		List<String> errorMessages = List.of(ex.getMessage());
		ErrorResponse errorResponse = buildErrorResponse(errorMessages);
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
	}
	
	
	private ErrorResponse buildErrorResponse(List<String> errorMessages) {
		return new ErrorResponse(errorMessages);
	}
	
	private void logException(Exception ex) {
		System.out.println(ex.getCause() + ": " + ex.getMessage());
	}

}
