package hugo.simplesns.web.support.response;

import org.springframework.http.HttpStatus;

record ApiResponse<T>(
    HttpStatus status,
    String message,
    T data
) {

    static <T> ApiResponse<T> of(HttpStatus status, String message, T data) {
        return new ApiResponse<>(status, message, data);
    }

    static <T> ApiResponse<T> of(HttpStatus status, String message) {
        return new ApiResponse<>(status, message, null);
    }

    static <T> ApiResponse<T> of(HttpStatus status, T data) {
        return switch (status) {
            case HttpStatus.OK, HttpStatus.CREATED -> ApiResponse.of(status, "성공", data);
            default -> ApiResponse.of(status, "실패", data);
        };
    }

}
