package projectJM.jotItDown.config.JWT;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.filter.OncePerRequestFilter;
import projectJM.jotItDown.apiPayload.code.status.ErrorStatus;
import projectJM.jotItDown.apiPayload.exception.handler.AuthHandler;
import projectJM.jotItDown.config.JWT.refreshToken.JWTRefreshTokenRepository;
import projectJM.jotItDown.config.PrincipalDetailsService;

import java.io.IOException;

@RequiredArgsConstructor
public class JWTFilter extends OncePerRequestFilter {

    private final JWTUtil jwtUtil;
    private final PrincipalDetailsService principalDetailsService;
    private final JWTRefreshTokenRepository jwtRefreshTokenRepository;

    // JWT 검증
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // JWT header
        System.out.println("🔍 JWTFilter 실행됨!");
        String authHeader = request.getHeader("Authorization");

        if (authHeader != null && authHeader.startsWith("Bearer")) {
            String token = authHeader.substring(7);

            try {
                if (jwtUtil.validateToken(token)) {
                    String email = jwtUtil.getEmail(token);
                    UserDetails userDetails = principalDetailsService.loadUserByUsername(email);

                    if (userDetails != null) {
                        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
                        SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
                    } else {
                        throw new AuthHandler(ErrorStatus._NOT_FOUND_MEMBER);
                    }
                }
            } catch (ExpiredJwtException e) {
                System.out.println("⏳ Access Token 만료됨!");
                handleExpiredAccessToken(request, response);
            } catch (JwtException e) {
                System.out.println("🚨 JWT 검증 실패: " + e.getMessage());
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                response.getWriter().write("Unauthorized");
                return;
            }
        }
        filterChain.doFilter(request, response);
    }

    // Access Token 만료 시
    private void handleExpiredAccessToken (HttpServletRequest request, HttpServletResponse response) {
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
