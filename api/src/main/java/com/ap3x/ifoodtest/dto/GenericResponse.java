package com.ap3x.ifoodtest.dto;

import java.time.LocalDateTime;

public class GenericResponse {

    private String status;
    private LocalDateTime timestamp;
    private Object data;

    public GenericResponse(String status, Object data) {
        this.status = status;
        this.data = data;
        this.timestamp = LocalDateTime.now();
    }

    public GenericResponse(Object data) {
        this.timestamp = LocalDateTime.now();
        this.status = "success";
        this.data = data;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
