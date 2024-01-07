package nl.marisabel.confetticloud.cms.security.jwt.refreshToken;


import nl.marisabel.confetticloud.cms.security.user.model.UserModel;

import java.util.Optional;

public interface RefreshTokenService {
public RefreshTokenModel createRefreshToken(String username);

public Optional<RefreshTokenModel> findByToken(String token);

 public RefreshTokenModel verifyExpiration(RefreshTokenModel token);


}
