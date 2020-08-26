package io.github.srinivasv147.tictactoe.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import io.github.srinivasv147.tictactoe.service.JwtUtils;

@Component
public class JwtRequestFilter extends OncePerRequestFilter{
	
	@Autowired
	JwtUtils jwtUtils;
	
	@Autowired
	UserDetailsService fixedUserDetailsService;
	
	private static final String AUTH_HEADER = "Authorization";
	private static final String BEARER = "Bearer";
	private static final String FIXED_USER = "const-user";
	
	private void nullifySecurityToken() {
		// I am afraid that someone might send the constant user access token that I have set so
		// I will null any incoming access token for good measure.
		SecurityContextHolder.getContext().setAuthentication(null);
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		final String authHeader = request.getHeader(AUTH_HEADER);
		
		if(authHeader != null && authHeader.startsWith(BEARER)) {
			String jwt = authHeader.substring(7);
			try {
				String userId = jwtUtils.getUsernameFromToken(jwt);//if this fails the jwt is wrong.
				
				if(jwtUtils.validateToken(jwt, userId)) {//checks if token is expired
					UserDetails user = fixedUserDetailsService.loadUserByUsername(FIXED_USER);
					UsernamePasswordAuthenticationToken userPassAuthToken 
					= new UsernamePasswordAuthenticationToken(user, "default", user.getAuthorities());
					SecurityContextHolder.getContext().setAuthentication(userPassAuthToken);
				}
				else {
					//if jwt is expired
					nullifySecurityToken();
				}
			}
			catch(Exception e) {
				//if jwt is tampered with
				nullifySecurityToken();
				e.printStackTrace();
			}
		}
		else {
			//if there is no jwt
			nullifySecurityToken();
		}
		filterChain.doFilter(request, response);
		
	}

}
