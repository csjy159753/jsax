package com.jinhe.modules.system.dto;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author Administrator
 */
@Data
public class SysRegionDTO implements Serializable{
    private static final long serialVersionUID = 1L;

    private String id;

    @ApiModelProperty(value = "行政区名称")
    private String name;

    private String fullName;

    @ApiModelProperty(value = "行政区代码")
    private String code;

    @ApiModelProperty(value = "上级行政区代码")
    private String parentCode;

    @ApiModelProperty(value = "城市代码(固定电话，例如北京010)")
    private String cityCode;

    @ApiModelProperty(value = "行政区级别 0_国家 1_省级（province） 2_地区级（prefecture） 3_县级（county） 4_乡镇级（township） 5_行政村级（village） 6_自然村（natura villagel）")
    private Integer levelInfo;

    @ApiModelProperty(value = "1_直辖市，2_计划单列市，3_省会城市，4_副省级市辖区，4_地级市，5_副地级市，6_县级市 0_非城市")
    private Integer cityType;

    @ApiModelProperty(value = "是否是自治行政区")
    private Boolean isAutonomy;

    @ApiModelProperty(value = "是否是经济特区")
    private Boolean isEconomic;

    @ApiModelProperty(value = "是否是特别行政区")
    private Boolean isSar;

    @ApiModelProperty(value = "是否是市辖区")
    private Boolean isDistrict;

    @ApiModelProperty(value = "是否是旗（县、市、区）")
    private Boolean isBanner;

    @ApiModelProperty(value = "是否是盟（市）")
    private Boolean isLeague;

    @ApiModelProperty(value = "是否市独立林区")
    private Boolean isForestryArea;

    @ApiModelProperty(value = "是否是一个苏木（县）")
    private Boolean isSumu;

    @ApiModelProperty(value = "是否是自然村")
    private Boolean isNaturalVillage;

    @ApiModelProperty(value = "为调查设立的虚拟区域")
    private Boolean isVirtual;

    @ApiModelProperty(value = "创建时间")

    private LocalDateTime createTime;

    @ApiModelProperty(value = "经度")
    private Double lng;

    @ApiModelProperty(value = "维度")
    private Double lat;

    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "url")
    private String url;

    @ApiModelProperty(value = "路径")
    private String path;

    @ApiModelProperty(value = "子项数量")
    private Integer childrenNum;
}
