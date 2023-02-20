package com.management.restfulwebservices.JwtAuth;

import java.io.IOException;

import org.apache.tomcat.util.http.parser.Authorization;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class JwtAuthFilter extends OncePerRequestFilter {
	
	
	@Override
	protected void doFilterInternal(
			HttpServletRequest request,
			HttpServletResponse response,
			FilterChain filterChain)throws ServletException, IOException {
		// TODO Auto-generated method stub
		final String authHeader = request.getHeader(ALREADY_FILTERED_SUFFIX);
		final String username;
		final String jwtToken;
		
		if(authHeader == null || !authHeader.startsWith("Bearer")) {
			filterChain.doFilter(request, response);
			return;
		}
		jwtToken = authHeader.substring(7);			// As Bearer has 6 letters
		username = "something"; // TODO to be implemented
//		if(username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
//			UserDetails userDetails = userDetailsService .
//		}
		
	}
}




















