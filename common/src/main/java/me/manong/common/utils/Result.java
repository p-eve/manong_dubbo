package me.manong.common.utils;

import java.io.Serializable;

public class Result<T> implements Serializable {
    private String code;
    private String msg;
    private T data;

    public static <T> Result<T> success() {
        return new Result("200", "ok");
    }

    public static <T> Result<T> success(T data) {
        return new Result(data);
    }

    public static <T> Result<T> build(String code, String msg) {
        return new Result(code, msg);
    }

    public static <T> Result<T> build(String code, String msg, T data) {
        return new Result(code, msg, data);
    }

    public static <T> Result<T> error(String codeMsg) {
        return new Result("500",codeMsg);
    }

    public static <T> Result<T> error() {
        return new Result("500","Internal server error");
    }

    public static <T> Result<T> error401() {
        return new Result("401","Unauthorized");
    }

    public static <T> Result<T> error403() {
        return new Result("403","Forbidden");
    }

    public static <T> Result<T> error(T data) {
        return new Result("500", "Internal server error", data);
    }

    public Result(){

    }

    public Result(T data) {
        this.code = "200";
        this.msg = "ok";
        this.data = data;
    }

    public Result(String msg) {
        this.code = "200";
        this.data = null;
        this.msg = msg;
    }


    public Result(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Result(String code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public String getCode() {
        return this.code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return this.msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return this.data;
    }

    public void setData(T data) {
        this.data = data;
    }
}

