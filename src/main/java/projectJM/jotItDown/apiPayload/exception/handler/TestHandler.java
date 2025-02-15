package projectJM.jotItDown.apiPayload.exception.handler;

import projectJM.jotItDown.apiPayload.code.BaseErrorCode;
import projectJM.jotItDown.apiPayload.exception.GeneralException;

public class TestHandler extends GeneralException {

    public TestHandler(BaseErrorCode code) {
        super(code);
    }
}
