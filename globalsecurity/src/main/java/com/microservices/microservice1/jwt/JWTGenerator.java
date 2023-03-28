package com.microservices.microservice1.jwt;

import java.util.Date;

import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class JWTGenerator {
	public String genetateToken(Authentication authentication) {
		String username=authentication.getName();
		Date now=new Date();
		Date expireDate=new Date(now.getTime()+SecurityConstants.JWT_EXPIRATION);
		
		String token=Jwts.builder()
				.setSubject(username)
				.setIssuedAt(new Date())
				.setExpiration(expireDate)
				.signWith(SignatureAlgorithm.HS512, SecurityConstants.JWT_SECRET)
				.compact();
		
		return token;
	}
	
	public boolean validateToken(String token) {
		try {
			Jwts.parser().setSigningKey(SecurityConstants.JWT_SECRET).parseClaimsJws(token);
			return true;
		}
		catch (Exception e) {
			throw new AuthenticationCredentialsNotFoundException("JWT was expired or incorrect...");
		}
	}
	
	public String getUsernameFromToken(String token) {
		try {
			Claims claims=Jwts.parser().setSigningKey(SecurityConstants.JWT_SECRET).parseClaimsJws(token).getBody();
			return claims.getSubject();
		}
		catch (Exception e) {
			throw new AuthenticationCredentialsNotFoundException("JWT was expired or incorrect...");
		}
	}
	
	public Date getExpireDate(String token) {
		try {
			Claims claims=Jwts.parser().setSigningKey(SecurityConstants.JWT_SECRET).parseClaimsJws(token).getBody();
			return claims.getExpiration();
		}
		catch (Exception e) {
			throw new AuthenticationCredentialsNotFoundException("JWT was expired or incorrect...");
		}
	}
}
