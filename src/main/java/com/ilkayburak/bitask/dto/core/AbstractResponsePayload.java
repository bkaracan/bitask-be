package com.ilkayburak.bitask.dto.core;

import com.ilkayburak.bitask.enumarations.core.MessageEnum;
import com.ilkayburak.bitask.enumarations.core.ResponseEnum;

public class AbstractResponsePayload {

    public AbstractResponsePayload() {}

    public ResponsePayload setResponse() {
        return getDefaultSuccessResponse();
    }

    public <T> ResponsePayload<T> setResponse(T data) {
        return setResponseData(ResponseEnum.OK, data);
    }

    public ResponsePayload setResponse(ResponseEnum responseEnum, String message) {
        return setResponseData(responseEnum, message);
    }

    public ResponsePayload setResponse(ResponseEnum responseEnum, MessageEnum message) {
        return setResponseData(responseEnum, message.getMessage());
    }

    public <T> ResponsePayload<T> setResponse(
            ResponseEnum responseEnum, MessageEnum message, T data) {
        return setResponse(responseEnum, message.getMessage(), data, false);
    }

    public ResponsePayload setResponse(
            ResponseEnum responseEnum, String message, Boolean showNotification) {
        return setResponse(responseEnum, message, null, showNotification);
    }

    private <T> ResponsePayload<T> setResponseData(ResponseEnum responseEnum, T data) {
        return new ResponsePayload<T>(responseEnum, data);
    }

    private ResponsePayload getDefaultSuccessResponse() {
        return new ResponsePayload(ResponseEnum.OK);
    }

    public <T> ResponsePayload<T> setResponse(
            ResponseEnum responseEnum, String message, T data, Boolean showNotification) {
        return new ResponsePayload<T>(responseEnum, message, data, showNotification);
    }

    public <T> ResponsePayload<T> setResponse(
            ResponseEnum responseEnum, MessageEnum message, T data, Boolean showNotification) {
        return setResponse(responseEnum, message.getMessage(), data, showNotification);
    }
}
