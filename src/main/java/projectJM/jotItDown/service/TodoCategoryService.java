package projectJM.jotItDown.service;

import projectJM.jotItDown.domain.Member;
import projectJM.jotItDown.domain.TodoCategory;
import projectJM.jotItDown.dto.request.TodoCategoryRequestDTO;

public interface TodoCategoryService {

    TodoCategory createTodoCategory (Member member, TodoCategoryRequestDTO.CreateTodoCategoryDTO createTodoCategoryDTO);
}
