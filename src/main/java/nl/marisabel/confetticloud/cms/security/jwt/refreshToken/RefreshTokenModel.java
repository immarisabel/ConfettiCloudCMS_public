package nl.marisabel.confetticloud.cms.security.jwt.refreshToken;

import jakarta.persistence.*;
import lombok.*;
import nl.marisabel.confetticloud.cms.security.user.model.UserModel;

import java.time.Instant;


@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "REFRESH_TOKENS")
public class RefreshTokenModel {
 @Id
 @GeneratedValue(strategy = GenerationType.IDENTITY)
 @Column(name = "id", nullable = false)
 private Long id;

 private String token;
 private String username;
 private Instant expiration;

 // map to user entity

 @OneToOne
 @JoinColumn(name = "user_id", referencedColumnName = "id")
 private UserModel userInfo;


}
