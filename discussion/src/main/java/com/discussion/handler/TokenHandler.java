package com.discussion.handler;

import java.time.Duration;
import java.util.Base64;
import java.util.Date;
import java.util.UUID;

import org.springframework.security.core.Authentication;

import com.discussion.exception.DiscussionException;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class TokenHandler {
	private static final String SECRET = "jkasjfgfkjhgfiquwfhkjhkjfhkjfhaskjfhaskjfhakffafjahfkajmhfkjfhkjfhjkfhfkjfhakjfh";
	public static String generate(Authentication authentication) {
		
		Date date = new Date();
		
		return  Jwts.builder() 
				.issuedAt(date)
				.expiration(new Date(date.getTime()+Duration.ofMinutes(20).toMillis()))
			    .header()                                   
			        .keyId(UUID.randomUUID().toString())
			        .add("type", "jwt")
			        .and()
			    .subject(authentication.getName())
			    .claims()
			    .add("roles", authentication.getAuthorities().toArray())
			    .and()
			    .signWith(SignatureAlgorithm.HS256, Base64.getEncoder().encode(SECRET.getBytes()))                      
			    .compact();                                 
	}
	
	
	public static boolean validate(String token) {
		try {
			Jwts.parser()
				.setSigningKey(Base64.getEncoder().encode(SECRET.getBytes()))
				.build()
				.parseClaimsJws(token);
			return true;
		} catch (JwtException e) {
				throw new DiscussionException(e);
		}
	} 
	
	public static String  extract(String token) {
		return  Jwts.parser()
				.setSigningKey(Base64.getEncoder().encode(SECRET.getBytes()))
				.build().parseClaimsJws(token)
				.getBody().getSubject();
	}

}
