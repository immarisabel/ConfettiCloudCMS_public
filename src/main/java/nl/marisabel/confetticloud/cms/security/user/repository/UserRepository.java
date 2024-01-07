package nl.marisabel.confetticloud.cms.security.user.repository;

import nl.marisabel.confetticloud.cms.security.user.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserModel, Long> {

	UserModel findByUsername(String username);

	boolean existsByEmail(String email);

	boolean existsByUsername(String username);

}
