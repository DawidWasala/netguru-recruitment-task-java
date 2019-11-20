package pl.waskicompany.netguru.netgururecruitmenttask.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FORBIDDEN)
public class ResourceAlreadyExistsException extends RuntimeException {
    public ResourceAlreadyExistsException() {
        super();
    }

    public ResourceAlreadyExistsException(String message) {
        super(message);
    }

    public ResourceAlreadyExistsException(String message, Throwable cause) {
        super(message, cause);
    }


}
