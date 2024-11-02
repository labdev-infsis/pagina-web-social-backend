package com.infsis.socialpagebackend.exceptions;

public class ErrorResponse {

    private String exception;
    private int code;

    public ErrorResponse(String exception) {
        this.exception = exception;
    }

    public ErrorResponse(int code, String exception){
        this.code = code;
        this.exception = exception;
    }

    public String getException() {
        return exception;
    }

    public void setException(String exception) {
        this.exception = exception;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

}
