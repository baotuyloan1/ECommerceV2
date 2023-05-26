package com.bnd.ecommerce.exception.handler;

import com.bnd.ecommerce.exception.DeleteFailException;
import com.bnd.ecommerce.exception.ErrorDTO;
import com.bnd.ecommerce.exception.ResourceNotFoundException;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobalExceptionHandler {

  private static final Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandler.class);

  @ExceptionHandler(ResourceNotFoundException.class)
  @ResponseStatus(HttpStatus.NOT_FOUND)
  @ResponseBody
  public ErrorDTO handleCategoryNotFound(HttpServletRequest request, Exception exception) {
    ErrorDTO errorDTO = new ErrorDTO();
    errorDTO.setStatus(HttpStatus.NOT_FOUND.value());
    errorDTO.addError(exception.getMessage());
    errorDTO.setPath(
        request.getScheme() // Lấy scheme (HTTP hoặc HTTPS)
            + request.getServerName() // Lấy domain name
            + request.getServerPort() // lấy port(int) nếu có
            + request.getContextPath() // lấy tên của web application
            + request.getServletPath() // Lấy servlet path (nếu có)
        );
    LOGGER.error(exception.getMessage(), exception);
    return errorDTO;
  }

  //    ConstraintViolationException lỗi khi valid path variable không thành công
  @ExceptionHandler(ConstraintViolationException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ResponseBody
  public ErrorDTO handleConstraintViolationException(
      HttpServletRequest request, Exception exception) {
    ErrorDTO errorDTO = new ErrorDTO();

    errorDTO.setTimestamp(new Date());
    errorDTO.setStatus(HttpStatus.BAD_REQUEST.value());
    errorDTO.addError(exception.getMessage());
    errorDTO.setPath(request.getRequestURL().toString());

    LOGGER.error(exception.getMessage(), exception);
    return errorDTO;
  }

  @ExceptionHandler(DeleteFailException.class)
  @ResponseStatus(HttpStatus.CONFLICT)
  @ResponseBody
  public ErrorDTO handleDeleteFailException(HttpServletRequest request, Exception exception) {
    ErrorDTO errorDTO = new ErrorDTO();

    errorDTO.setTimestamp(new Date());
    errorDTO.setStatus(HttpStatus.CONFLICT.value());
    errorDTO.addError(exception.getMessage());
    errorDTO.setPath(
        request.getScheme()+"://" // Lấy scheme (HTTP hoặc HTTPS)
            + request.getServerName() // Lấy domain name
            + request.getServerPort() // lấy port(int) nếu có
            + request.getContextPath() // lấy tên của web application
            + request.getServletPath() // Lấy servlet path (nếu có)
        );
    return errorDTO;
  }
}
