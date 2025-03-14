package projectJM.jotItDown.dto.request;

import lombok.Getter;
import projectJM.jotItDown.domain.enums.Role;
import projectJM.jotItDown.domain.enums.SignUpType;


public class MemberRequestDTO {

    @Getter
    public static class JoinDTO {
        private String email;
        private String password;
        private SignUpType signUpType;
        private Role role;
    }

    @Getter
    public static class MemberUpdateDTO {
        private String nickname;
        private String profileUrl;
        private String introMessage;
    }
}
