package br.com.rodrigo.monteiro.api.rest;

import br.com.rodrigo.monteiro.api.exception.FinishedException;
import br.com.rodrigo.monteiro.api.exception.KalahGameNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.Map;

import static javax.servlet.RequestDispatcher.ERROR_EXCEPTION;
import static javax.servlet.RequestDispatcher.ERROR_STATUS_CODE;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.web.context.request.RequestAttributes.SCOPE_REQUEST;

/**
 * Created by monteiro on 9/14/16.
 */
@ControllerAdvice
public class RestExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(RestExceptionHandler.class);

    @ExceptionHandler(KalahGameNotFoundException.class)
    protected String handleKalahGameNotFound(Exception exception, Map<String, Object> model) {
        model.put("message", exception.getMessage());
        log.warn("[Kalah Game NOT_FOUND] Error: {}", exception.getMessage());
        return "error";
    }

    @ExceptionHandler(FinishedException.class)
    protected String handleFinished(FinishedException exception, Map<String, Object> model) {
        model.put("message", exception.getMessage());
        log.info("Kalah Game THE END: {}", exception.getMessage());
        return "error";
    }

    @ExceptionHandler(Exception.class)
    protected String globalExceptionHandler(RuntimeException e, WebRequest request, Map<String, Object> model) {
        HttpStatus httpStatus = getHttpStatus(getErrorStatus(request));
        model.put("message", getErrorMessage(request, httpStatus));
        log.warn("[Global exception] Error: {}", e);
        return "error";
    }

    private String getErrorMessage(WebRequest request, HttpStatus httpStatus) {
        Throwable exc = (Throwable) request.getAttribute(ERROR_EXCEPTION, SCOPE_REQUEST);
        return exc != null ? exc.getMessage() : httpStatus.getReasonPhrase();
    }

    private int getErrorStatus(WebRequest request) {
        Integer statusCode = (Integer) request.getAttribute(ERROR_STATUS_CODE, SCOPE_REQUEST);
        return statusCode != null ? statusCode : INTERNAL_SERVER_ERROR.value();
    }

    private HttpStatus getHttpStatus(int statusCode) {
        return HttpStatus.valueOf(statusCode);
    }
}