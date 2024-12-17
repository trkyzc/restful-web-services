package com.example.restful_web_services.controllers;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.restful_web_services.business.abstracts.UserService;
import com.example.restful_web_services.dtos.UserDto;
import com.example.restful_web_services.entities.User;

import jakarta.validation.Valid;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@RestController
public class UserController {
	
	UserService userService;
	
	
	public UserController(UserService userService) {
		super();
		this.userService = userService;
	}

	@GetMapping("/users")
	ResponseEntity<List<User>> getUsers(){
		return new ResponseEntity<>(userService.getUsers(),HttpStatus.OK);
	}
	
	@GetMapping("/users/{id}")
	ResponseEntity<EntityModel<Optional<User>>>getUserById(@PathVariable long id ){
		
		Optional<User> user = userService.getUserById(id);
		
		EntityModel<Optional<User>> entityModel = EntityModel.of(user);  // create an entity model to add links to the response
		WebMvcLinkBuilder link = linkTo(methodOn(this.getClass()).getUsers());  // create a link to the getUsers method
		entityModel.add(link.withRel("all-users")); // add the link to the entity model
		
		return new ResponseEntity<>(entityModel,HttpStatus.OK);
	}
	
	@PostMapping("/users")
	ResponseEntity<User> createUser(@Valid @RequestBody UserDto userDto){
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(userService.createUser(userDto).getId()).toUri();
		
		return ResponseEntity.created(location).body(userService.createUser(userDto));
		
		//return new ResponseEntity<>(userService.createUser(userDto),HttpStatus.CREATED);
	}
	
	@DeleteMapping("/users/{id}")
	ResponseEntity<Void> deleteUser(@PathVariable long id){
		userService.deleteUser(id);
		return new ResponseEntity<>(HttpStatus.OK);
	} 
	
	@PutMapping("/users/{id}")
	ResponseEntity<User> updateUser(@PathVariable long id, @Valid @RequestBody UserDto userDto) {
		return new ResponseEntity<>(userService.updateUser(id, userDto), HttpStatus.OK);
	}

}
