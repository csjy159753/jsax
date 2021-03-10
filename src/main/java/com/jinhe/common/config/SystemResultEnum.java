package com.jinhe.common.config;

import com.jinhe.common.config.AbstractEnum;
 import com.jinhe.common.config.ResultEnum;

public class SystemResultEnum extends ResultEnum {

    public SystemResultEnum(Integer code, String msg) {
        super(code, msg);
    }
    public static final ResultEnum DUPLICATE_KEY = new ResultEnum(-1, "key值重复！");
    public static final ResultEnum UNKNOWN_ERROR = new ResultEnum(-1, "程序内部出错请联系管理员！");
    /**
     * 用户模块
     */
    public static final ResultEnum USER_OBSOLETE = new ResultEnum(1001, "用户被禁用");
    public static final ResultEnum USER_AUDIT = new ResultEnum(1002, "用户审核中");

    /**
     * 角色模块
     */
    public static final ResultEnum ROLE_NOT_FOUND = new ResultEnum(1101, "角色信息未发现");
    public static final ResultEnum ROLE_ASSOCIATED_USERS = new ResultEnum(1101, "角色有关联用户无法删除");
    public static final ResultEnum ROLE_EXIST_SUBSET_UNABLE_DEL = new ResultEnum(1103, "角色存在子角色无法删除");
    public static final ResultEnum ROLE_INSERT_PERMISSIONS = new ResultEnum(1104, "更新内容为空");
    public static final ResultEnum ROLE_TAG_REPEAT = new ResultEnum(1105, "角色表示重复");
    public static final ResultEnum ROLE_TYPE_REPEAT = new ResultEnum(1106, "角色类型重复");
    public static final ResultEnum ROLE_UPDATE_ERROR =new ResultEnum(1107, "角色更新失败");
    /**
     * 机构模块
     */

    public static final ResultEnum ORGAN_ASSOCIATED_USERS = new ResultEnum(1201, "机构关联用户无法删除");
    public static final ResultEnum ORGAN_NOT_FOUND = new ResultEnum(1202, "机构未发现");
    public static final ResultEnum ORGAN_EXIST_SUBSET_UNABLE_DEL = new ResultEnum(1203, "机构存在子机构无法删除");
    public static final ResultEnum ORGAN_INSERT_PERMISSIONS = new ResultEnum(1204, "机构新增权限失败");
    public static final ResultEnum ORGAN_UPDATE_ERROR = new ResultEnum(1205, "更新机构失败");
    public static final ResultEnum ORGAN_TYPE_ERROR = new ResultEnum(1206, "机构类型不正确");
    public static final ResultEnum ORGAN_TAG_REPEAT = new ResultEnum(1207, "机构标识符字符串重复");
    /**
     * 行政区
     */
    public static final ResultEnum REGION_ASSOCIATED_USERS = new ResultEnum(1301, "行政区关联用户无法删除");
    public static final ResultEnum REGION_NOT_FOUND = new ResultEnum(1302, "行政区未发现");
    public static final ResultEnum REGION_EXIST_SUBSET_UNABLE_DEL = new ResultEnum(1303, "行政区存在子行政区无法删除");
    public static final ResultEnum REGION_UPDATE_ERROR = new ResultEnum(1304, "更新行政区失败");
    public static final ResultEnum REGION_INSERT_ERROR = new ResultEnum(1305, "新增行政区失败");
    public static final ResultEnum REGION_EXIST_CODE = new ResultEnum(1306, "行政编码已存在不允许修改");
    public static final ResultEnum REGION_CODE_NOT_FOUND = new ResultEnum(1306, "行政编码已存在不允许修改");
    /**
     * 文件上传
     */
    public static final ResultEnum FILE_NOT_FOUND = new ResultEnum(1401, "未发现已上传的文件");
    public static final ResultEnum FILE_UPLOAD_ERROR = new ResultEnum(1402, "文件上传失败");
    public static final ResultEnum FILE_DELETE_ERROR = new ResultEnum(1403, "文件删除失败");
    /**
     * 用户模块
     */
    public static final ResultEnum USER_ACCOUNT_OR_PASSWORD_ISNULL = new ResultEnum(1501, "账户或密码为空");
    public static final ResultEnum INSERT_USER_ERROR = new ResultEnum(1502, "新增用户失败");
    public static final ResultEnum INSERT_USER_ROLE_ERROR = new ResultEnum(1503, "新增用户角色失败");
    public static final ResultEnum INSERT_USER_ORGAN_ERROR = new ResultEnum(1504, "新增用户机构失败");
    public static final ResultEnum RESET_PASSWORD_ERROR = new ResultEnum(1505, "重置密码失败");
    public static final ResultEnum DISABLE_OR_RESTORE_ERROR = new ResultEnum(1506, "禁用/恢复用户失败");
    public static final ResultEnum INSERT_OR_UPDATE_HEADPORTRAIT_ERROR = new ResultEnum(1507, "添加/更新头像失败");
    public static final ResultEnum USER_ORGAN_AND_ROLE_IMPERFECT = new ResultEnum(1508, "输入的机构和角色不完整");
    public static final ResultEnum USER_NAME_ALREADY_EXISTS = new ResultEnum(1509, "用户信息已存在");
    public static final ResultEnum USER_NOT_FOUND = new ResultEnum(1510, "用户不存在");
    public static final ResultEnum USER_INSERT_PERMISSION_ERROR = new ResultEnum(1511, "用户新增权限失败");
    public static final ResultEnum USER_DELETE_ERROR = new ResultEnum(1512, "删除用户失败");
    public static final ResultEnum USER_UPDATE_PASSWORD_ERROR = new ResultEnum(1513, "用户密码格式不正确");
    public static final ResultEnum USER_NAME_LENGTH_ERROR = new ResultEnum(1514, "用户名长度不够");
    public static final ResultEnum USER_NAME_CORRECT = new ResultEnum(1515, "用户名正确");
    public static final ResultEnum USER_NOT_RELEVANCY_ORGAN = new ResultEnum(1516, "用户没有关联机构");


    /**
     * 权限模块
     */
    public static final ResultEnum PERMISSION_NOT_FOUND = new ResultEnum(1601, "未查询到权限信息");
    public static final ResultEnum PERMISSION_EXIST_SUBSET_UNABLE_DEL = new ResultEnum(1602, "权限存在子机构无法删除");

    /**
     * 资源模块
     */
    public static final ResultEnum RESOURCEITEM_SELECT_NOT_FOUND = new ResultEnum(1701, "未查询到资源信息");
    public static final ResultEnum RESOURCEITEM_INSERT_ERROR = new ResultEnum(1702, "资源新增失败");
    public static final ResultEnum RESOURCEITEM_DELETE_ERROR = new ResultEnum(1702, "资源删除失败");
    public static final ResultEnum RESOURCEITEM_UPDATE_ERROR = new ResultEnum(1702, "资源更新失败");

    /**
     * 菜单模块
     */

    public static final ResultEnum RESOURCE_SAVE_UPDATE_ERROR = new ResultEnum(1801, "新增或者更新菜单失败");
    public static final ResultEnum RESOURCE_DELETE_ERROR = new ResultEnum(1802, "删除菜单失败");
    public static final ResultEnum RESOURCE_UPDATE_ERROR = new ResultEnum(1803, "更新菜单失败");
    public static final ResultEnum RESOURCE_SELECT_NOT_FOUND = new ResultEnum(1804, "未查询到菜单信息");
    public static final ResultEnum RESOURCE_EXIST_SUBSET_UNABLE_DEL = new ResultEnum(1805, "菜单存在子机构无法删除");
    public static final ResultEnum RESOURCE_PERMISSION_DENIED = new ResultEnum(1086, "查询菜单权限不足");

    /**
     * 字典
     */
    public static final ResultEnum DICTIONARY_EXIST_SUBSET_UNABLE_DEL = new ResultEnum(1901, "字典存在子属性无法删除");
    public static final ResultEnum DICTIONARY_EXIST_SAME_VALUE = new ResultEnum(1902, "字典存在相同值");
    public static final ResultEnum DICTIONARY_EXIST_SAME_NAME = new ResultEnum(1903, "字典存在相同名称");
    public static final ResultEnum DICTIONARY_NOT_TYPE = new ResultEnum(1904, "字典类型未填写");
    public static final ResultEnum DICTIONARY_TYPE_NOT_REPETITION = new ResultEnum(1905, "字典类型不能重复");
    public static final ResultEnum DICTIONARY_TYPE_SYSTEM_NOT_DEL = new ResultEnum(1906, "系统字典不允许删除");


}
