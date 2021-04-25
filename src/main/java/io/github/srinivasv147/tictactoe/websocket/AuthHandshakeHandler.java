package io.github.srinivasv147.tictactoe.websocket;

import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.security.Principal;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.http.NameValuePair;
import org.apache.http.client.utils.URLEncodedUtils;
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
        URI uri = request.getURI();
		List<NameValuePair> params = URLEncodedUtils.parse(uri, StandardCharsets.UTF_8);
		params = params.stream()
				.filter(param -> param.getName().equals("token")).collect(Collectors.toList());
		if(params.size() != 1) return null;
		else {
			String queryOtp = params.get(0).getValue();
			Optional<Otp> otp = otpRepository.findOneByToken(queryOtp);
			if (!otp.isPresent()) return null;
			String user = otp.get().getUser().getUsrId();
			return new StompPrincipal(user);
		}
    }
	
}
