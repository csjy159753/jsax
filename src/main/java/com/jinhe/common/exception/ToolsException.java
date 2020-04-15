package com.jinhe.common.exception;

public class ToolsException extends RuntimeException  {
    private static final long serialVersionUID = 1L;

    private String msg;
    private Integer code = 500;

    public ToolsException(String msg) {
        super(msg);
        this.msg = msg;
    }

    public ToolsException(String msg, Throwable e) {
        super(msg, e);
        this.msg = msg;
    }

    public ToolsException(Integer code,String msg ) {
        super(msg);
        this.msg = msg;
        this.code = code;
    }

    public ToolsException(Integer code,String msg,  Throwable e) {
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
}
