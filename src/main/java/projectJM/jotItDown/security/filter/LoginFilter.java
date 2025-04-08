package projectJM.jotItDown.security.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import projectJM.jotItDown.apiPayload.BaseResponse;
import projectJM.jotItDown.apiPayload.code.status.ErrorStatus;
import projectJM.jotItDown.apiPayload.exception.handler.AuthHandler;
import projectJM.jotItDown.security.JWT.JWTUtil;
import projectJM.jotItDown.security.JWT.refreshToken.JWTRefreshToken;
import projectJM.jotItDown.security.JWT.refreshToken.JWTRefreshTokenRepository;
import projectJM.jotItDown.security.PrincipalDetails;
import projectJM.jotItDown.dto.request.LoginRequestDTO;

import java.io.IOException;
import java.util.Collection;
import java.util.Iterator;

@RequiredArgsConstructor
public class LoginFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;
    private final JWTUtil jwtUtil;
    private final JWTRefreshTokenRepository jwtRefreshTokenRepository;

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException {
        System.out.println("🚀 LoginFilter: attemptAuthentication() 실행됨"); // 디버깅용 로그
        LoginRequestDTO loginRequestDTO = readBody(request);
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = UsernamePasswordAuthenticationToken.unauthenticated(loginRequestDTO.getEmail(),
                loginRequestDTO.getPassword());

        return authenticationManager.authenticate(usernamePasswordAuthenticationToken);
    }

    public LoginRequestDTO readBody (HttpServletRequest request) {
        ObjectMapper om = new ObjectMapper();

        try {
            return om.readValue(request.getInputStream(), LoginRequestDTO.class);
        } catch (IOException e) {
            throw new AuthHandler(ErrorStatus._BAD_REQUEST);
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        PrincipalDetails principalDetails = (PrincipalDetails) authResult.getPrincipal();
        String email = principalDetails.getUsername();

        // authority
        Collection<? extends GrantedAuthority> authorities = authResult.getAuthorities();
        Iterator<? extends GrantedAuthority> iterator = authorities.iterator();
        GrantedAuthority grantedAuthority = iterator.next();
        String role = grantedAuthority.getAuthority();

        // 토큰 만들기
        String accessToken = jwtUtil.createAccessToken(email, role);
        String refreshToken = jwtUtil.createRefreshToken(email,role);
        System.out.println("🚀 생성된 토큰: " + accessToken);

        // 기존 Refresh Token 삭제
        if (jwtRefreshTokenRepository.existsByEmail(email))
            jwtRefreshTokenRepository.deleteByEmail(email);

        // Refresh Token DB, Cookie에 저장하여 보관
        JWTRefreshToken jwtRefreshToken = new JWTRefreshToken(refreshToken, email);
        jwtRefreshTokenRepository.save(jwtRefreshToken);

        Cookie refreshTokenCookie = new Cookie("Refresh-Token", refreshToken);
        refreshTokenCookie.setHttpOnly(true);
        refreshTokenCookie.setPath("/");
        refreshTokenCookie.setMaxAge((int)(jwtUtil.getRefreshTokenValidityMilliseconds() / 1000));
        response.addCookie(refreshTokenCookie);

        // Access Token 발급 성공 응답
        response.setHeader("Authorization", "Bearer " + accessToken);
        response.setStatus(HttpStatus.OK.value());

        BaseResponse<Object> successResponse = BaseResponse.onSuccess(null);
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(response.getOutputStream(), successResponse);
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
        response.setContentType("application/json; chartset=UTF-8");
        response.setStatus(HttpStatus.UNAUTHORIZED.value());

        ErrorStatus unauthorized = ErrorStatus._AUTHENTICATION_FAILED;

        BaseResponse<Object> errorResponse = BaseResponse.onFailure(unauthorized.getCode(), unauthorized.getMessage(), null);
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(response.getOutputStream(), errorResponse);
    }
}
