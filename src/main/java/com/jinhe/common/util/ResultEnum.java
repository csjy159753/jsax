package com.jinhe.common.util;

public enum  ResultEnum {
    //这里是可以自己定义的，方便与前端交互即可
    /**
     *  0-1000系统模块
     */
    UNKNOWN_ERROR(-1,"未知错误"),
    SUCCESS(200,"成功"),
    Error(500,"程序异常错误"),
    NotFind(404,"未发现"),
    ParameterError(101,"参数不正确"),
    AbnormalValue(102,"异常值"),
    PermissionDenied(103,"已废弃"),
    Obsolete(104,"没有权限；拒绝访问 非法操作，通常是进行了不被授权的操作"),
    ConfigurationError(105,"配置信息错误"),
    DataFormatError(106,"参数格式不正确"),
    IdNotFindInfo(105,"107"),
    /**
     * 角色模块
     */
    RoleNotFind(1101,"角色信息未发现"),
    RoleAssociatedUsers(1101,"角色有关联用户无法删除"),
    RoleExistSubsetUnableDel(1103,"角色存在子角色无法删除"),
    /**
     * 机构模块
     */
    OrganAssociatedUsers(1201,"机构关联用户无法删除"),
    OrganNotFind(1202,"机构未发现"),
    OrganExistSubsetUnableDel(1103,"机构存在子机构无法删除"),
    /**
     *行政区
     */
    RegionAssociatedUsers(1301,"行政区关联用户无法删除"),
    RegionNotFind(1303,"行政区未发现"),
    RegionExistSubsetUnableDel(1103,"行政区存在子行政区无法删除"),
    ;
    private Integer code;
    private String msg;

    ResultEnum(Integer code, String msg) {
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
