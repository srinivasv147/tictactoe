package io.github.srinivasv147.tictactoe.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.github.srinivasv147.tictactoe.dto.CreateUserDTO;
import io.github.srinivasv147.tictactoe.dto.GameStateDTO;
import io.github.srinivasv147.tictactoe.dto.LoginDTO;
import io.github.srinivasv147.tictactoe.dto.LoginResDTO;
import io.github.srinivasv147.tictactoe.service.FindMoveService;
import io.github.srinivasv147.tictactoe.service.LoginService;
import io.github.srinivasv147.tictactoe.service.UserService;

@CrossOrigin
@RestController
@RequestMapping("/api")
public class TicTacToeController{
	
	@Autowired
	FindMoveService findMove;
	
	@Autowired
	LoginService loginService;
	
	@Autowired
	UserService userService;
	
	@GetMapping("/game/{gameState}")
	public GameStateDTO greeting(
			@PathVariable(value = "gameState") List<Integer> gameState) {
		return findMove.findNextMove(gameState);
	}
	
	@PostMapping("/authenticate")
	public LoginResDTO loginResponse(@RequestBody LoginDTO loginDTO) {
		LoginResDTO res = loginService.generateLoginRes(loginDTO);
		return res;
	}
	@PostMapping("/create-user")
	public LoginResDTO createUser(@RequestBody CreateUserDTO user) {
		Boolean created = false;
		Boolean isEmailValid = loginService.checkValidEmail(user.getLoginDTO());
		if(isEmailValid) {
			created = userService.createUser(user.getLoginDTO().getEmail(), user.getUserId());
			if(created) return loginService.generateLoginRes(user.getLoginDTO(), true);
			else new LoginResDTO(null, false, false, null);
		}
		else return new LoginResDTO(null, false, false, null);
		return null;
	}

}
