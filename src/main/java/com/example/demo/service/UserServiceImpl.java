package com.example.demo.service;


import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.example.demo.config.CustomUserDetailsService;
import com.example.demo.config.JwtUtils;
import com.example.demo.dto.UserLoginDtoResponse;
import com.example.demo.entity.User;
import com.example.demo.model.LoginData;

import com.example.demo.repository.UserRepository;


@Service

public class UserServiceImpl implements UserService{

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private JwtUtils jwtUtils;
	@Autowired
	private CustomUserDetailsService customUserDetailsService;
	@Autowired
	PasswordEncoder passwordEncoder;

	@Override
	public String SignUp(User user) {
		// TODO Auto-generated method stub
		
		String password=user.getPassword();
		user.setPassword(passwordEncoder.encode(password));
		user.setRole("USER");
		User findedUser=userRepository.findByEmail(user.getEmail());
		if(findedUser!=null) {
			return "email already used. try with anathor one";
		}
		userRepository.save(user);		
		return "Successfully Sign Up";
	}
	@Override
	public ResponseEntity<UserLoginDtoResponse> login(LoginData data) {
		UserLoginDtoResponse response = new UserLoginDtoResponse();
		try {
		
			UserDetails user=customUserDetailsService.loadUserByUsername(data.getEmail());
			Authentication auth=authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(data.getEmail(),data.getPassword()));
			SecurityContextHolder.getContext().setAuthentication(auth);					
			String token=jwtUtils.createtoken(customUserDetailsService.getuserdetails().getEmail(),customUserDetailsService.getuserdetails().getRole());
			Set<String> roles = user.getAuthorities().stream().map(r -> r.getAuthority()).collect(Collectors.toSet());
			response.setToken(token);
			response.setRoles(roles.stream().collect(Collectors.toList()));
			return new ResponseEntity<UserLoginDtoResponse>(response,HttpStatus.OK);
			
		}catch(Exception ex) {
			
			ex.printStackTrace();
		}
	return new ResponseEntity<UserLoginDtoResponse>(response,HttpStatus.INTERNAL_SERVER_ERROR);
}
	@Override
	public List<User> listUsers() {
		String username=SecurityContextHolder.getContext().getAuthentication().getName().toString();
		return userRepository.findallusers(username);

	}




	

}
