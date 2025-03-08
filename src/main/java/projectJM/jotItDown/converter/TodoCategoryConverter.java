package projectJM.jotItDown.converter;

import projectJM.jotItDown.domain.TodoCategory;
import projectJM.jotItDown.dto.request.TodoCategoryRequestDTO;
import projectJM.jotItDown.dto.response.TodoCategoryResponseDTO;

public class TodoCategoryConverter {

    public static TodoCategory toTodoCategory (TodoCategoryRequestDTO.CreateTodoCategoryDTO createTodoCategoryDTO) {
        return TodoCategory.builder()
                .id(createTodoCategoryDTO.getId())
                .title(createTodoCategoryDTO.getTitle())
                .color(createTodoCategoryDTO.getColor())
                .member(createTodoCategoryDTO.getMember())
                .build();
    }

    public static TodoCategoryResponseDTO.CreateTodoCategoryResultDTO toCreateTodoCategoryResult (TodoCategory todoCategory) {
        return TodoCategoryResponseDTO.CreateTodoCategoryResultDTO.builder()
                .id(todoCategory.getId())
                .title(todoCategory.getTitle())
                .color(todoCategory.getColor())
                .build();
    }
}
