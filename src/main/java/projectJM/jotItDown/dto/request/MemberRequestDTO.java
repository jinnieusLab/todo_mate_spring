package projectJM.jotItDown.dto.request;

import lombok.Getter;
import projectJM.jotItDown.domain.enums.SignUpType;


public class MemberRequestDTO {

    @Getter
    public static class JoinDTO {
        private Long memberId;
        private String email;
        private String password;
        private SignUpType signUpType;
    }
}
