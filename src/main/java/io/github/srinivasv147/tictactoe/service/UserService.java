package io.github.srinivasv147.tictactoe.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import io.github.srinivasv147.tictactoe.entities.User;
import io.github.srinivasv147.tictactoe.repository.UserRepository;

@Component
public class UserService {
	
	@Autowired
	UserRepository userRepository;

	public Boolean createUser(String email, String userId) {
		try {
			User user = new User(email, userId);
			userRepository.saveAndFlush(user);
			return true;
		}
		catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public Boolean checkUserByEmail(String email) {
		
		Optional<User> user = userRepository.findOneByEmail(email);
		if(user.isPresent()) return true;
		return false;
	}

	public User getUserByEmail(String email) {
		Optional<User> user = userRepository.findOneByEmail(email);
		if(user.isPresent()) return user.get();
		return null;
	}

}
