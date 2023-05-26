package com.bnd.ecommerce.exception.handler;

import com.bnd.ecommerce.exception.ErrorItem;
import com.bnd.ecommerce.exception.ErrorResponse;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import com.bnd.ecommerce.exception.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ApiExceptionHandler {


  @SuppressWarnings("rawtypes")
  @ExceptionHandler(ConstraintViolationException.class)
  public ResponseEntity<ErrorResponse> handle(ConstraintViolationException e) {
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
  public ResponseEntity<ErrorItem> handle(ResourceNotFoundException e){
    ErrorItem errorItem = new ErrorItem();
    errorItem.setMessage(e.getMessage());
    return new ResponseEntity<>(errorItem, HttpStatus.NOT_FOUND);
  }
}
