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

    /**
     * 申请code码范围
     * GADL 20001-200100
     */
    public static final ResultEnum GADL_UPLOAD_LOSE_FILE = new ResultEnum(20001, "上传文件不全同事需要shp,shx");
}
