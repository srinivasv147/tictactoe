package io.github.srinivasv147.tictactoe.service;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import io.github.srinivasv147.tictactoe.entities.Otp;
import io.github.srinivasv147.tictactoe.entities.User;
import io.github.srinivasv147.tictactoe.repository.OtpRepository;

@Component
public class OtpService {
	
	Logger logger = LoggerFactory.getLogger(OtpService.class);
	
	@Autowired
	private OtpRepository otpRepository;
	
	@Value("${otp.length}")
	private Integer otpLength;
	
	@Transactional(isolation = Isolation.SERIALIZABLE)
	public boolean addOtptoUser(String otp, User curUser) {
		try {
			Optional<Otp> temp = otpRepository.findOneByUser(curUser.getEmail());
			if(temp.isPresent()) {
				temp.get().setToken(otp);
				otpRepository.saveAndFlush(temp.get());
			}
			else {
				otpRepository.saveAndFlush(new Otp(otp,curUser));
			}
		}
		catch(Exception e) {
			e.printStackTrace();
			logger.error(e.toString());
			return false;
		}
		return true;
	}

	public String generateRandomString() {
		
		String str = "abcdefghijklmnopqrstuvwxyzABCD"
	            +"EFGHIJKLMNOPQRSTUVWXYZ0123456789"; 
	    int n = str.length(); 
	  
	    // String to hold my OTP 
	    StringBuilder otp = new StringBuilder(); 
	  
	    for (int i = 1; i <= otpLength; i++) 
	        otp.append(str.charAt((int) ((Math.random()*10) % n))); 
	  
	    return otp.toString(); 
	}

}
