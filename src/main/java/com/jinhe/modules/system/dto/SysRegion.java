package com.jinhe.modules.system.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 
 * </p>
 *
 * @author rls
 * @since 2020-04-16
 */
@Data
@ApiModel(value="行政区",description="行政区")
public class SysRegion {

    /**
     * 行政区ID
     */
    @ApiModelProperty(value="ID",name="id",example="123")
    private String id;

    /**
     * 行政区名称
     */
    @ApiModelProperty(value="名称",name="name",example="马鞍山")
    private String name;

    /**
     * 行政区完整名称
     */
    @ApiModelProperty(value="完整名称",name="fullName",example="安徽省马鞍山")
    private String fullName;

    /**
     * 行政区代码
     */
    @ApiModelProperty(value="行政区代码",name="code",example="12")
    private String code;

    /**
     * 上级行政区代码
     */
    @ApiModelProperty(value="上级行政区代码",name="parentCode",example="1")
    private String parentCode;

    /**
     * 城市代码(固定电话，例如北京010)
     */
    @ApiModelProperty(value="城市代码",name="cityCode",example="0555")
    private String cityCode;

    /**
     * 行政区级别 0_国家 1_省级（province） 2_地区级（prefecture） 3_县级（county） 4_乡镇级（township） 5_行政村级（village） 6_自然村（natura villagel）
     */
    @ApiModelProperty(value="行政区级别",name="level",example="2")
    private Integer level;

    /**
     * 1_直辖市，2_计划单列市，3_省会城市，4_副省级市辖区，4_地级市，5_副地级市，6_县级市 0_非城市
     */
    @ApiModelProperty(value="类型",name="cityType",example="4")
    private Integer cityType;

    /**
     * 是否是自治行政区
     */
    @ApiModelProperty(value="是否是自治行政区",name="isAutonomy",example="false")
    private Boolean isAutonomy;

    /**
     * 是否是经济特区
     */
    @ApiModelProperty(value="是否是经济特区",name="isEconomic",example="false")
    private Boolean isEconomic;

    /**
     * 是否是特别行政区
     */
    @ApiModelProperty(value="是否是特别行政区",name="isSar",example="false")
    private Boolean isSar;

    /**
     * 是否是市辖区
     */
    @ApiModelProperty(value="是否是市辖区",name="isDistrict",example="false")
    private Boolean isDistrict;

    /**
     * 是否是旗（县、市、区）
     */
    @ApiModelProperty(value="是否是旗（县、市、区）",name="isBanner",example="false")
    private Boolean isBanner;

    /**
     * 是否是盟（市）
     */
    @ApiModelProperty(value="是否是盟（市）",name="isLeague",example="false")
    private Boolean isLeague;

    /**
     * 是否是独立林区
     */
    @ApiModelProperty(value="是否是独立林区",name="isForestryArea",example="false")
    private Boolean isForestryArea;

    /**
     * 是否是一个苏木（县）
     */
    @ApiModelProperty(value="是否是一个苏木（县）",name="isSumu",example="false")
    private Boolean isSumu;

    /**
     * 是否是自然村
     */
    @ApiModelProperty(value="是否是自然村",name="isNaturalVillage",example="false")
    private Boolean isNaturalVillage;

    /**
     * 为调查设立的虚拟区域
     */
    @ApiModelProperty(value="为调查设立的虚拟区域",name="isVirtual",example="1")
    private Boolean isVirtual;

    /**
     * 创建时间
     */
    @ApiModelProperty(value="创建时间",name="createTime",example="1")
    private LocalDateTime createTime;

    /**
     * 经度
     */
    @ApiModelProperty(value="经度",name="lng",example="1")
    private Double lng;

    /**
     * 维度
     */
    @ApiModelProperty(value="维度",name="lat",example="1")
    private Double lat;

    /**
     * 更新时间
     */
    @ApiModelProperty(value="更新时间",name="updateTime",example="1")
    private LocalDateTime updateTime;


}
