package projectJM.jotItDown.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import projectJM.jotItDown.apiPayload.BaseResponse;
import projectJM.jotItDown.service.TestService;

@RestController
@RequiredArgsConstructor
public class TestController {
    private final TestService testService;

    @GetMapping("/")
    public BaseResponse<String> test() {
        return BaseResponse.onSuccess("성공 응답 테스트 - 성공!");
    }

    @GetMapping("/failed")
    public BaseResponse<String> failedTest() {
        testService.failedTest();
        return BaseResponse.onSuccess("성공!");
    }
}
