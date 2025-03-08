package projectJM.jotItDown.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import projectJM.jotItDown.apiPayload.BaseResponse;
import projectJM.jotItDown.converter.TodoCategoryConverter;
import projectJM.jotItDown.domain.Member;
import projectJM.jotItDown.domain.TodoCategory;
import projectJM.jotItDown.dto.request.TodoCategoryRequestDTO;
import projectJM.jotItDown.dto.response.TodoCategoryResponseDTO;
import projectJM.jotItDown.service.TodoCategoryService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/todoCategoryList")
public class TodoCategoryRestController {

    private final TodoCategoryService todoCategoryService;

    @PostMapping
    public BaseResponse<TodoCategoryResponseDTO.CreateTodoCategoryResultDTO> createTodoCategory (@AuthenticationPrincipal Member member, @RequestBody TodoCategoryRequestDTO.CreateTodoCategoryDTO createTodoCategoryDTO) {
        TodoCategory todoCategory = todoCategoryService.createTodoCategory(member, createTodoCategoryDTO);
        return BaseResponse.onSuccess(TodoCategoryConverter.toCreateTodoCategoryResult(todoCategory));
    }

}
