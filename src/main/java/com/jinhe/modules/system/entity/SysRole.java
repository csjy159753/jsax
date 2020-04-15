package com.jinhe.modules.system.entity;

import java.util.Date;
import java.io.Serializable;


import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author rls
 * @since 2020-04-14
 */
@Data
@Accessors(chain = true)
@TableName("sys_role")
public class SysRole extends Model<SysRole> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    @TableId("ID")
    private String id;

    /**
     * 父级id
     */
    @TableField("PARENT_ID")
    private String parentId;

    /**
     * 名称
     */
    @TableField("NAME")
    private String name;

    /**
     * 标签
     */
    @TableField("TAG")
    private String tag;

    /**
     * 类型
     */
    @TableField("TYPE")
    private Integer type;

    /**
     * 排序
     */
    @TableField("SORT")
    private Integer sort;

    /**
     * 是否锁定
     */
    @TableField("LOCK_OUT_ENABLED")
    private Integer lockOutEnabled;

    /**
     * 锁定时间
     */
    @TableField("LOCK_OUT_TIME")
    private Date lockOutTime;

    /**
     * 创建人id
     */
    @TableField("OPERATOR_ID")
    private String operatorId;

    /**
     * 创建时间
     */
    @TableField("CREATE_TIME")
    private Date createTime;

    /**
     * 描述
     */
    @TableField("DESCRIPTION")
    private String description;

    /**
     * 状态
     */
    @TableField("STATE")
    private Integer state;

    /**
     * 更新时间
     */
    @TableField("UPDATE_TIME")
    private Date updateTime;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
