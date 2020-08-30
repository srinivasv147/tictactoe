package io.github.srinivasv147.tictactoe.security;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import io.github.srinivasv147.tictactoe.service.UserService;

@Service
public class PrincipalUserDetailsService implements UserDetailsService {
	
	@Autowired
	UserService userService;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		//since we validate the jwt we use a constant user for any person.
		if(userService.checkUserByUserId(username)) {
			return new User(username, "default", new ArrayList<>());
		}
		else {
			throw new UsernameNotFoundException("user "+username+" was not found");
		}
	}
	
	

}
