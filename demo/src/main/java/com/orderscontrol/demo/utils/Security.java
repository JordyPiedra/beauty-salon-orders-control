package com.orderscontrol.demo.utils;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import com.orderscontrol.demo.dto.UserDto;
import com.orderscontrol.demo.entity.User;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class Security {

	private final String HEADER = "Authorization";
	private final String PREFIX = "Bearer ";
	private final static String SECRET = "mySecretKey";

	public static String getUsernameFromToken() {
		Jwt jwt = (Jwt) SecurityContextHolder.getContext().getAuthentication().getCredentials();

		String username;
		try {
			final Claims claims = getClaimsFromToken(jwt.getBody().toString());
			username = claims.getSubject();
		} catch (Exception e) {
			username = null;
		}
		return username;
	}

	public static Claims getClaimsFromToken(String token) {
		Claims claims;
		try {
			claims = Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token).getBody();
		} catch (Exception e) {
			claims = null;
		}
		return claims;
	}

	public static String getJWTToken(User user) {
		String secretKey = SECRET;
		// List<GrantedAuthority> grantedAuthorities = getAuthorities(user);
		String token = Jwts.builder().setId("ordersControlJWT").setSubject(user.getUsername())
				.claim("authorities",
						getAuthorities(user).stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()))
				.claim("isAdmin", false).setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + 600000))
				.signWith(SignatureAlgorithm.HS512, secretKey.getBytes()).compact();

		return "Bearer " + token;
	}

	private static Set<? extends GrantedAuthority> getAuthorities(User retrievedUser) {
		Set<SimpleGrantedAuthority> authorities = new HashSet<>();
		authorities.add(new SimpleGrantedAuthority("ROLE_" + retrievedUser.getRole()));
		return authorities;
	}

	public static UserDto getCurrentUser() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		UserDto user = new UserDto();
		if (!(authentication instanceof AnonymousAuthenticationToken)) {

			user.setUsername(authentication.getName());
		} else {
			user.setUsername("SYSTEM");
		}

		return user;
	}
}
