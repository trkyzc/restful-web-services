package com.example.restful_web_services.business.concretes;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.restful_web_services.business.abstracts.UserService;
import com.example.restful_web_services.dtos.UserDto;
import com.example.restful_web_services.entities.User;
import com.example.restful_web_services.mapper.UserMapper;
import com.example.restful_web_services.repository.abstracts.UserRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class UserManager implements UserService {
	
	UserRepository userRepository;
	
	UserMapper userMapper;
	
	

	public UserManager(UserRepository userRepository,UserMapper userMapper) {
		super();
		this.userRepository = userRepository;
		this.userMapper=userMapper;
		
	}

	@Override
	public List<User> getUsers() {
		
		return userRepository.findAll();
	}

	@Override
	public Optional<User> getUserById(long id) {
		
		Optional<User> user = userRepository.findById(id);
		if (user.isEmpty()) {
			throw new EntityNotFoundException("User not found");
		}
		return user;
	}

	@Override
	public User createUser(UserDto userDto) {
		
		User user = userMapper.toEntity(userDto);
		return userRepository.save(user);
		
	}

	@Override
	public void deleteUser(long id) {
		Optional<User> user = userRepository.findById(id);
		
		if(user.isEmpty()) {
			throw new EntityNotFoundException("User not found");
		}
		
		userRepository.deleteById(id);
		
	}

	@Override
	public User updateUser(long id, UserDto userDto) {
		
		Optional<User> user = userRepository.findById(id);

		if (user.isEmpty()) {
			throw new EntityNotFoundException("User not found");
		}
		
		user.get().setName(userDto.getName());
		user.get().setBirthDate(userDto.getBirthDate());
		
		return userRepository.save(user.get());
		
		
	}



}
