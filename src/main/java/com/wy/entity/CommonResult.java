package com.wy.entity;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * Created by yuanwang on 17/8/15.
 */
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class CommonResult<T> {

    private Integer code;

    private String msg;

    private T data;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
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
