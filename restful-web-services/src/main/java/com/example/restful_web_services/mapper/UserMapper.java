package com.example.restful_web_services.mapper;


import org.mapstruct.Mapper;

import com.example.restful_web_services.dtos.UserDto;
import com.example.restful_web_services.entities.User;

@Mapper(componentModel = "spring")
public interface UserMapper {
	
	UserDto toDto(User user);
	User toEntity(UserDto userDto);
	

}
