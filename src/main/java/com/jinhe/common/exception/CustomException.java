package com.jinhe.common.exception;

import com.jinhe.common.config.ResultEnum;

/**
 * 自定义异常
 *
 * @author rls
 */
public class CustomException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    private String msg;
    private int code = 500;
    private ResultEnum ResultEnum;

    public CustomException(String msg) {
        super(msg);
        this.msg = msg;
    }

    public CustomException(String msg, Throwable e) {
        super(msg, e);
        this.msg = msg;
    }

    public CustomException(String msg, int code) {
        super(msg);
        this.msg = msg;
        this.code = code;
    }

    public CustomException(String msg, int code, Throwable e) {
        super(msg, e);
        this.msg = msg;
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public <T extends ResultEnum> CustomException(T systemResultEnum) {
        super((String) systemResultEnum.getMsg());
        this.ResultEnum = systemResultEnum;
    }
}
