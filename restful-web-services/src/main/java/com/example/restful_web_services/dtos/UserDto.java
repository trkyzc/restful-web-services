package com.example.restful_web_services.dtos;

import java.util.Date;

import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import lombok.Data;


@Data
public class UserDto {
	
	@Size(min = 2, message = "Name should have atleast 2 characters")
	private String name;
	
	@Past(message = "Birth date should be in the past")
	private Date birthDate;

}
