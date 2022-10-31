package com.example.demo.config;

import java.util.ArrayList;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.entity.User;

import com.example.demo.repository.UserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService{

	@Autowired
	UserRepository userrepository;
	User user;
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
			user=userrepository.findByEmail(username);					
			List<GrantedAuthority> grantedAuthorities = new ArrayList<GrantedAuthority>();
			grantedAuthorities.add(new SimpleGrantedAuthority(user.getRole()));							
			return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(),grantedAuthorities);				
	}
	public User getuserdetails() {
		return user;
	}
}
