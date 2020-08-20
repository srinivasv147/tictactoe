package io.github.srinivasv147.tictactoe.service;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.github.srinivasv147.tictactoe.dto.LoginDTO;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken.Payload;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;

@Component
public class LoginService {
	
	@Autowired
	JwtUtils jwtUtils;
	
	@Autowired
	HttpTransport transport;
	
	@Value("${google.oauth.clientID}")
	String gclientId;
	
	public String generateJwt(LoginDTO loginDTO) {
		Boolean gTokenIsValid = false;
		JsonFactory jsonFactory = new JacksonFactory();
		//Check if google token is right.
		GoogleIdTokenVerifier verifier =  new GoogleIdTokenVerifier.Builder(transport, jsonFactory)
				.setAudience(Collections.singletonList(gclientId)).build();
		try {
			GoogleIdToken idToken = verifier.verify(loginDTO.getToken());
			if(idToken != null) {
				Payload payload = idToken.getPayload();
				gTokenIsValid = payload.getEmail().equals(loginDTO.getEmail());
			}
		} catch (GeneralSecurityException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		//Generate token with email address.
		if(gTokenIsValid) {
			return jwtUtils.generateToken(loginDTO.getEmail());
		}
		else {
			return "DEFAULT";
		}
	}
	
}
