package hugo.simplesns.core.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {
    // User
    DUPLICATED_USERNAME(HttpStatus.CONFLICT, "이미 사용 중인 사용자 이름입니다."),
    NOT_FOUND_USER(HttpStatus.NOT_FOUND, "존재하지 않는 사용자입니다."),

    // Post
    NOT_FOUND_POST(HttpStatus.NOT_FOUND, "존재하지 않는 포스트입니다."),

    // Follow
    ALREADY_FOLLOWED(HttpStatus.CONFLICT, "이미 팔로우 중입니다."),
    NOT_FOUND_FOLLOW(HttpStatus.NOT_FOUND, "먼저 팔로우해주세요."),

    // Like
    ALREADY_LIKED(HttpStatus.CONFLICT, "이미 좋아요를 눌렀습니다."),
    NOT_FOUND_LIKE(HttpStatus.NOT_FOUND, "먼저 좋아요를 눌러주세요."),
    ;

    private final HttpStatus status;
    private final String message;

    public ApplicationException toException() {
        return new ApplicationException(status, message);
    }
}
