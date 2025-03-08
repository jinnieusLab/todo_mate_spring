package projectJM.jotItDown.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import projectJM.jotItDown.domain.enums.Color;

public class TodoCategoryResponseDTO {

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class CreateTodoCategoryResultDTO {
        private Long id;
        private String title;
        private Color color;
    }
}
