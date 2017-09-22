package com.anigenero.sandbox.poker.controller.bean;

import com.anigenero.sandbox.poker.common.exception.ErrorCode;

import java.util.ArrayList;
import java.util.List;

public class ErrorResponse extends DefaultResponse<Object> {

    private final String error;
    private final String errorMessage;

    private List<String> messages;

    public ErrorResponse(ErrorCode errorCode) {

        super(null);

        this.error = errorCode.getErrorCode();
        this.errorMessage = errorCode.getMessage();

    }

    public String getError() {
        return error;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void addMessage(String message) {

        if (this.messages == null) {
            messages = new ArrayList<>();
        }

        messages.add(message);

    }

    public List<String> getMessages() {
        return messages;
    }

}
