package projectJM.jotItDown.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import projectJM.jotItDown.domain.enums.SignUpType;

import java.time.LocalDateTime;

public class MemberResponseDTO {

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class JoinResultDTO {
        private Long memberId;
        private String email;
        private LocalDateTime createdAt;
    }

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class memberPreviewDTO {
        private Long memberId;
        private String email;
        private SignUpType signUpType;
        private String nickname;
    }
}
