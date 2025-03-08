package projectJM.jotItDown.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import projectJM.jotItDown.domain.Member;
import projectJM.jotItDown.domain.enums.Color;

public class TodoCategoryRequestDTO {

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class CreateTodoCategoryDTO {
        private Long id;
        private String title;
        private Color color;
        private Member member;
    }
}
