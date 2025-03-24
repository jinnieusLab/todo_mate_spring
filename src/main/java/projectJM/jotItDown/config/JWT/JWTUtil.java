package projectJM.jotItDown.config.JWT;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import projectJM.jotItDown.apiPayload.code.status.ErrorStatus;
import projectJM.jotItDown.apiPayload.exception.handler.AuthHandler;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.time.ZonedDateTime;
import java.util.Date;

@Slf4j
@Component
public class JWTUtil {

    private final SecretKey secretKey;
    private final long accessTokenValidityMilliseconds;

    // JWTUtil 생성자
    public JWTUtil(@Value("${spring.jwt.secret}") final String secretKey,
                   @Value("${spring.jwt.access-token-time}") final long accessTokenValidityMilliseconds) {
        this.secretKey = Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8));
        this.accessTokenValidityMilliseconds = accessTokenValidityMilliseconds;
    }

    // AccessToken 생성
    public String createAccessToken(String email, String role) {
        return createToken(email, role, accessTokenValidityMilliseconds);
    }

    // 기본 Token 생성
    // Payload에 사용자 정보 Claims 추가
    private String createToken(String email, String role, long validityMilliseconds) {
        Claims claims = Jwts.claims();
        claims.put("email", email);
        claims.put("role", role);

        // 토큰 이용 시간 (현재, 만료 시간)
        ZonedDateTime now = ZonedDateTime.now();
        ZonedDateTime tokenValidity = now.plusSeconds(validityMilliseconds / 1000);

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(Date.from(now.toInstant()))
                .setExpiration(Date.from(tokenValidity.toInstant()))
                .signWith(secretKey, SignatureAlgorithm.HS256)
                .compact();
    }

    private Jws<Claims> getClaims(String token) {
        return Jwts.parserBuilder().setSigningKey(secretKey).build().parseClaimsJws(token);
    }

    public String getEmail(String token) {
        return getClaims(token).getBody().get("email", String.class);
    }

    // JWT 검증 (JWTFilter에서 사용)

    public boolean validateToken(String token) {
        try {
            Jws<Claims> claims = getClaims(token);
            Date now = new Date();
            Date expiredDate = claims.getBody().getExpiration();
            return expiredDate.after(now);
        } catch (ExpiredJwtException e) {
            throw new AuthHandler(ErrorStatus._AUTH_EXPIRE_TOKEN);
        } catch (SignatureException | SecurityException | IllegalStateException | MalformedJwtException | UnsupportedJwtException e) {
            throw new AuthHandler(ErrorStatus._AUTH_INVALID_TOKEN);
        }
    }

}
