package projectJM.jotItDown.security.JWT.auth;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import projectJM.jotItDown.apiPayload.code.status.ErrorStatus;
import projectJM.jotItDown.apiPayload.exception.handler.AuthHandler;
import projectJM.jotItDown.security.JWT.JWTUtil;
import projectJM.jotItDown.security.JWT.refreshToken.JWTRefreshTokenRepository;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final JWTRefreshTokenRepository jwtRefreshTokenRepository;
    private final JWTUtil jwtUtil;

    // Access Token 만료 시
    public void handleExpiredAccessToken (HttpServletRequest request, HttpServletResponse response) {
        // Refresh Token 검증
        String refreshToken = getRefreshTokenFromCookie(request);

        // Refresh Token 없을 시
        if (refreshToken == null || !jwtRefreshTokenRepository.existsByToken(refreshToken)) {
            throw new AuthHandler(ErrorStatus._AUTH_INVALID_TOKEN);
        }

        if (!jwtUtil.validateToken(refreshToken)) {
            throw new AuthHandler(ErrorStatus._AUTH_INVALID_TOKEN);
        }

        if (jwtRefreshTokenRepository.existsByToken(refreshToken)) {
            String email = jwtUtil.getEmail(refreshToken);
            String role = jwtUtil.getRole(refreshToken);

            String accessToken = jwtUtil.createAccessToken(email, role);
            response.setHeader("Authorization", "Bearer " + accessToken);
            response.setStatus(HttpStatus.OK.value());
        }
    }

    // Cookie에서 Refresh Token 가져오기
    private String getRefreshTokenFromCookie (HttpServletRequest request) {

        if (request.getCookies() == null) {
            System.out.println("🚨 쿠키가 없음!");
            return null;
        }

        for (Cookie cookie : request.getCookies()) {
            if ("Refresh-Token".equals(cookie.getName())) {
                System.out.println("🔍 쿠키 이름: " + cookie.getName() + " | 값: " + cookie.getValue());
                return cookie.getValue();
            }
        }

        System.out.println("🚨 쿠키가 없음!");
        return null;
    }
}
