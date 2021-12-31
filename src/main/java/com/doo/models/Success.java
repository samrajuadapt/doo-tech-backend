package com.doo.models;

/**
 * Created by Sam Raju on 31/12/2021
 */
public class Success {
    private String message;

    public Success() {
    }

    public Success(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
