package io.github.srinivasv147.tictactoe.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import io.github.srinivasv147.tictactoe.dto.GameStateDTO;
import io.github.srinivasv147.tictactoe.dto.LoginDTO;
import io.github.srinivasv147.tictactoe.dto.LoginResDTO;
import io.github.srinivasv147.tictactoe.service.FindMoveService;
import io.github.srinivasv147.tictactoe.service.LoginService;

@RestController
public class TicTacToeController{
	
	@Autowired
	FindMoveService findMove;
	
	@Autowired
	LoginService loginService;
	
	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping("/game/{gameState}")
	public GameStateDTO greeting(
			@PathVariable(value = "gameState") List<Integer> gameState) {
		return findMove.findNextMove(gameState);
	}
	
	@CrossOrigin(origins = "http://localhost:4200")
	@PostMapping("/login")
	public LoginResDTO loginResponse(@RequestBody LoginDTO loginDTO) {
		LoginResDTO res = new LoginResDTO(loginService.generateJwt(loginDTO));
		return res;
	}

}
