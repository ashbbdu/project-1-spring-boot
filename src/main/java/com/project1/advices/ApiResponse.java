package com.project1.advices;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ApiResponse<T> {
    private LocalDateTime timeStamp;
    private T data;
    private Boolean success;
    private ApiError error;

    public ApiResponse () {
        this.timeStamp = LocalDateTime.now();

    }


    public ApiResponse (T data) {
        this();
        this.data = data;
        this.success = true;
    }

    public ApiResponse (ApiError error) {
        this();
        this.error = error;
        this.success = false;
    }

}
