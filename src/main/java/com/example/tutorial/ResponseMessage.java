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

    public static <T> ResponseMessage<T> success(T data, String message) {
        return new ResponseMessage<T>(200, message, data);
    }

    public static <T> ResponseMessage<T> error(int ret, String message) {
        return new ResponseMessage<T>(ret, message, null);
    }

    public int getRet() {
        return ret;
    }

    public void setRet(int ret) {
        this.ret = ret;
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
