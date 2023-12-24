package sanity.nil.userservice.presentation.api.middleware;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.ErrorResponse;
import org.springframework.web.ErrorResponseException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import sanity.nil.userservice.application.exceptions.UserNotFoundException;

@RestControllerAdvice
@RequiredArgsConstructor
@Slf4j
public class GeneralExceptionHandler {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(UserNotFoundException.class)
    public ErrorResponse handle(final UserNotFoundException e) {
//        printError(e);
        return new ErrorResponseException(HttpStatus.NOT_FOUND, e.getCause());
    }
}

