package com.doo.models;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * Created by Sam Raju on 31/12/2021
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Success<T> {
    private String message;
    private T data;

    public Success() {
    }

    public Success(String message) {
        this.message = message;
    }

    public Success(String message, T data) {
        this.message = message;
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
