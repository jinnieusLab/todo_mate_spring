package projectJM.jotItDown.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.sql.Time;
import java.util.Date;

public class TodoResponseDTO {

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class CreateTodoResultDTO {
        private Long id;

        private String content;

        private Date date;

        private Time setTime;

        private String location;

        private Long memberId;

        private Long todoCategoryId;
    }
}
