package com.example.demo.config;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class JwtFilter extends OncePerRequestFilter{

	@Autowired
	JwtUtils jwtutils;
	@Autowired
	CustomUserDetailsService customUserDetailsService;
	
	String username;
	Claims claims;
	@Override
	protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain)
			throws ServletException, IOException {
		
		if(httpServletRequest.getServletPath().matches("/login|/signup")) {
			filterChain.doFilter(httpServletRequest, httpServletResponse);
		}
		else {
			String authorizationHeader=httpServletRequest.getHeader("Authorization");
			String token=null;
			if(authorizationHeader!=null && authorizationHeader.startsWith("Bearer")) {
				 token=authorizationHeader.substring(7);
				 username=jwtutils.getUsername(token);
				 claims=jwtutils.extractAllClaims(token);	
				 log.info("username is "+ username);
			}
			if(username!=null && SecurityContextHolder.getContext().getAuthentication()==null) {
				 log.info("not connected");
				UserDetails userdetails=customUserDetailsService.loadUserByUsername(username);
				if(jwtutils.validatetoken(token, userdetails)) {
					log.info("valide");
					log.info(userdetails.getUsername());
					log.info(userdetails.getPassword());
					log.info(userdetails.getAuthorities().toString());
					
					UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken= new UsernamePasswordAuthenticationToken(userdetails,userdetails.getPassword(),userdetails.getAuthorities());
					
					usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(httpServletRequest));
					
					SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
				log.info(SecurityContextHolder.getContext().getAuthentication().toString());
				}
				log.info("not valide");
			}
			log.info("not connected");
		}
		filterChain.doFilter(httpServletRequest, httpServletResponse);

	}

}

