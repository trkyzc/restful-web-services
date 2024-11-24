package com.example.restful_web_services.exception;

import java.time.LocalDateTime;
import java.util.Map;

import jakarta.annotation.Nullable;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ErrorDetails {
	
	private String message;
	private String details;
	private LocalDateTime timestamp;
	private Map validationErrors;
	
	public ErrorDetails(String message, String details, LocalDateTime timestamp) {
		super();
		this.message = message;
		this.details = details;
		this.timestamp = timestamp;
	}

	
}
