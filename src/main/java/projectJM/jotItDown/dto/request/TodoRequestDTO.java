package projectJM.jotItDown.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import projectJM.jotItDown.domain.TodoCategory;

import java.sql.Time;
import java.util.Date;

public class TodoRequestDTO {

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class CreateTodoDTO {
        private String content;
        private Date date;
        private Time setTime;
        private String location;
        private TodoCategory todoCategory;
    }
}
