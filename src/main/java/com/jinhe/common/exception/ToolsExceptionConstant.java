package com.jinhe.common.exception;

public enum ToolsExceptionConstant {
    //这里是可以自己定义的
    NOTEXSITERROR(404,"未找到"),


    ;
    private Integer code;
    private String msg;

    ToolsExceptionConstant(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
