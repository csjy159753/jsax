package com.jinhe.modules.Enterprise.entity;

import java.time.LocalDateTime;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * <p>
 * 
 * </p>
 *
 * @author rls
 * @since 2021-05-07
 */
@ApiModel(value="CjaxProducts对象", description="")
public class CjaxProducts implements Serializable {

    private static final long serialVersionUID=1L;

    private String id;

    @ApiModelProperty(value = "省")
    private String province;

    @ApiModelProperty(value = "序号_1")
    private Integer sort1;

    @ApiModelProperty(value = "市")
    private String city;

    @ApiModelProperty(value = "县")
    private String county;

    @ApiModelProperty(value = "项目编码")
    private String projectCoding;

    @ApiModelProperty(value = "项目名称")
    private String projectName;

    @ApiModelProperty(value = "项目类型")
    private String projectType;

    @ApiModelProperty(value = "权属单位")
    private String ownershipUnit;

    @ApiModelProperty(value = "开工时间")
    private LocalDateTime startTime;

    @ApiModelProperty(value = "建成时间")
    private LocalDateTime endTime;

    @ApiModelProperty(value = "项目概位坐标东经 ")
    private String longitude;

    @ApiModelProperty(value = "项目概位坐标北纬 ")
    private String latitude;

    @ApiModelProperty(value = "功能区类型")
    private String functionType;

    @ApiModelProperty(value = "项目是否符合功能分区管控要求")
    private String meetRequirements;

    @ApiModelProperty(value = "二级管控要求 ")
    private String secondRequirements;

    @ApiModelProperty(value = "自然保护区")
    private String natureReserve;

    @ApiModelProperty(value = "自然保护区之前还是之后建设")
    private String beforeProtectZoomOrAfter;

    @ApiModelProperty(value = "水源 ")
    private String waterSource;

    @ApiModelProperty(value = "风景")
    private String sceneries;

    @ApiModelProperty(value = "水产 ")
    private String waterProducts;

    private LocalDateTime createTime;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public Integer getSort1() {
        return sort1;
    }

    public void setSort1(Integer sort1) {
        this.sort1 = sort1;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    public String getProjectCoding() {
        return projectCoding;
    }

    public void setProjectCoding(String projectCoding) {
        this.projectCoding = projectCoding;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getProjectType() {
        return projectType;
    }

    public void setProjectType(String projectType) {
        this.projectType = projectType;
    }

    public String getOwnershipUnit() {
        return ownershipUnit;
    }

    public void setOwnershipUnit(String ownershipUnit) {
        this.ownershipUnit = ownershipUnit;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getFunctionType() {
        return functionType;
    }

    public void setFunctionType(String functionType) {
        this.functionType = functionType;
    }

    public String getMeetRequirements() {
        return meetRequirements;
    }

    public void setMeetRequirements(String meetRequirements) {
        this.meetRequirements = meetRequirements;
    }

    public String getSecondRequirements() {
        return secondRequirements;
    }

    public void setSecondRequirements(String secondRequirements) {
        this.secondRequirements = secondRequirements;
    }

    public String getNatureReserve() {
        return natureReserve;
    }

    public void setNatureReserve(String natureReserve) {
        this.natureReserve = natureReserve;
    }

    public String getBeforeProtectZoomOrAfter() {
        return beforeProtectZoomOrAfter;
    }

    public void setBeforeProtectZoomOrAfter(String beforeProtectZoomOrAfter) {
        this.beforeProtectZoomOrAfter = beforeProtectZoomOrAfter;
    }

    public String getWaterSource() {
        return waterSource;
    }

    public void setWaterSource(String waterSource) {
        this.waterSource = waterSource;
    }

    public String getSceneries() {
        return sceneries;
    }

    public void setSceneries(String sceneries) {
        this.sceneries = sceneries;
    }

    public String getWaterProducts() {
        return waterProducts;
    }

    public void setWaterProducts(String waterProducts) {
        this.waterProducts = waterProducts;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "CjaxProducts{" +
        "id=" + id +
        ", province=" + province +
        ", sort1=" + sort1 +
        ", city=" + city +
        ", county=" + county +
        ", projectCoding=" + projectCoding +
        ", projectName=" + projectName +
        ", projectType=" + projectType +
        ", ownershipUnit=" + ownershipUnit +
        ", startTime=" + startTime +
        ", endTime=" + endTime +
        ", longitude=" + longitude +
        ", latitude=" + latitude +
        ", functionType=" + functionType +
        ", meetRequirements=" + meetRequirements +
        ", secondRequirements=" + secondRequirements +
        ", natureReserve=" + natureReserve +
        ", beforeProtectZoomOrAfter=" + beforeProtectZoomOrAfter +
        ", waterSource=" + waterSource +
        ", sceneries=" + sceneries +
        ", waterProducts=" + waterProducts +
        ", createTime=" + createTime +
        "}";
    }
}
