package nl.marisabel.confetticloud.cms.security.jwt;

import nl.marisabel.confetticloud.cms.security.mapper.UserMapper;
import nl.marisabel.confetticloud.cms.security.service.UserService;
import nl.marisabel.confetticloud.cms.security.user.model.UserModel;
import nl.marisabel.confetticloud.cms.security.dto.AuthenticatedUserDto;
import nl.marisabel.confetticloud.cms.security.dto.LoginRequest;
import nl.marisabel.confetticloud.cms.security.dto.LoginResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class JwtTokenService {

	private final UserService userService;

	private final JwtTokenManager jwtTokenManager;

	private final AuthenticationManager authenticationManager;

	public LoginResponse getLoginResponse(LoginRequest loginRequest) {

		final String username = loginRequest.getUsername();
		final String password = loginRequest.getPassword();

		final UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(username, password);

		authenticationManager.authenticate(usernamePasswordAuthenticationToken);

		final AuthenticatedUserDto authenticatedUserDto = userService.findAuthenticatedUserByUsername(username);

		final UserModel userModel = UserMapper.INSTANCE.convertToUser(authenticatedUserDto);
		final String token = jwtTokenManager.generateToken(userModel);

		log.info("{} has successfully logged in!", userModel.getUsername());

		return new LoginResponse(token);
	}

}
