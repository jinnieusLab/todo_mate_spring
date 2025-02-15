package projectJM.jotItDown.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import projectJM.jotItDown.apiPayload.BaseResponse;

@RestController
public class TestController {

    @GetMapping("/")
    public BaseResponse<String> test() {
        return BaseResponse.onSuccess("성공 응답 테스트 - 성공!");
    }
}
