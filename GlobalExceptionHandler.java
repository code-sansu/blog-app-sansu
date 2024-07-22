package com.sanskar.blog.exceptions;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.sanskar.blog.payloads.ApiResponse;

// we can still get error by default but not the way we want ,  even if we dont make this class
//but we want to return response entity for and of exceptions 


@RestControllerAdvice           // declares this class as exception handler   
public class GlobalExceptionHandler {
    
	
	//ResourceNotFoundException is custom exception, we have defined class for it
	@ExceptionHandler(ResourceNotFoundException.class)  //whenever ResourceNotFoundException is called this method is run
	public ResponseEntity<ApiResponse > resourceNotFoundExceptionHandler(ResourceNotFoundException ex){
		String message=ex.getMessage();
		ApiResponse apiResponse=new ApiResponse(message,false);
		return new ResponseEntity<ApiResponse>(apiResponse,HttpStatus.NOT_FOUND);
	}
	
	//MethodArgumentNotValidException is default exception we get when we enter invalid data , and we are handling it directly
    //so whenever we encounter this exception , following method is executed 
	//since we have annotated the class as @RestControllerAdvice and passed the MethodArgumentNotValidException.class 
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String,String>> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex){
		
		Map<String,String> response=new HashMap<>();
		
		//we will retrieve all errors from ex which is coming form exception in program
		//we take all bindingresults form ex , get allerrors , and traverse for each error,
		//used lambda to get fieldname and default message , (for fieldname we typecast error to fielderror)
		//and mapped all field error with thier default message
		
		ex.getBindingResult().getAllErrors().forEach((error)->{
			String fieldName=((FieldError)error).getField();
		    String message=error.getDefaultMessage();
		    response.put(fieldName,message);
		});
		return new ResponseEntity<Map<String,String>>(response,HttpStatus.BAD_REQUEST);
	}
	
}
