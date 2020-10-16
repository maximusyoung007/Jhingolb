package com.maximus.jhingolbback.result;

public class Result<T> {

    private String type;
    private String msg;
    private T data;

    //成功
    public static <T>Result<T> success(T data,String message) {
        return new Result<T>(data,message,ResultType.SUCCESS.getType());
    }

    //错误
    public static <T>Result<T> error(T data,String message) {
        return new Result<T>(data,message,ResultType.ERROR.getType());
    }

    private Result(T data,String message,String type) {
        this.data = data;
        this.msg = message;
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMsg() {
        return msg;
    }
    public void setMsg(String msg) {
        this.msg = msg;
    }
    public T getData() {
        return data;
    }
    public void setData(T data) {
        this.data = data;
    }
}
