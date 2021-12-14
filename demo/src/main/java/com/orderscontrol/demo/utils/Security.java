package com.orderscontrol.demo.utils;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;

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
            claims = Jwts.parser()
                    .setSigningKey(SECRET)
                    .parseClaimsJws(token)
                    .getBody();
        } catch (Exception e) {
            claims = null;
        }
        return claims;
    }
    
	public static String getJWTToken(String Username) {
		String secretKey = SECRET;
		List<GrantedAuthority> grantedAuthorities = AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_User");

		String token = Jwts.builder().setId("softtekJWT").setSubject(Username)
				.claim("authorities",
						grantedAuthorities.stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()))
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + 600000))
				.signWith(SignatureAlgorithm.HS512, secretKey.getBytes()).compact();

		return "Bearer " + token;
	}
	
 
}
