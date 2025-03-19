package projectJM.jotItDown.apiPayload.code.status;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import projectJM.jotItDown.apiPayload.code.BaseErrorCode;
import projectJM.jotItDown.apiPayload.code.ErrorReasonDTO;

@Getter
@AllArgsConstructor
public enum ErrorStatus implements BaseErrorCode {
    _INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "COMMON500", "서버 에러, 관리자에게 문의 바랍니다."),
    _BAD_REQUEST(HttpStatus.BAD_REQUEST, "COMMON400", "잘못된 요청입니다."),
    _UNAUTHORIZED(HttpStatus.UNAUTHORIZED, "COMMON401", "인증이 필요합니다"),
    _FORBIDDEN(HttpStatus.FORBIDDEN, "COMMON403", "금지된 요청입니다"),

    // Member
    _NOT_FOUND_MEMBER(HttpStatus.NOT_FOUND, "MEMBER400", "해당 멤버를 찾을 수 없습니다."),

    // Auth
    _AUTH_EXPIRE_TOKEN(HttpStatus.UNAUTHORIZED, "AUTH401_1", "토큰이 만료되었습니다."),
    _AUTH_INVALID_TOKEN(HttpStatus.UNAUTHORIZED, "AUTH401_2", "토큰이 유효하지 않습니다");

    private final HttpStatus httpStatus;
    private final String code;
    private final String message;

    @Override
    public ErrorReasonDTO getReason() {
        return ErrorReasonDTO.builder()
                .isSuccess(false)
                .code(code)
                .message(message)
                .build();
    }

    @Override
    public ErrorReasonDTO getReasonHttpStatus() {
        return ErrorReasonDTO.builder()
                .isSuccess(false)
                .httpStatus(httpStatus)
                .code(code)
                .message(message)
                .build();
    }
}
