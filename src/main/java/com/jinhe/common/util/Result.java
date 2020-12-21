package com.jinhe.common.util;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Administrator
 */
public class Result<T extends Object> {

    private static final String CODE = "code";
    private static final Object MSG = "msg";

    private Integer code = 200;
    private String msg = "操作成功";
    private T data = null;

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

    public Result setData(T data) {
        this.data = data;
        return this;
    }
}
