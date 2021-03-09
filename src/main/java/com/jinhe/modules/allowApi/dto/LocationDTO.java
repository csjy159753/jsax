package com.jinhe.modules.allowApi.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * @author Administrator
 */
@ApiModel(value="位置",description="位置")
public class LocationDTO implements Serializable {

    private static final long serialVersionUID = -2135658722504964479L;
    /**
     * 是否在面内
     */
    @ApiModelProperty(value="是否在面内",name="insideFlag",example="false")
    private Boolean insideFlag;

    /**
     * 投影点
     */
    @ApiModelProperty(value="投影点",name="pointP",example="{x:1.0001, y:1.0001}")
    private PointDTO pointP;

    /**
     * 点
     */
    @ApiModelProperty(value="点",name="point",example="{x:1.0001, y:1.0001}")
    private PointDTO point;

    /**
     * 最短距离
     */
    @ApiModelProperty(value="最短距离",name="distance",example="1.0001")
    private String distance;

    public LocationDTO() {
    }

    public Boolean getInsideFlag() {
        return insideFlag;
    }

    public void setInsideFlag(Boolean insideFlag) {
        this.insideFlag = insideFlag;
    }

    public PointDTO getPointP() {
        return pointP;
    }

    public void setPointP(PointDTO pointP) {
        this.pointP = pointP;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public PointDTO getPoint() {
        return point;
    }

    public void setPoint(PointDTO point) {
        this.point = point;
    }

    @Override
    public String toString() {
        return "LocationDTO{" +
                "insideFlag=" + insideFlag +
                ", pointP=" + pointP +
                ", point=" + point +
                ", distance='" + distance + '\'' +
                '}';
    }
}
