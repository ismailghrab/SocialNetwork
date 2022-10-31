package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.dto.UserLoginDtoResponse;
import com.example.demo.entity.User;
import com.example.demo.model.LoginData;
import com.example.demo.service.UserService;

@RestController
@CrossOrigin(value = "http://localhost:4200", allowCredentials = "true")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@PostMapping("/Signup")
	private String Signup(@RequestBody User user){
		return userService.SignUp( user);	
	}
	
	@PostMapping("/Login")
	private ResponseEntity<UserLoginDtoResponse> login(@RequestBody LoginData data){
		return userService.login( data);
	}
	
	@GetMapping("/listusers")
	private List<User> listUsers(){	
		return userService.listUsers()	;
	}
}
