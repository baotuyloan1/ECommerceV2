package com.bnd.ecommerce.exception;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.http.HttpStatus;

public class ErrorResponse {

  private HttpStatus httpStatus;
  private String messageTemplate;

  @JsonProperty("errors")
  private List<ErrorItem> errorItemList;

  @JsonProperty("time error:")
  private Date timestamp;


  public Date getTimestamp() {
    return timestamp;
  }

  public void setTimestamp(Date timestamp) {
    this.timestamp = timestamp;
  }

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
