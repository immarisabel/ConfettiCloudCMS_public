package nl.marisabel.confetticloud.cms.security.jwt;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class JwtResponseDto {

 private String token;
 private String refreshToken;
}
