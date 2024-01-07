package nl.marisabel.confetticloud.cms.security.user.controller;

import jakarta.validation.Valid;
import nl.marisabel.confetticloud.cms.security.dto.LoginRequest;
import nl.marisabel.confetticloud.cms.security.dto.LoginResponse;
import nl.marisabel.confetticloud.cms.security.jwt.JwtResponseDto;
import nl.marisabel.confetticloud.cms.security.jwt.JwtTokenService;
import lombok.RequiredArgsConstructor;
import nl.marisabel.confetticloud.cms.security.jwt.refreshToken.RefreshTokenDto;
import nl.marisabel.confetticloud.cms.security.jwt.refreshToken.RefreshTokenModel;
import nl.marisabel.confetticloud.cms.security.jwt.refreshToken.RefreshTokenServiceImplemetation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping("${url.mapping.secret-passage.v1}")
public class LoginController {

 private final JwtTokenService jwtTokenService;
 private final RefreshTokenServiceImplemetation refreshTokenService;


 @PostMapping("/login")
 public JwtResponseDto loginRequest(@Valid @RequestBody LoginRequest loginRequest) {

  RefreshTokenModel refreshToken = refreshTokenService.createRefreshToken(loginRequest.getUsername());

  final LoginResponse loginResponse = jwtTokenService.getLoginResponse(loginRequest);

  return JwtResponseDto.builder()
          .token(loginResponse.getToken())
          .refreshToken(refreshToken.getToken())
          .build();

 }


 @RequestMapping("/refresh")
 public JwtResponseDto refreshLoginRequest(@RequestBody RefreshTokenDto refreshTokenDto, LoginRequest loginRequest) {


  return refreshTokenService.findByToken(refreshTokenDto.getToken())
          .map(refreshTokenService::verifyExpiration)
          .map(RefreshTokenModel::getUserInfo)
          .map(userInfo -> {
           String username = userInfo.getUsername();
           String accessToken = jwtTokenService.getLoginResponse(loginRequest).getToken();
           return JwtResponseDto.builder()
                   .token(accessToken)
                   .refreshToken(refreshTokenDto.getToken())
                   .build();
          })
          .orElseThrow(() -> new RuntimeException("Refresh token has expired."));

 }

}
