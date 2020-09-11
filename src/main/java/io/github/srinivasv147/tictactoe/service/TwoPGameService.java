package io.github.srinivasv147.tictactoe.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import io.github.srinivasv147.tictactoe.dto.ChallengeDTO;
import io.github.srinivasv147.tictactoe.entities.TwoPGame;
import io.github.srinivasv147.tictactoe.repository.TwoPGameRepository;

@Service
public class TwoPGameService {
	
	@Autowired
	TwoPGameRepository gameRepository;

	@Transactional(isolation = Isolation.SERIALIZABLE)
	public TwoPGame createGame(ChallengeDTO challenge) {
		
		return null;
	}

	public boolean checkActiveGame(String name) {
		// TODO Auto-generated method stub
		return false;
	}
	
	

}
