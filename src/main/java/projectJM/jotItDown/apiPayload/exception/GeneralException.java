package projectJM.jotItDown.apiPayload.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import projectJM.jotItDown.apiPayload.code.BaseErrorCode;
import projectJM.jotItDown.apiPayload.code.ErrorReasonDTO;

@Getter
@AllArgsConstructor
public class GeneralException extends RuntimeException {

    private BaseErrorCode code;

    public ErrorReasonDTO getErrorReason() {

        return this.code.getReason();

    }

    public ErrorReasonDTO getErrorReasonHttpStatus() {

        return this.code.getReasonHttpStatus();

    }
}
