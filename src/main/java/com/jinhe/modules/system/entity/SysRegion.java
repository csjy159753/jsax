package com.jinhe.modules.system.entity;

import java.time.LocalDateTime;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * <p>
 * 行政区编码
 * </p>
 *
 * @author rls
 * @since 2020-10-29
 */
@ApiModel(value="SysRegion对象", description="行政区编码")
public class SysRegion implements Serializable {

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

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getParentCode() {
        return parentCode;
    }

    public void setParentCode(String parentCode) {
        this.parentCode = parentCode;
    }

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    public Integer getLevelInfo() {
        return levelInfo;
    }

    public void setLevelInfo(Integer levelInfo) {
        this.levelInfo = levelInfo;
    }

    public Integer getCityType() {
        return cityType;
    }

    public void setCityType(Integer cityType) {
        this.cityType = cityType;
    }

    public Boolean getAutonomy() {
        return isAutonomy;
    }

    public void setAutonomy(Boolean autonomy) {
        isAutonomy = autonomy;
    }

    public Boolean getEconomic() {
        return isEconomic;
    }

    public void setEconomic(Boolean economic) {
        isEconomic = economic;
    }

    public Boolean getSar() {
        return isSar;
    }

    public void setSar(Boolean sar) {
        isSar = sar;
    }

    public Boolean getDistrict() {
        return isDistrict;
    }

    public void setDistrict(Boolean district) {
        isDistrict = district;
    }

    public Boolean getBanner() {
        return isBanner;
    }

    public void setBanner(Boolean banner) {
        isBanner = banner;
    }

    public Boolean getLeague() {
        return isLeague;
    }

    public void setLeague(Boolean league) {
        isLeague = league;
    }

    public Boolean getForestryArea() {
        return isForestryArea;
    }

    public void setForestryArea(Boolean forestryArea) {
        isForestryArea = forestryArea;
    }

    public Boolean getSumu() {
        return isSumu;
    }

    public void setSumu(Boolean sumu) {
        isSumu = sumu;
    }

    public Boolean getNaturalVillage() {
        return isNaturalVillage;
    }

    public void setNaturalVillage(Boolean naturalVillage) {
        isNaturalVillage = naturalVillage;
    }

    public Boolean getVirtual() {
        return isVirtual;
    }

    public void setVirtual(Boolean virtual) {
        isVirtual = virtual;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public Double getLng() {
        return lng;
    }

    public void setLng(Double lng) {
        this.lng = lng;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
