package com.ilkayburak.bitask.dto.core;

import com.ilkayburak.bitask.enumarations.core.MessageEnum;
import com.ilkayburak.bitask.enumarations.core.ResponseEnum;

public class ResponsePayload<T> {
  private Integer code;
  private String message;
  private Boolean success;
  private ResponseEnum responseEnum;
  private Boolean showNotification = false;
  private T data;

  public ResponsePayload() {}

  public ResponsePayload(ResponseEnum responseEnum) {
    super();
    this.responseEnum = responseEnum;
    this.code = responseEnum.getHttpStatusCode();
    this.message = responseEnum.getDescription();
    this.success = responseEnum.getIsSuccess();
    this.showNotification = responseEnum.getDefaultNotification();
  }

  public ResponsePayload(ResponseEnum responseEnum, String message) {
    this(responseEnum);
    if (responseEnum.equals(ResponseEnum.BADREQUEST)
        || responseEnum.equals(ResponseEnum.FORBIDDEN)
        || responseEnum.equals(ResponseEnum.NOTFOUND)
        || responseEnum.equals(ResponseEnum.UNAUTHORIZED)
        || responseEnum.equals(ResponseEnum.ERROR)) {
      this.success = false;
    }
    this.message = message;
  }

  public ResponsePayload(ResponseEnum responseEnum, String message, T data) {
    this(responseEnum);
    this.message = message;
    this.data = data;
  }

  public ResponsePayload(
      ResponseEnum responseEnum, String message, T data, Boolean showNotification) {
    this(responseEnum);
    this.message = message;
    this.data = data;
    this.showNotification = showNotification;
  }

  public ResponsePayload(Integer code, String message, Boolean success) {
    this(code, success);
    this.message = message;
  }

  public ResponsePayload(Integer code, Boolean success) {
    this(success);
    this.code = code;
  }

  public ResponsePayload(Boolean success) {
    super();
    this.success = success;
  }

  public ResponsePayload(ResponseEnum responseEnum, T data) {
    this(responseEnum);
    this.data = data;
  }

  public ResponsePayload(ResponseEnum responseEnum, T data, Boolean showNotification) {
    this(responseEnum);
    this.data = data;
    this.showNotification = showNotification;
  }

  public ResponsePayload(ResponseEnum responseEnum, String message, Boolean success, T data) {
    this(responseEnum);
    this.message = message;
    this.success = success;
    this.data = data;
  }

  public ResponsePayload(
      ResponseEnum responseEnum,
      String message,
      Boolean success,
      T data,
      Boolean showNotification) {
    this(responseEnum);
    this.message = message;
    this.success = success;
    this.data = data;
    this.showNotification = showNotification;
  }

  public ResponsePayload(MessageEnum messageEnum, Boolean success) {
    this.code = messageEnum.getKod();
    this.message = messageEnum.getMessage();
    this.success = success;
  }

  public Integer getCode() {
    return code;
  }

  public void setCode(Integer code) {
    this.code = code;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public Boolean getSuccess() {
    return success;
  }

  public void setSuccess(Boolean success) {
    this.success = success;
  }

  public Boolean getShowNotification() {
    return showNotification;
  }

  public void setShowNotification(Boolean showNotification) {
    this.showNotification = showNotification;
  }

  public ResponseEnum getResponseEnum() {
    return responseEnum;
  }

  public void setResponseEnum(ResponseEnum responseEnum) {
    this.responseEnum = responseEnum;
    this.code = responseEnum.getHttpStatusCode();
    this.message = responseEnum.getDescription();
    this.success = responseEnum.getIsSuccess();
  }

  public void setResponseEnum(ResponseEnum responseEnum, String message) {
    this.responseEnum = responseEnum;
    this.code = responseEnum.getHttpStatusCode();
    this.message = message;
    this.success = responseEnum.getIsSuccess();
  }

  public T getData() {
    return data;
  }

  public void setData(T data) {
    this.data = data;
  }
}
