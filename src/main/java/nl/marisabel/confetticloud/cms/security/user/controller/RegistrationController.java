package nl.marisabel.confetticloud.cms.security.user.controller;

import jakarta.validation.Valid;
import nl.marisabel.confetticloud.cms.security.dto.RegistrationRequest;
import nl.marisabel.confetticloud.cms.security.dto.RegistrationResponse;
import nl.marisabel.confetticloud.cms.security.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping("${url.mapping.secret-passage.v1}/registration")
public class RegistrationController {

	private final UserService userService;

	@PostMapping
	public ResponseEntity<RegistrationResponse> registrationRequest(@Valid @RequestBody RegistrationRequest registrationRequest) {

		final RegistrationResponse registrationResponse = userService.registration(registrationRequest);

		return ResponseEntity.status(HttpStatus.CREATED).body(registrationResponse);
	}

}
