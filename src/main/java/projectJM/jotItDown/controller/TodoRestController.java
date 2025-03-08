package projectJM.jotItDown.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import projectJM.jotItDown.apiPayload.BaseResponse;
import projectJM.jotItDown.domain.Member;
import projectJM.jotItDown.dto.request.TodoRequestDTO;
import projectJM.jotItDown.dto.response.TodoResponseDTO;

@RestController
@RequiredArgsConstructor
@RequestMapping("/todoList")
public class TodoRestController {

//    // Todo 생성 코드
//    @PostMapping
//    public BaseResponse<TodoResponseDTO.CreateTodoResultDTO> CreateTodo (@AuthenticationPrincipal Member member, @RequestBody TodoRequestDTO.CreateTodoDTO createTodoDTO) {
//
//    }
}
