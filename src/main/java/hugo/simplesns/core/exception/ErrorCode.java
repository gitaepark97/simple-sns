package hugo.simplesns.core.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {
    DUPLICATED_USERNAME(HttpStatus.CONFLICT, "이미 사용 중인 사용자 이름입니다."),
    NOT_FOUND_USER(HttpStatus.NOT_FOUND, "존재하지 않는 사용자입니다.");

    private final HttpStatus status;
    private final String message;

    public ApplicationException toException() {
        return new ApplicationException(status, message);
    }
}
