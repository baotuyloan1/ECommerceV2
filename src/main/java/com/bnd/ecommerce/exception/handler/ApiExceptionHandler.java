package com.bnd.ecommerce.exception.handler;

import com.bnd.ecommerce.exception.ErrorItem;
import com.bnd.ecommerce.exception.ErrorResponse;
import com.bnd.ecommerce.exception.ResourceNotFoundException;

import java.sql.SQLException;
import java.util.*;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ApiExceptionHandler {

  @SuppressWarnings("rawtypes")
  @ExceptionHandler(ConstraintViolationException.class)
  public ResponseEntity<ErrorResponse> handleConstraintViolationException(
      ConstraintViolationException e) {
    ErrorResponse errors = new ErrorResponse();
    for (ConstraintViolation violation : e.getConstraintViolations()) {
      ErrorItem error = new ErrorItem();
      error.setCode(violation.getMessageTemplate());
      error.setMessage(violation.getMessage());
      errors.addError(error);
    }
    return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(ResourceNotFoundException.class)
  public ResponseEntity<ErrorItem> handleResourceNotFoundException(ResourceNotFoundException e) {
    ErrorItem errorItem = new ErrorItem();
    errorItem.setMessage(e.getMessage());
    return new ResponseEntity<>(errorItem, HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<ErrorResponse> handleMethodArgumentNotValidException(
      MethodArgumentNotValidException ex) {
    ErrorResponse errorResponse = new ErrorResponse();
    errorResponse.setTimestamp(new Date());
    errorResponse.setMessageTemplate(ex.getMessage());
    errorResponse.setHttpStatus(HttpStatus.BAD_REQUEST);
    for (FieldError x : ex.getBindingResult().getFieldErrors()) {

      ErrorItem errorItem = new ErrorItem();
      errorItem.setMessage(x.getDefaultMessage());
      errorItem.setCode(x.getCode());
      errorResponse.addError(errorItem);
    }
    return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
  }


}
