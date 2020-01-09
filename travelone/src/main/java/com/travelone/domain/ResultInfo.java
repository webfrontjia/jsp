package com.travelone.domain;

import java.io.Serializable;

public class ResultInfo<T> implements Serializable {
    private int code;
    private T data;
    private String errorMsg;

    public ResultInfo() {
    }

    public ResultInfo(int code, T data, String errorMsg) {
        this.code = code;
        this.data = data;
        this.errorMsg = errorMsg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    @Override
    public String toString() {
        return "ResultInfo{" +
                "code=" + code +
                ", data=" + data +
                ", errorMsg='" + errorMsg + '\'' +
                '}';
    }
}
