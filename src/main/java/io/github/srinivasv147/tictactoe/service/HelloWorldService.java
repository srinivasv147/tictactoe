package io.github.srinivasv147.tictactoe.service;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import io.github.srinivasv147.tictactoe.dto.GreetingDTO;

@RestController
public class HelloWorldService {
	
	@GetMapping("/hello")
	public GreetingDTO greeting() {
		return new GreetingDTO("hello world");
	}

}
