package projectJM.jotItDown.dto.request;

import lombok.Getter;
import projectJM.jotItDown.domain.enums.SignUpType;


public class MemberRequestDTO {

    @Getter
    public static class JoinDTO {
        private String email;
        private String password;
        private SignUpType signUpType;
    }

    @Getter
    public static class MemberUpdateDTO {
        private String nickname;
        private String profileUrl;
        private String introMessage;
    }
}
