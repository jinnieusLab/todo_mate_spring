package projectJM.jotItDown.apiPayload.exception.handler;

import projectJM.jotItDown.apiPayload.code.BaseErrorCode;
import projectJM.jotItDown.apiPayload.exception.GeneralException;

public class MemberHandler extends GeneralException {

    public MemberHandler(BaseErrorCode code) {
        super(code);
    }
}
