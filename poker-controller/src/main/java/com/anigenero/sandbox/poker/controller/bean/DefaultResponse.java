package com.anigenero.sandbox.poker.controller.bean;

import java.io.Serializable;
import java.util.List;

public class DefaultResponse<T> implements Serializable {

    private List<String> warnings;
    private T data;

    public DefaultResponse() {
        // default constructor for using #setData()
    }

    public DefaultResponse(T data) {
        this.data = data;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public List<String> getWarnings() {
        return warnings;
    }

    public void setWarnings(List<String> warnings) {
        this.warnings = warnings;
    }

}
