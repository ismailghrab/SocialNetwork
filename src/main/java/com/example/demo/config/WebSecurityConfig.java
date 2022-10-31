package com.example.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;

@EnableWebSecurity
public class WebSecurityConfig {
	@Autowired
	private CustomUserDetailsService customUserDetailsService;
	@Autowired
	private JwtFilter jwtFilter;
	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	 @Bean
	 public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
		  return authenticationConfiguration.getAuthenticationManager();}
		
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		AuthenticationManagerBuilder auth=http.getSharedObject(AuthenticationManagerBuilder.class);
	auth.userDetailsService(customUserDetailsService);
	http.cors().configurationSource(request -> new CorsConfiguration().applyPermitDefaultValues())
	.and()
	.csrf().disable()
	.authorizeRequests()
	.antMatchers("/Login","/Signup")
	.permitAll()
	.anyRequest()
	.authenticated()
	.and()
	.exceptionHandling()
	.and()
	.sessionManagement()
	.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
	http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
	return http.build();
	
	}
	
}
