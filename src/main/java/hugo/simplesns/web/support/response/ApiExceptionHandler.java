package hugo.simplesns.web.support.response;

import hugo.simplesns.core.exception.ApplicationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
class ApiExceptionHandler {

    @ExceptionHandler(ApplicationException.class)
    ApiResponse<?> handleApplicationException(ApplicationException e) {
        return ApiResponse.of(e.getStatus(), e.getMessage());
    }

    @ExceptionHandler(Exception.class)
    ApiResponse<?> handleException(Exception e) {
        log.error(e.getMessage(), e);
        return ApiResponse.of(HttpStatus.INTERNAL_SERVER_ERROR, "서버 내부 오류입니다.");
    }

}
