package com.jinhe.common.exception;

import com.jinhe.config.ResultEnum;

/**
 * 重复提交异常
 *
 * @author Administrator
 */
public class DuplicateSubmitException extends RuntimeException {

    private String msg;
    private int code = 500;
    private ResultEnum resultEnum;

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

    public DuplicateSubmitException(ResultEnum resultEnum) {
        super((String) resultEnum.getMsg());
        this.resultEnum = resultEnum;
    }

}