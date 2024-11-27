package com.example.tutorial;

public class ResponseMessage<T> {
    private int ret;

    private String message;

    private T data;

    public ResponseMessage(int ret, String message, T data) {
        this.ret = ret;
        this.message = message;
        this.data = data;
    }
}
