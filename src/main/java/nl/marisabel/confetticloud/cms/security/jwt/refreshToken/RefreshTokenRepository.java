package nl.marisabel.confetticloud.cms.security.jwt.refreshToken;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RefreshTokenRepository extends JpaRepository<RefreshTokenModel, Long> {

 Optional<RefreshTokenModel> findByToken(String token);
}
