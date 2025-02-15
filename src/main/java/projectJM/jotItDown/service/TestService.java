package projectJM.jotItDown.service;

import org.springframework.stereotype.Service;
import projectJM.jotItDown.apiPayload.code.status.ErrorStatus;
import projectJM.jotItDown.apiPayload.exception.handler.TestHandler;

@Service
public class TestService {
    public void failedTest() {
        if (true) {
            throw new TestHandler(ErrorStatus._BAD_REQUEST);
        }
    }
}
