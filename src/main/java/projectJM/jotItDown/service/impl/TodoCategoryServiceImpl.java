package projectJM.jotItDown.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import projectJM.jotItDown.apiPayload.code.status.ErrorStatus;
import projectJM.jotItDown.apiPayload.exception.handler.MemberHandler;
import projectJM.jotItDown.converter.TodoCategoryConverter;
import projectJM.jotItDown.domain.Member;
import projectJM.jotItDown.domain.TodoCategory;
import projectJM.jotItDown.dto.request.TodoCategoryRequestDTO;
import projectJM.jotItDown.repository.TodoCategoryRepository;
import projectJM.jotItDown.service.TodoCategoryService;

@Service
@RequiredArgsConstructor
public class TodoCategoryServiceImpl implements TodoCategoryService {

    private final TodoCategoryRepository todoCategoryRepository;

    @Override
    public TodoCategory createTodoCategory(Member member, TodoCategoryRequestDTO.CreateTodoCategoryDTO createTodoCategoryDTO) {
        if (member == null) throw new MemberHandler(ErrorStatus._NOT_FOUND_MEMBER);

        TodoCategory todoCategory = TodoCategoryConverter.toTodoCategory(createTodoCategoryDTO);
        return todoCategoryRepository.save(todoCategory);
    }
}
