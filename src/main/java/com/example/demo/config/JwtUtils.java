package com.example.demo.config;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service

public class JwtUtils {
	private String secret="ritej";
	
	public String getUsername(String token) {
		return extractClaims(token,Claims::getSubject);
	}
	public Date getExpiration(String token) {
		return extractClaims(token,Claims::getExpiration);
	}
	
	public <T> T extractClaims (String token, Function<Claims,T> claimsresolver) {
		Claims claims=extractAllClaims(token);
		return claimsresolver.apply(claims);
		
	}
	public Claims extractAllClaims(String token){
		return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
	}
	public String createtoken(String email,String userRole) {
		Map<String,Object> claims=new HashMap<>();
		claims.put("role", userRole);
		return Jwts.builder().setClaims(claims).
				setSubject(email).
				setIssuedAt(new Date(System.currentTimeMillis())).
				setExpiration(new Date(System.currentTimeMillis()+1000*60*60*10)).
				signWith(SignatureAlgorithm.HS256, secret).compact();
				
	}
	public Boolean isTokenExpired(String token) {
		return getExpiration(token).before(new Date());
	}
	public Boolean validatetoken(String token,UserDetails user ) {		
		return user.getUsername().equals(getUsername(token))&&!isTokenExpired(token);
	}
	

}
