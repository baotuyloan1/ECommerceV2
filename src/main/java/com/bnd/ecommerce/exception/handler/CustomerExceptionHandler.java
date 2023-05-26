package com.bnd.ecommerce.exception.handler;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

//ưu tiên sử lý các handleMethod ở class extends ResponseEntityExceptionHandler truo, không có thì nó nhảy vào class kia
@ControllerAdvice
public class CustomerExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        Map<String, Object> responseBody = new LinkedHashMap<>();
        responseBody.put("timestamp", new Date());
        responseBody.put("status", status.value());

        Map<String, String> errors = new LinkedHashMap<>();
        for (FieldError x : ex.getBindingResult().getFieldErrors()) {
            String defaultMessage = x.getDefaultMessage();
            String objectName = x.getObjectName();
            String field = x.getField();
            String code = x.getCode();
            errors.put("defaultMessage", defaultMessage);
            errors.put("objectName", objectName);
            errors.put("field", field);
            errors.put("code", code);
        }
        responseBody.put("errors", errors);

        return new ResponseEntity<>(responseBody, headers,status);
    }


}
