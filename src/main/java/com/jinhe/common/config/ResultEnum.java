package com.jinhe.common.config;

public class ResultEnum extends AbstractEnum {

    public ResultEnum(Integer code, String msg) {
        super(code, msg);
    }

    public static final ResultEnum UNKNOWN_ERROR = new ResultEnum(-1, "程序内部出错请联系管理员！");
    public static final ResultEnum TOKRN_ERROR = new ResultEnum(10, "Token验证失败！");
    public static final ResultEnum SUCCESS = new ResultEnum(200, "成功！");
    public static final ResultEnum ERROR = new ResultEnum(500, "程序异常错误");
    public static final ResultEnum NOT_FOUND = new ResultEnum(404, "对象不存在");
    public static final ResultEnum PARAMETER_ERROR = new ResultEnum(101, "参数不正确");
    public static final ResultEnum ABNORMAL_VALUE = new ResultEnum(102, "异常值");
    public static final ResultEnum PERMISSION_DENIED = new ResultEnum(103, "没有权限访问");
    public static final ResultEnum OBSOLETE = new ResultEnum(104, "没有权限；拒绝访问 非法操作，通常是进行了不被授权的操作");
    public static final ResultEnum CONFIGURATION_ERROR = new ResultEnum(105, "配置信息错误");
    public static final ResultEnum DATA_FORMAT_ERROR = new ResultEnum(106, "参数格式不正确");
    public static final ResultEnum ID_NOT_FIND_INFO = new ResultEnum(105, "107");
    public static final ResultEnum ACCOUNT_OR_PASSWORD_ERROR = new ResultEnum(108, "用户名或密码错误");
    public static final ResultEnum DUPLICATE_SUBMIT = new ResultEnum(109, "表单重复提交");
    public static final ResultEnum NETWORK_ERROR = new ResultEnum(19, "新增失败,请检查网络是否正常");
    public static final ResultEnum VERIFICATION_USER = new ResultEnum(20, "未获取到用户id异常");
    public static final ResultEnum TOOLS_EXCEPTION_CONSTANT = new ResultEnum(21, "zip压缩异常");

 }
