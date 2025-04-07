package projectJM.jotItDown.config.JWT;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import projectJM.jotItDown.apiPayload.code.status.SuccessStatus;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/refresh")
    public ResponseEntity<String> createNewAccessToken (HttpServletRequest request, HttpServletResponse response) {
        authService.handleExpiredAccessToken(request, response);
        return ResponseEntity.ok(SuccessStatus._OK.getMessage());
    }
}
