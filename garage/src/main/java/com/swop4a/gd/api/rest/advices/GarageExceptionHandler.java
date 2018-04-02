package com.swop4a.gd.api.rest.advices;

import com.swop4a.gd.exceptions.GarageNotFoundException;
import com.swop4a.gd.api.rest.model.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GarageExceptionHandler {

	@ExceptionHandler(GarageNotFoundException.class)
	public ResponseEntity<ErrorResponse> handleException(GarageNotFoundException ex) {
		return buildErrorResponse(ex, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorResponse> handleException(Exception ex) {
		return buildErrorResponse(ex, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	private ResponseEntity<ErrorResponse> buildErrorResponse(Exception ex, HttpStatus status) {
		ErrorResponse response = new ErrorResponse();
		response.setMessage(ex.getMessage());
		response.setStackTrace(ex.getStackTrace());
		return new ResponseEntity<>(response, status);
	}
}
