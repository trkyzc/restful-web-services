package com.example.restful_web_services.business.abstracts;

import java.util.List;
import java.util.Optional;

import org.springframework.hateoas.EntityModel;

import com.example.restful_web_services.dtos.UserDto;
import com.example.restful_web_services.entities.User;

public interface UserService {
	
	List<User> getUsers();
	Optional<User> getUserById(long id);
	User createUser(UserDto userDto);
	void deleteUser(long id);
	User updateUser(long id,UserDto userDto);
	
	

}
