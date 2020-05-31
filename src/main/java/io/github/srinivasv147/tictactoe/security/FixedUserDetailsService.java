package io.github.srinivasv147.tictactoe.security;

import java.util.ArrayList;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class FixedUserDetailsService implements UserDetailsService{

	private static final String USER = "const-user";
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		//since we validate the jwt we use a constant user for any person.
		if(username == USER) {
			return new User(USER, "default", new ArrayList<>());
		}
		else {
			throw new UsernameNotFoundException("user "+username+"was not found");
		}
	}
	
	

}
