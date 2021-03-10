package com.jinhe.config;

import com.jinhe.common.config.ResultEnum;

/**
 * @author rls
 */
public class BusinessResultEnum extends ResultEnum {

    public BusinessResultEnum(Integer code, String msg) {
        super(code, msg);
    }

    public static final ResultEnum CUSTOM_DEMO = new ResultEnum(10001, "业务返回码示例");


}
