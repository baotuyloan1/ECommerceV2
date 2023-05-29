package com.bnd.ecommerce.exception.handler;

import com.bnd.ecommerce.exception.ErrorItem;
import com.bnd.ecommerce.exception.ErrorResponse;
import java.util.Date;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

// ưu tiên sử lý các handleMethod ở class extends ResponseEntityExceptionHandler truo, không có thì
// nó nhảy vào class kia
//@RestControllerAdvice
//public class CustomerExceptionHandler extends ResponseEntityExceptionHandler {
//
//  @Override
//  protected ResponseEntity<Object> handleMethodArgumentNotValid(
//      MethodArgumentNotValidException ex,
//      HttpHeaders headers,
//      HttpStatus status,
//      WebRequest request) {
//    ErrorResponse errorResponse = new ErrorResponse();
//    errorResponse.setTimestamp(new Date());
//    errorResponse.setMessageTemplate(ex.getMessage());
//    errorResponse.setHttpStatus(HttpStatus.CONFLICT);
//    for (FieldError x : ex.getBindingResult().getFieldErrors()) {
//
//      ErrorItem errorItem = new ErrorItem();
//      errorItem.setMessage(x.getDefaultMessage());
//      errorItem.setCode(x.getCode());
//      errorResponse.addError(errorItem);
//    }
//    return new ResponseEntity<>(errorResponse, headers, status);
//  }
//}
