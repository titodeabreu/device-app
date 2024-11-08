package com.device.controller.v1.exception;

import lombok.Data;

@Data
public class GlobalErrorResponse {
    private int status;
    private String message;
    private long timestamp;

    public GlobalErrorResponse(int status, String message) {
        this.status = status;
        this.message = message;
        this.timestamp = System.currentTimeMillis();
    }
}