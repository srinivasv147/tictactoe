package io.github.srinivasv147.tictactoe.service;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.github.srinivasv147.tictactoe.dto.LoginDTO;
import io.github.srinivasv147.tictactoe.dto.LoginResDTO;
import io.github.srinivasv147.tictactoe.entities.User;

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
	
	@Autowired
	UserService userService;
	
	@Value("${google.oauth.clientID}")
	String gclientId;
	
	public LoginResDTO generateLoginRes(LoginDTO loginDTO) {
		Boolean gTokenIsValid = checkValidEmail(loginDTO);
		return generateLoginRes(loginDTO, gTokenIsValid);
	}
	
	public LoginResDTO generateLoginRes(LoginDTO loginDTO, Boolean isValidEmail) {
		if(isValidEmail) {
			User user = userService.getUserByEmail(loginDTO.getEmail());
			if(user != null)
				return new LoginResDTO(jwtUtils.generateToken(user.getUsrId()), true, true
						, userService.getUserByEmail(loginDTO.getEmail()).getUsrId());
			else return new LoginResDTO(null, true, false, null);
		}
		else {
			return new LoginResDTO(null, false, false, null);
		}
	}

	public Boolean checkValidEmail(LoginDTO loginDTO) {
		JsonFactory jsonFactory = new JacksonFactory();
		GoogleIdTokenVerifier verifier =  new GoogleIdTokenVerifier.Builder(transport, jsonFactory)
				.setAudience(Collections.singletonList(gclientId)).build();
		try {
			GoogleIdToken idToken = verifier.verify(loginDTO.getToken());
			if(idToken != null) {
				Payload payload = idToken.getPayload();
				return payload.getEmail().equals(loginDTO.getEmail());
			}
		} catch (GeneralSecurityException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
}
