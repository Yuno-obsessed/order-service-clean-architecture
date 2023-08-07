package sanity.nil.onlineshop.presentation.api.middleware;

import jakarta.annotation.Priority;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import sanity.nil.onlineshop.application.product.exceptions.ProductIsDeleted;
import sanity.nil.onlineshop.application.product.exceptions.ProductNotFound;
import sanity.nil.onlineshop.application.product.exceptions.SubtypeNotFound;
import sanity.nil.onlineshop.domain.product.exceptions.UnsupportedPriceException;
import sanity.nil.onlineshop.domain.product.exceptions.UnsupportedQuantityException;
import sanity.nil.onlineshop.presentation.api.exception.response.ErrorResponse;
import sanity.nil.onlineshop.presentation.api.exception.request.RequestIdHolder;


import java.io.PrintWriter;
import java.io.StringWriter;

import static sanity.nil.onlineshop.presentation.api.exception.response.ErrorCode.*;

@RestControllerAdvice
@RequiredArgsConstructor
@Slf4j
public class GeneralExceptionHandler {

    private final RequestIdHolder requestIdHolder;

//    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
//    @ExceptionHandler(Exception.class)
//    public ErrorResponse handle(final Exception e) {
//       return new ErrorResponse(requestIdHolder.mustGet(), INTERNAL_ERROR.getReason(), e.getClass().getCanonicalName());
//    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(ProductNotFound.class)
    public ErrorResponse handle(final ProductNotFound e) {
        return new ErrorResponse(requestIdHolder.mustGet(), NOT_FOUND.getReason());
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(ProductIsDeleted.class)
    public ErrorResponse handle(final ProductIsDeleted e) {
        return new ErrorResponse(requestIdHolder.mustGet(), NOT_FOUND.getReason());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(UnsupportedQuantityException.class)
    public ErrorResponse handle(final UnsupportedQuantityException e) {
        return new ErrorResponse(requestIdHolder.mustGet(), BAD_REQUEST.getReason());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(UnsupportedPriceException.class)
    public ErrorResponse handle(final UnsupportedPriceException e) {
        return new ErrorResponse(requestIdHolder.mustGet(), BAD_REQUEST.getReason());
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(SubtypeNotFound.class)
    public ErrorResponse handle(final SubtypeNotFound e) {
        printError(e);
        return new ErrorResponse(requestIdHolder.mustGet(), BAD_REQUEST.getReason());
    }

    private static void printError(Exception ex) {
        log.error("Error occurred during execution: ErrorMessage: {}, Stack Trace: {}",
                ex.getMessage(), getStackTraceAsString(ex));
    }

    private static String getStackTraceAsString(Throwable throwable) {
        StringWriter stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stringWriter);
        throwable.printStackTrace(printWriter);
        return stringWriter.toString();
    }
}
