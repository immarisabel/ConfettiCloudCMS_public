package nl.marisabel.confetticloud.cms.security.service;

import nl.marisabel.confetticloud.cms.security.user.service.UserValidationService;
import nl.marisabel.confetticloud.cms.security.user.model.UserModel;
import nl.marisabel.confetticloud.cms.security.user.model.UserRole;
import nl.marisabel.confetticloud.cms.security.dto.AuthenticatedUserDto;
import nl.marisabel.confetticloud.cms.security.dto.RegistrationRequest;
import nl.marisabel.confetticloud.cms.security.dto.RegistrationResponse;
import nl.marisabel.confetticloud.cms.security.mapper.UserMapper;
import nl.marisabel.confetticloud.cms.utils.GeneralMessageAccessor;
import nl.marisabel.confetticloud.cms.security.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

	private static final String REGISTRATION_SUCCESSFUL = "registration_successful";

	private final UserRepository userRepository;

	private final BCryptPasswordEncoder bCryptPasswordEncoder;

	private final UserValidationService userValidationService;

	private final GeneralMessageAccessor generalMessageAccessor;

	@Override
	public UserModel findByUsername(String username) {

		return userRepository.findByUsername(username);
	}

	@Override
	public RegistrationResponse registration(RegistrationRequest registrationRequest) {

		userValidationService.validateUser(registrationRequest);

		final UserModel userModel = UserMapper.INSTANCE.convertToUser(registrationRequest);
		userModel.setPassword(bCryptPasswordEncoder.encode(userModel.getPassword()));
		userModel.setUserRole(UserRole.USER);

		userRepository.save(userModel);

		final String username = registrationRequest.getUsername();
		final String registrationSuccessMessage = generalMessageAccessor.getMessage(null, REGISTRATION_SUCCESSFUL, username);

		log.info("{} registered successfully!", username);

		return new RegistrationResponse(registrationSuccessMessage);
	}

	@Override
	public AuthenticatedUserDto findAuthenticatedUserByUsername(String username) {

		final UserModel userModel = findByUsername(username);

		return UserMapper.INSTANCE.convertToAuthenticatedUserDto(userModel);
	}
}
