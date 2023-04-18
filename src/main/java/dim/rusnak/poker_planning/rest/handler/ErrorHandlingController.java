package dim.rusnak.poker_planning.rest.handler;

import dim.rusnak.poker_planning.exception.EntityNotFoundException;
import dim.rusnak.poker_planning.exception.ForbiddenException;
import dim.rusnak.poker_planning.exception.NoContentException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ErrorHandlingController {


    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ExceptionHandler({NoContentException.class})
    public void noContent() {
    }

    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ExceptionHandler({ForbiddenException.class})
    public void forbidden() {
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler({EntityNotFoundException.class})
    public void notFound() {
    }
}
