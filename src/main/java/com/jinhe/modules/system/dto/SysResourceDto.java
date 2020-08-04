package com.jinhe.modules.system.dto;


import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.jinhe.modules.system.entity.SysResourceItem;
import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * author:xk
 * date:2020.04.15
 **/
@Data
public class SysResourceDto implements Serializable {


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

    @JSONField
    private List<SysResourceItem> sysResourceItem = new ArrayList<>(1);


}
