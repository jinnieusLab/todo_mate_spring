package projectJM.jotItDown.config.filter;

import io.swagger.v3.core.util.Json;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

public class LoginFilter extends UsernamePasswordAuthenticationFilter {

//    @Override
//    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
//            throws AuthenticationException {
//        String email = Json
//    }
}
