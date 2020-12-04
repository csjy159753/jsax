package com.jinhe.config;

public enum ResultEnum {
    //这里是可以自己定义的，方便与前端交互即可
    /**
     * 0-1000系统模块
     */
    UNKNOWN_ERROR(-1, "程序内部出错请联系管理员！"),
    TOKRN_ERROR(10, "Token验证失败"),
    SUCCESS(200, "成功"),
    ERROR(500, "程序异常错误"),
    NOT_FOUND(404, "对象不存在"),
    PARAMETER_ERROR(101, "参数不正确"),
    ABNORMAL_VALUE(102, "异常值"),
    PERMISSION_DENIED(103, "已废弃"),
    OBSOLETE(104, "没有权限；拒绝访问 非法操作，通常是进行了不被授权的操作"),
    CONFIGURATION_ERROR(105, "配置信息错误"),
    DATA_FORMAT_ERROR(106, "参数格式不正确"),
    ID_NOT_FIND_INFO(105, "107"),
    ACCOUNT_OR_PASSWORD_ERROR(108, "用户名或密码错误"),
    DUPLICATE_SUBMIT(109, "表单重复提交"),
    NETWORK_ERROR(19, "新增失败,请检查网络是否正常"),

    /**
     * 用户模块
     */
    USER_OBSOLETE(1001, "用户被禁用"),
    USER_AUDIT(1002, "用户审核中"),

    /**
     * 角色模块
     */
    ROLE_NOT_FOUND(1101, "角色信息未发现"),
    ROLE_ASSOCIATED_USERS(1101, "角色有关联用户无法删除"),
    ROLE_EXIST_SUBSET_UNABLE_DEL(1103, "角色存在子角色无法删除"),
    ROLE_UPDATE_ERROR(1103, "角色更新失败"),
    ROLE_INSERT_PERMISSIONS(1104, "更新内容为空"),
    ROLE_TAG_REPEAT(1105, "角色表示重复"),
    ROLE_TYPE_REPEAT(1105, "角色类型重复"),
    /**
     * 机构模块
     */

    ORGAN_ASSOCIATED_USERS(1201, "机构关联用户无法删除"),
    ORGAN_NOT_FOUND(1202, "机构未发现"),
    ORGAN_EXIST_SUBSET_UNABLE_DEL(1203, "机构存在子机构无法删除"),
    ORGAN_INSERT_PERMISSIONS(1204, "机构新增权限失败"),
    ORGAN_UPDATE_ERROR(1205, "更新机构失败"),
    ORGAN_TYPE_ERROR(1206, "机构类型不正确"),
    ORGAN_TAG_REPEAT(1207, "机构标识符字符串重复"),
    /**
     * 行政区
     */
    REGION_ASSOCIATED_USERS(1301, "行政区关联用户无法删除"),
    REGION_NOT_FOUND(1302, "行政区未发现"),
    REGION_EXIST_SUBSET_UNABLE_DEL(1303, "行政区存在子行政区无法删除"),
    REGION_UPDATE_ERROR(1304, "更新行政区失败"),
    REGION_INSERT_ERROR(1305, "新增行政区失败"),
    REGION_EXIST_CODE(1306, "行政编码已存在不允许修改"),
    REGION_CODE_NOT_FOUND(1306, "行政编码已存在不允许修改"),
    /**
     * 文件上传
     */
    FILE_NOT_FOUND(1401, "未发现已上传的文件"),
    FILE_UPLOAD_ERROR(1402, "文件上传失败"),
    FILE_DELETE_ERROR(1403, "文件删除失败"),
    /**
     * 用户模块
     */
    USER_ACCOUNT_OR_PASSWORD_ISNULL(1501, "账户或密码为空"),
    INSERT_USER_ERROR(1502, "新增用户失败"),
    INSERT_USER_ROLE_ERROR(1503, "新增用户角色失败"),
    INSERT_USER_ORGAN_ERROR(1504, "新增用户机构失败"),
    RESET_PASSWORD_ERROR(1505, "重置密码失败"),
    DISABLE_OR_RESTORE_ERROR(1506, "禁用/恢复用户失败"),
    INSERT_OR_UPDATE_HEADPORTRAIT_ERROR(1507, "添加/更新头像失败"),
    USER_ORGAN_AND_ROLE_IMPERFECT(1508, "输入的机构和角色不完整"),
    USER_NAME_ALREADY_EXISTS(1509, "用户信息已存在"),
    USER_NOT_FOUND(1510, "用户不存在"),
    USER_INSERT_PERMISSION_ERROR(1511, "用户新增权限失败"),
    USER_DELETE_ERROR(1512, "删除用户失败"),
    USER_UPDATE_PASSWORD_ERROR(1513, "用户密码格式不正确"),
    USER_NAME_LENGTH_ERROR(1514, "用户名长度不够"),
    USER_NAME_CORRECT(1515, "用户名正确"),
    USER_NOT_RELEVANCY_ORGAN(1516, "用户没有关联机构"),


    /**
     * 权限模块
     */
    PERMISSION_NOT_FOUND(1601, "未查询到权限信息"),
    PERMISSION_EXIST_SUBSET_UNABLE_DEL(1602, "权限存在子机构无法删除"),

    /**
     * 资源模块
     */
    RESOURCEITEM_SELECT_NOT_FOUND(1701, "未查询到资源信息"),
    RESOURCEITEM_INSERT_ERROR(1702, "资源新增失败"),
    RESOURCEITEM_DELETE_ERROR(1702, "资源删除失败"),
    RESOURCEITEM_UPDATE_ERROR(1702, "资源更新失败"),

    /**
     * 菜单模块
     */

    RESOURCE_SAVE_UPDATE_ERROR(1801, "新增或者更新菜单失败"),
    RESOURCE_DELETE_ERROR(1802, "删除菜单失败"),
    RESOURCE_UPDATE_ERROR(1803, "更新菜单失败"),
    RESOURCE_SELECT_NOT_FOUND(1804, "未查询到菜单信息"),
    RESOURCE_EXIST_SUBSET_UNABLE_DEL(1805, "菜单存在子机构无法删除"),
    RESOURCE_PERMISSION_DENIED(1086, "查询菜单权限不足"),

    /**
     * 字典
     */
    DICTIONARY_EXIST_SUBSET_UNABLE_DEL(1901, "字典存在子属性无法删除"),
    DICTIONARY_EXIST_SAME_VALUE(1902, "字典存在相同值"),
    DICTIONARY_EXIST_SAME_NAME(1903, "字典存在相同名称"),
    /**
     * 10000以后是业务的
     */


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
