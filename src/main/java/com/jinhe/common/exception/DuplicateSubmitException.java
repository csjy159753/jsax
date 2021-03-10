package com.jinhe.common.exception;

import com.jinhe.common.config.ResultEnum;

/**
 * 重复提交异常
 *
 * @author Administrator
 */
public class DuplicateSubmitException extends RuntimeException {

    private String msg;
    private int code = 500;
    private ResultEnum ResultEnum;

    public DuplicateSubmitException(String msg) {
        super(msg);
    }

    public DuplicateSubmitException(String msg, Throwable cause) {
        super(msg, cause);
    }

    public DuplicateSubmitException(String msg, int code) {
        super(msg);
        this.msg = msg;
        this.code = code;
    }

    public <T extends ResultEnum> DuplicateSubmitException(T systemResultEnum) {
        super((String) systemResultEnum.getMsg());
        this.ResultEnum = systemResultEnum;
    }

}