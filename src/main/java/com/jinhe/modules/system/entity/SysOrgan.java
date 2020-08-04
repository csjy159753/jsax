package com.jinhe.modules.system.entity;

import com.baomidou.mybatisplus.annotation.TableId;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author rls
 * @since 2020-04-16
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class SysOrgan implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 主键id
     */
    @TableId("ID")
    private String id;

    /**
     * 父类ID
     */
    @TableField("PARENT_ID")
    private String parentId;

    /**
     * 类型
     */
    @TableField("TYPE")
    private Integer type;

    /**
     * 编码
     */
    @TableField("CODE")
    private String code;

    /**
     * 名称
     */
    @TableField("NAME")
    private String name;

    /**
     * 全名
     */
    @TableField("FULL_NAME")
    private String fullName;

    /**
     * 行政区编码
     */
    @TableField("REGION_CODE")
    private String regionCode;

    /**
     * 行政区名称
     */
    @TableField("REGION_NAME")
    private String regionName;

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
     * 排序
     */
    @TableField("SORT")
    private Integer sort;

    @TableField("PATH")
    private String path;

    /**
     * 深度
     */
    @TableField("DEPTH")
    private Integer depth;

    /**
     * 创建人
     */
    @TableField("CREATE_USER")
    private String createUser;

    /**
     * 创建时间
     */
    @TableField("CREATE_TIME")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")//接收参数
    private Date createTime;

    /**
     * 更新时间
     */
    @TableField("UPDATE_TIME")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")//接收参数
    private Date updateTime;



}
