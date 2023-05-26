package com.bnd.ecommerce.exception;

import java.util.ArrayList;
import java.util.List;
import org.springframework.http.HttpStatus;

public class ErrorResponse {

  private HttpStatus httpStatus;
  private String messageTemplate;
  private List<ErrorItem> errorItemList;

  public void setCode(String messageTemplate) {
    this.messageTemplate = messageTemplate;
  }

  public HttpStatus getHttpStatus() {
    return httpStatus;
  }

  public void setHttpStatus(HttpStatus httpStatus) {
    this.httpStatus = httpStatus;
  }

  public String getMessageTemplate() {
    return messageTemplate;
  }

  public void setMessageTemplate(String messageTemplate) {
    this.messageTemplate = messageTemplate;
  }

  public List<ErrorItem> getErrorItemList() {
    return errorItemList;
  }

  public void setErrorItemList(List<ErrorItem> errorItemList) {
    this.errorItemList = errorItemList;
  }

  public void addError(ErrorItem error) {
    if (errorItemList == null) errorItemList = new ArrayList<>();
    errorItemList.add(error);
  }
}
