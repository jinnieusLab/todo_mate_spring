package projectJM.jotItDown.security.JWT.auth;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/refresh")
    public ResponseEntity<String> createNewAccessToken (HttpServletRequest request, HttpServletResponse response) {
        authService.handleExpiredAccessToken(request, response);
        return ResponseEntity.ok("Access Token 재발급 성공");
    }
}
