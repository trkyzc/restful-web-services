package com.example.restful_web_services.repository.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.restful_web_services.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {
	

}
