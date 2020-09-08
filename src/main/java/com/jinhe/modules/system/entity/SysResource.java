package com.jinhe.modules.system.entity;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author rls
 * @since 2020-04-13
 */
@Data
@Accessors(chain = true)
@TableName("sys_resource")
public class SysResource extends Model<SysResource> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    @TableId("ID")
    private String id;

    /**
     * 父键id
     */
    @TableField("PARENT_ID")
    private String parentId;

    /**
     * 英文名称
     */
    @TableField("NAME")
    private String name;

    /**
     * 显示名称
     */
    @TableField("DISPLAY_NAME")
    private String displayName;

    /**
     * 描述
     */
    @TableField("NOTE")
    private String note;

    /**
     * 状态0正常1禁用
     */
    @TableField("STATE")
    private Integer state;

    /**
     * 类型（预留）
     */
    @TableField("TYPE")
    private Integer type;

    /**
     * 排序
     */
    @TableField("SORT")
    private Integer sort;

    /**
     * url路径
     */
    @TableField("PATH")
    private String path;

    /**
     * 小图标
     */
    @TableField("ICON")
    private String icon;

    /**
     * 创建者id
     */
    @TableField("CREATE_USER_ID")
    private String createUserId;

    /**
     * 创建时间
     */
    @TableField("CREATE_TIME")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")//接收参数
    private Date createTime;

    /**
     * 更新
     */
    @TableField("UPDATE_TIME")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")//接收参数
    private Date updateTime;

    /**
     * 显示类型
     */
    @TableField("DISPLAY_TYPE")
    private Integer displayType;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
