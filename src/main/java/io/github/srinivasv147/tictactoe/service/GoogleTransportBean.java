package io.github.srinivasv147.tictactoe.service;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;

@Component
public class GoogleTransportBean {
	
	@Bean
	public HttpTransport globalHttpTransportBean() {
		return new NetHttpTransport();
	}

}
