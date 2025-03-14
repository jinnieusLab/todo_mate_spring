package projectJM.jotItDown.apiPayload.exception.handler;

import projectJM.jotItDown.apiPayload.code.BaseErrorCode;
import projectJM.jotItDown.apiPayload.exception.GeneralException;

public class AuthHandler extends GeneralException {

    public AuthHandler(BaseErrorCode code) {
        super(code);
    }
}
