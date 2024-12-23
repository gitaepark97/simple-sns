package hugo.simplesns.support.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class ApplicationException extends RuntimeException {

    private final HttpStatus status;

    ApplicationException(HttpStatus status, String message) {
        super(message);
        this.status = status;
    }

}
