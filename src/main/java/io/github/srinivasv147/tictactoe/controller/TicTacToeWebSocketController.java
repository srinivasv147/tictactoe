package io.github.srinivasv147.tictactoe.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import io.github.srinivasv147.tictactoe.dto.ChallengeDTO;

@Controller
public class TicTacToeWebSocketController {
	
	Logger logger = LoggerFactory.getLogger(TicTacToeWebSocketController.class);
	
	@Autowired
	SimpMessagingTemplate simpleMessagingTemplate;
	
	@MessageMapping("/challenge")
	public void challengePassThrough(ChallengeDTO challenge) throws Exception{
		
		logger.info("got challenge from user {} to user {}",challenge.getChallenger(),challenge.getChallengee());
		
		if(challenge.getChallengee() != null)
			this.simpleMessagingTemplate
			.convertAndSend("/user/"+challenge.getChallengee()+"/topic/challenge", challenge);
		
	}
}
