package com.ing.tech.authentication.application.controller.advice;

import com.ing.tech.authentication.application.exceptions.AuthenticationBusinessException;
import java.util.ArrayList;
import java.util.List;
import javax.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Slf4j
@ControllerAdvice
public class ValidationAdvice extends ResponseEntityExceptionHandler {

  @ExceptionHandler(ConstraintViolationException.class)
  public ResponseEntity<Object> handleConstraintViolation(ConstraintViolationException ex) {
    List<Object> errors = new ArrayList<>();
    ex.getConstraintViolations().forEach(constraintViolation -> errors
        .add(constraintViolation.getPropertyPath() + ":" + constraintViolation.getMessage()));
    log.error(errors.toString());

    return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(AuthenticationBusinessException.class)
  public ResponseEntity<Object> handleAuthenticationBusinessException(
      AuthenticationBusinessException ex) {
    String exceptionMessage = ex.getMessage();
    log.error(exceptionMessage);

    return new ResponseEntity<>(exceptionMessage, HttpStatus.BAD_REQUEST);
  }

  @Override
  protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
      HttpHeaders headers, HttpStatus status, WebRequest request) {
    List<Object> errors = new ArrayList<>();
    ex.getBindingResult().getFieldErrors().forEach(
        fieldError -> errors.add(fieldError.getField() + ":" + fieldError.getDefaultMessage()));
    log.error(errors.toString());

    return handleExceptionInternal(ex, errors.toString(), headers, status, request);
  }
}
