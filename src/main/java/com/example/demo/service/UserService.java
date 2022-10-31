package com.example.demo.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.example.demo.dto.UserLoginDtoResponse;
import com.example.demo.entity.User;

import com.example.demo.model.LoginData;

public interface UserService {
	
	String SignUp(User user);
	ResponseEntity<UserLoginDtoResponse> login(LoginData data);
	List<User> listUsers();
}
