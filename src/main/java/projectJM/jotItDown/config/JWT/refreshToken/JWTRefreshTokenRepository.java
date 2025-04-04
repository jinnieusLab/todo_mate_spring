package projectJM.jotItDown.config.JWT.refreshToken;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface JWTRefreshTokenRepository extends JpaRepository<JWTRefreshToken, Long> {

    boolean existsByToken(String refreshToken);

    @Modifying
    @Transactional
    void deleteByEmail(String email);

    boolean existsByEmail(String email);
}
