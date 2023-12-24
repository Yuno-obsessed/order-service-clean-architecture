package sanity.nil.authservice.presentation.api.middleware;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import sanity.nil.authservice.application.dto.response.ErrorDTO;
import sanity.nil.authservice.application.exceptions.RefreshTokenIsEmptyException;
import sanity.nil.authservice.application.exceptions.UserServiceException;

@RestControllerAdvice
@RequiredArgsConstructor
@Slf4j
public class GeneralExceptionHandler {

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public ErrorDTO handle(final Exception e) {
        printError(e);
        return new ErrorDTO(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
    }

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(RefreshTokenIsEmptyException.class)
    public ErrorDTO handle(final RefreshTokenIsEmptyException e) {
        return new ErrorDTO(HttpStatus.CONFLICT);
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(UserServiceException.class)
    public ErrorDTO handle(final UserServiceException e) {
        printError(e);
        return new ErrorDTO(HttpStatus.NOT_FOUND, e.getMessage());
    }

    private static void printError(Exception ex) {
        log.error("Error occurred during execution: ErrorMessage: {}, Cause: {}",
                ex.getMessage(), ex.getCause().toString());
    }
}

