package io.github.srinivasv147.tictactoe.service;

import java.util.Optional;

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
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private OtpRepository otpRepository;
	
	@Value("${otp.length}")
	private Integer otpLength;
	
	public String createOtp(String userId) {
		boolean repeated = true;
		String otp = "";
		User curUser = userService.getUserByUserId(userId);
		if (curUser == null) return "DEFAULT";
		while(repeated) {
			otp = generateRandomString(otpLength);
			//if user exists then update the otp and if not then add otp, if otp exits generate a new otp.
			repeated = addOtptoDb(otp, curUser);
		}
		return otp;
	}
	
	@Transactional(isolation = Isolation.SERIALIZABLE)
	public boolean addOtptoDb(String otp, User curUser) {
		
		return false;
	}

	private String generateRandomString(Integer len) {
		
		String str = "abcdefghijklmnopqrstuvwxyzABCD"
	            +"EFGHIJKLMNOPQRSTUVWXYZ0123456789"; 
	    int n = str.length(); 
	  
	    // String to hold my OTP 
	    StringBuilder otp = new StringBuilder(); 
	  
	    for (int i = 1; i <= len; i++) 
	        otp.append(str.charAt((int) ((Math.random()*10) % n))); 
	  
	    return otp.toString(); 
	}

}
