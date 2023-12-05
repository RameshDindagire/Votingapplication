package com.example.demo.exception;

import java.util.HashMap;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.demo.Helper.ApiResponse;


@ControllerAdvice
public class GlobalExceptionHandler {

//	@ExceptionHandler(ResourceNotFoundException.class)
//	public ResponseEntity<ApiResponse> resourceNotFoundExceptionHandler(ResourceNotFoundException ex) {
//		String message = ex.getMessage();
//		ApiResponse apiResponse = new ApiResponse(message, false);
//		return new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.NOT_FOUND);
//	}
//
//	@ExceptionHandler(MethodArgumentNotValidException.class)
//	public ResponseEntity<Map<String, String>> handleMethodArgsNotValidException(MethodArgumentNotValidException ex) {
//		Map<String, String> resp = new HashMap<>();
//		ex.getBindingResult().getAllErrors().forEach((error) -> {
//			String fieldName = ((FieldError) error).getField();
//			String message = error.getDefaultMessage();
//			resp.put(fieldName, message);
//		});
//
//		return new ResponseEntity<Map<String, String>>(resp, HttpStatus.BAD_REQUEST);
//	}
//	
//
//	@ExceptionHandler(ApiException.class)
//	public ResponseEntity<ApiResponse> handleApiException(ApiException ex) {
//		String message = ex.getMessage();
//		ApiResponse apiResponse = new ApiResponse(message, true);
//		return new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.BAD_REQUEST);
//	}
//	
//	
//	 @ExceptionHandler(UserAlreadyExistsException.class)
//		public ResponseEntity<String> handleUserAlreadyExistsException(UserAlreadyExistsException ex) {
//			return ResponseEntity.badRequest().body(ex.getMessage());
//	 }
//	 
	 @ExceptionHandler(ApiException.class)
	    public ResponseEntity<String> handleInvalidOtpException(ApiException ex) {
	        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
	    }

}
