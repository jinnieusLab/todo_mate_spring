package projectJM.jotItDown.controller;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import projectJM.jotItDown.apiPayload.BaseResponse;
import projectJM.jotItDown.dto.response.TodoResponseDTO;

@RestController
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class TodoRestController {

//    // Todo 생성 코드
//    @PostMapping("/member/{memberId}/todoList")
//    public BaseResponse<TodoResponseDTO.CreateTodoResultDTO> CreateTodo () {
//
//    }
}
