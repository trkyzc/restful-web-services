package com.example.restful_web_services.exception;


import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;


import jakarta.persistence.EntityNotFoundException;

@RestControllerAdvice
public class GlobalExceptionHandler {
	

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ErrorDetails> handleUserNotFoundException(EntityNotFoundException ex,WebRequest request) throws Exception {
        
    	ErrorDetails errorDetails = new ErrorDetails( ex.getMessage(), request.getDescription(false),LocalDateTime.now());
		
		return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    }
    
    @ExceptionHandler(MethodArgumentNotValidException.class)  //MethodArgumentNotValidException türünde hata olduğunda bu metodu çalıştır.(@Valid hatası)
    public ResponseEntity<Object> handleValidationExceptions(MethodArgumentNotValidException exception, WebRequest request){  //exception doğrulama sırasında oluşan tüm hataları içerir
        Map<String,String> errorMap=new HashMap<>();
        exception.getBindingResult().getAllErrors().forEach((error)->{
            String fieldName=((FieldError)error).getField();    // Her bir hata field error olarak değerlendirilir.
            String errorMessage=error.getDefaultMessage();      // Hatanın mesajını alır.
            errorMap.put(fieldName,errorMessage);               // Map'e ekler.
        });
        ErrorDetails errorDetails = new ErrorDetails("ValidationErrors",request.getDescription(false),LocalDateTime.now(),errorMap);
        
        return new ResponseEntity<Object>(errorDetails, HttpStatus.BAD_REQUEST);
    }
    
    
    
    
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDetails> handleAllExceptions(Exception ex, WebRequest request) throws Exception{
    	
    	ErrorDetails errorDetails = new ErrorDetails( ex.getMessage(), request.getDescription(false),LocalDateTime.now());
		
		return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    


}
