package sanity.nil.onlineshop.presentation.api.middleware;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import sanity.nil.onlineshop.application.product.exceptions.ProductNotFound;
import sanity.nil.onlineshop.domain.product.exceptions.UnsupportedPriceException;
import sanity.nil.onlineshop.domain.product.exceptions.UnsupportedQuantityException;
import sanity.nil.onlineshop.presentation.exception.response.ErrorResponse;
import sanity.nil.onlineshop.presentation.exception.request.RequestIdHolder;

import java.time.LocalDateTime;

import static sanity.nil.onlineshop.presentation.exception.response.ErrorCode.*;

@RestControllerAdvice
@RequiredArgsConstructor
@Slf4j
public class GeneralExceptionHandler {

    private final RequestIdHolder requestIdHolder;

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public ErrorResponse handle(final Exception e) {
       printError(e);
       return new ErrorResponse(requestIdHolder.mustGet(), INTERNAL_ERROR.getReason(), e.getClass().getCanonicalName());
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(ProductNotFound.class)
    public ErrorResponse handle(final ProductNotFound e) {
        printError(e);
        return new ErrorResponse(requestIdHolder.mustGet(), NOT_FOUND.getReason());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(UnsupportedQuantityException.class)
    public ErrorResponse handle(final UnsupportedQuantityException e) {
        printError(e);
        return new ErrorResponse(requestIdHolder.mustGet(), BAD_REQUEST.getReason());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(UnsupportedPriceException.class)
    public ErrorResponse handle(final UnsupportedPriceException e) {
        printError(e);
        return new ErrorResponse(requestIdHolder.mustGet(), BAD_REQUEST.getReason());
    }

    private void printError(Exception exception) {
         log.error("Exception: {}", exception.getMessage());
         log.error("Cause: ", exception.getCause());
         log.error("Time: {}", LocalDateTime.now());
    }
}
