package nl.marisabel.confetticloud.cms.security.jwt.refreshToken;

import lombok.RequiredArgsConstructor;
import nl.marisabel.confetticloud.cms.security.user.model.UserModel;
import nl.marisabel.confetticloud.cms.security.user.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RefreshTokenServiceImplemetation implements RefreshTokenService {
 private final RefreshTokenRepository refreshTokenRepository;
private final UserRepository userRepository;

 @Override
 public RefreshTokenModel createRefreshToken(String username) {
  RefreshTokenModel newToken = RefreshTokenModel.builder()
          .userInfo(userRepository.findByUsername(username))
          .token(UUID.randomUUID().toString())
          .expiration(Instant.now().plusSeconds(60*30))
          .build();
 return refreshTokenRepository.save(newToken);
 }

 @Override
 public Optional<RefreshTokenModel> findByToken(String token) {
  return refreshTokenRepository.findByToken(token);
 }

 public RefreshTokenModel verifyExpiration(RefreshTokenModel token) {
  if (token.getExpiration().isBefore(Instant.now())) {
   refreshTokenRepository.delete(token);
   throw new RuntimeException("Refresh token has expired.");
  }
  return token;
 }
}
