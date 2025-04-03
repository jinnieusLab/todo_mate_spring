package projectJM.jotItDown.config.JWT.refreshToken;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JWTRefreshTokenRepository extends JpaRepository<JWTRefreshToken, Long> {

    boolean existsByToken(String refreshToken);
}
