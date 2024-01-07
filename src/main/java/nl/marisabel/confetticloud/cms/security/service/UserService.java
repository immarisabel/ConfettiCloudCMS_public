package nl.marisabel.confetticloud.cms.security.service;

import nl.marisabel.confetticloud.cms.security.user.model.UserModel;
import nl.marisabel.confetticloud.cms.security.dto.AuthenticatedUserDto;
import nl.marisabel.confetticloud.cms.security.dto.RegistrationRequest;
import nl.marisabel.confetticloud.cms.security.dto.RegistrationResponse;

public interface UserService {

	UserModel findByUsername(String username);

	RegistrationResponse registration(RegistrationRequest registrationRequest);

	AuthenticatedUserDto findAuthenticatedUserByUsername(String username);

}
