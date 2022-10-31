package com.example.demo.Util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;

import org.springframework.stereotype.Service;

import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
@Service
public class UserUtils {
	@Autowired
	UserRepository userRepository;

	public User getConnectedUser() {
		String username=SecurityContextHolder.getContext().getAuthentication().getName().toString();
		return  userRepository.findByEmail(username);
	
	}
	

}
