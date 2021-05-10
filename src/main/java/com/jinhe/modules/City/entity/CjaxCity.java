package com.jinhe.modules.City.entity;

import java.time.LocalDateTime;
import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * <p>
 * 
 * </p>
 *
 * @author rls
 * @since 2021-05-08
 */
@ApiModel(value="CjaxCity对象", description="")
public class CjaxCity implements Serializable {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "id")
    private Integer id;

    @ApiModelProperty(value = "省代码")
    private String codeForProvince;

    @ApiModelProperty(value = "省")
    private String province;

    @ApiModelProperty(value = "市代码")
    private String codeForCity;

    @ApiModelProperty(value = "市")
    private String city;

    @ApiModelProperty(value = "类型")
    private String typeOfCity;

    @ApiModelProperty(value = "shape_leng")
    private String shapeLeng;

    @ApiModelProperty(value = "shape_len_1")
    @TableField(value = "shape_len_1")
    private String shapeLen1;

    @ApiModelProperty(value = "shape_area")
    private String shapeArea;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime creatTime;

    @ApiModelProperty(value = "修改时间")
    private LocalDateTime updateTime;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCodeForProvince() {
        return codeForProvince;
    }

    public void setCodeForProvince(String codeForProvince) {
        this.codeForProvince = codeForProvince;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCodeForCity() {
        return codeForCity;
    }

    public void setCodeForCity(String codeForCity) {
        this.codeForCity = codeForCity;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getTypeOfCity() {
        return typeOfCity;
    }

    public void setTypeOfCity(String typeOfCity) {
        this.typeOfCity = typeOfCity;
    }

    public String getShapeLeng() {
        return shapeLeng;
    }

    public void setShapeLeng(String shapeLeng) {
        this.shapeLeng = shapeLeng;
    }

    public String getShapeLen1() {
        return shapeLen1;
    }

    public void setShapeLen1(String shapeLen1) {
        this.shapeLen1 = shapeLen1;
    }

    public String getShapeArea() {
        return shapeArea;
    }

    public void setShapeArea(String shapeArea) {
        this.shapeArea = shapeArea;
    }

    public LocalDateTime getCreatTime() {
        return creatTime;
    }

    public void setCreatTime(LocalDateTime creatTime) {
        this.creatTime = creatTime;
    }

    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        return "CjaxCity{" +
        "id=" + id +
        ", codeForProvince=" + codeForProvince +
        ", province=" + province +
        ", codeForCity=" + codeForCity +
        ", city=" + city +
        ", typeOfCity=" + typeOfCity +
        ", shapeLeng=" + shapeLeng +
        ", shapeLen1=" + shapeLen1 +
        ", shapeArea=" + shapeArea +
        ", creatTime=" + creatTime +
        ", updateTime=" + updateTime +
        "}";
    }
}
