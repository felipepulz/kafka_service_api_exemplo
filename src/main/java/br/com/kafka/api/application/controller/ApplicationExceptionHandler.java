package br.com.kafka.api.application.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSourceResolvable;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.core.Ordered.HIGHEST_PRECEDENCE;

@Slf4j
@ControllerAdvice
@Order(HIGHEST_PRECEDENCE)
public class ApplicationExceptionHandler extends ResponseEntityExceptionHandler {


    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        log.error("Error on request process ", ex);
        return new ResponseEntity<>(Collections.singletonMap("errorMessages", collectErrors(ex.getBindingResult().getAllErrors())), HttpStatus.BAD_REQUEST);
    }

    @Override
    protected ResponseEntity<Object> handleBindException(BindException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        log.error("Error on request process ", ex);
        return new ResponseEntity<>(Collections.singletonMap("errorMessages", collectErrors(ex.getAllErrors())), HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(ConstraintViolationException.class)
    protected ResponseEntity<Object> handleBadRequest(ConstraintViolationException ex) {
        log.error("Error on request process ", ex);
        return new ResponseEntity<>(Collections.singletonMap("errorMessages", collectErrors(ex)), HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handlerInternalServerError(Exception e, WebRequest webRequest) {
        log.error("Error on process the request", e);
        try {
            return handleException(e, webRequest);
        } catch (Exception ex) {
            log.warn("Unmapped exception {}", ex.getClass(), ex);
            return new ResponseEntity<>(Collections.singletonMap("errorMessages", List.of("server.error")), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private List<String> collectErrors(List<? extends MessageSourceResolvable> messageErrors) {
        return messageErrors.stream()
                .map(MessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.toList());
    }

    private List<String> collectErrors(ConstraintViolationException e) {
        return e.getConstraintViolations().stream()
                .map(ConstraintViolation::getMessage)
                .collect(Collectors.toList());
    }


}
