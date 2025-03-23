package projectJM.jotItDown.config.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import projectJM.jotItDown.apiPayload.BaseResponse;
import projectJM.jotItDown.apiPayload.code.status.ErrorStatus;
import projectJM.jotItDown.apiPayload.exception.handler.AuthHandler;
import projectJM.jotItDown.config.JWT.JWTUtil;
import projectJM.jotItDown.config.PrincipalDetails;
import projectJM.jotItDown.dto.request.LoginRequestDTO;

import java.io.IOException;
import java.util.Collection;
import java.util.Iterator;

@RequiredArgsConstructor
public class LoginFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;
    private final JWTUtil jwtUtil;

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
        String token = jwtUtil.createAccessToken(email, role);
        System.out.println("🚀 생성된 토큰: " + token);

        // 성공 응답
        response.setHeader("Authorization", "Bearer " + token);
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
