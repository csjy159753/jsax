package com.jinhe.modules.system.dto;

import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.jinhe.modules.system.entity.SysRegion;
import com.jinhe.modules.system.entity.SysResource;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

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
public class SysRegionDTO<T> implements Serializable{
    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value = "id")
    @TableField("ID")
    private String id;
    @ApiModelProperty(value = "地区名称")
    @TableField("NAME")
    private String name;
    @ApiModelProperty(value = "地区全称")
    @TableField("FULL_NAME")
    private String fullName;
    @ApiModelProperty(value = "区划编码")
    @TableField("CODE")
    private String code;
    @ApiModelProperty(value = "父级编码")
    @TableField("PARENT_CODE")
    private String parentCode;
    @ApiModelProperty(value = "行政区级别 0_国家 1_省级 2_地区级3县级4乡镇级5行政村级6自然村级")
    @TableField("LEVEL_INFO")
    private Integer levelInfo;
    @ApiModelProperty(value = "1_直辖市，2_计划单列市，3_省会城市，4_副省级市辖区，4_地级市，5_副地级市，6_县级市 0_非城市")
    @TableField("CITY_CODE")
    private Integer cityCode;
    @ApiModelProperty(value = "是否是自治行政区")
    @TableField("IS_AUTONOMY")
    private Integer isAutonomy;
    @ApiModelProperty(value = "是否是经济特区")
    @TableField("IS_ECONOMIC")
    private Integer isEconomic;
    @ApiModelProperty(value = "是否是特别行政区")
    @TableField("IS_SAR")
    private Integer isSar;
    @ApiModelProperty(value = "是否是市辖区")
    @TableField("IS_DISTRICT")
    private Integer isDistrict;
    @ApiModelProperty(value = "是否是旗（县、市、区）")
    @TableField("IS_BANNER")
    private Integer isBanner;
    @ApiModelProperty(value = "是否是盟（市）")
    @TableField("IS_LEAGUE")
    private Integer isLeague;
    @ApiModelProperty(value = "是否是独立林区")
    @TableField("IS_FORESTRY_AREA")
    private Integer isForestryArea;
    @ApiModelProperty(value = "是否是一个苏木（市）")
    @TableField("IS_SUMN")
    private Integer isSumn;
    @ApiModelProperty(value = "是否是自然村")
    @TableField("IS_NATURAL_VILLAGE")
    private Integer isNaturalVillage;
    @ApiModelProperty(value = "为调查设立的虚拟区域")
    @TableField("IS_VIRTUAL")
    private Integer isVirtual;
    @ApiModelProperty(value = "经度")
    @TableField("LNG")
    private Integer lng;
    @ApiModelProperty(value = "纬度")
    @TableField("LAT")
    private Integer lat;
    @ApiModelProperty(value = "创建时间")
    @TableField("CREATE_TIME")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;
    @ApiModelProperty(value = "更新时间")
    @TableField("UPADTE_TIME")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updateTime;

    private Integer depth;

    private int childrenNum ;

}
