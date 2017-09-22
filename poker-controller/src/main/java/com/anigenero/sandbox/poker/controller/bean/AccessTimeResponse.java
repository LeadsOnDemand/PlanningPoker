package com.anigenero.sandbox.poker.controller.bean;

import java.time.LocalDateTime;

public class AccessTimeResponse<T> extends DefaultResponse<T> {

    private LocalDateTime accessed;

    public AccessTimeResponse() {
        this.accessed = LocalDateTime.now();
    }

    public AccessTimeResponse(T data) {
        super(data);
        this.accessed = LocalDateTime.now();
    }

    public LocalDateTime getAccessed() {
        return accessed;
    }

}
