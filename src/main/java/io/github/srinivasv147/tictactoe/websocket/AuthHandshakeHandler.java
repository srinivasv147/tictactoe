package io.github.srinivasv147.tictactoe.websocket;

import java.security.Principal;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.support.DefaultHandshakeHandler;

import io.github.srinivasv147.tictactoe.entities.Otp;
import io.github.srinivasv147.tictactoe.repository.OtpRepository;

@Component("authHandshakeHandler")
public class AuthHandshakeHandler extends DefaultHandshakeHandler {
	
	@Autowired
	OtpRepository otpRepository;
	
	@Override
    protected Principal determineUser(ServerHttpRequest request,
                                      WebSocketHandler wsHandler,
                                      Map<String, Object> attributes) {
        String query = request.getURI().getQuery();
        if(query.startsWith("token")) {
        	String[] keyValue = query.split("=");
        	if( keyValue.length != 2 || !keyValue[0].equals("token")) return null;
        	Optional<Otp> otp = otpRepository.findOneByToken(keyValue[1]);
        	if(!otp.isPresent()) return null;
        	String user = otp.get().getUser().getUsrId();
        	return new StompPrincipal(user);
        }
        else return null;
    }
	
}
