package com.jinhe.modules.system.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

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
public class SysOrganUserDto<T> implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 主键id
     */
    @ApiModelProperty(value="ID",name="id",example="123")
    private String id;

    /**
     * 父类ID
     */
    @ApiModelProperty(value="parentId",name="parentId",example="123")
    private String parentId;

    /**
     * 类型
     */
    @ApiModelProperty(value="type",name="type",example="1")
    private Integer type;

    /**
     * 编码
     */
    @ApiModelProperty(value="code",name="code",example="123")
    private String code;

    /**
     * 名称
     */
    @ApiModelProperty(value="name",name="name",example="123")
    private String name;

    /**
     * 全名
     */
    @ApiModelProperty(value="fullName",name="fullName",example="123")
    private String fullName;

    /**
     * 行政区编码
     */
    @ApiModelProperty(value="regionCode",name="regionCode",example="123")
    private String regionCode;

    /**
     * 行政区名称
     */
    @ApiModelProperty(value="regionName",name="regionName",example="123")
    private String regionName;

    /**
     * 描述
     */
    @ApiModelProperty(value="description",name="description",example="123")
    private String description;

    /**
     * 状态
     */
    @ApiModelProperty(value="state",name="state",example="123")
    private Integer state;

    /**
     * 排序
     */
    @ApiModelProperty(value="sort",name="sort",example="123")
    private Integer sort;

    @ApiModelProperty(value="path",name="path",example="123")
    private String path;

    /**
     * 深度
     */
    @ApiModelProperty(value="depth",name="depth",example="123")
    private Integer depth;

    /**
     * 创建人
     */
    @ApiModelProperty(value="createUser",name="createUser",example="123")
    private String createUser;

    /**
     * 创建时间
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")//接收参数
    private Date createTime;

    /**
     * 更新时间
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")//接收参数
    private Date updateTime;

    private String UserId;

}
