package com.jinhe.modules.gadl.dto;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

public class TransFormPointsDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "type", name = "类型1表示平面坐标系转球面坐标系", example = "1")
    private int type;
    @ApiModelProperty(value = "epsg", name = "EPSG 投影坐标系编号例子EPSG:4490", example = "4490")
    private String epsg;
    @ApiModelProperty(value = "clong", name = "中央经线", example = "117")
    private int clong;
    @ApiModelProperty(value = "fe", name = "偏移量", example = "350000")
    private long fe;
    @ApiModelProperty(value = "points", name = "需要转换的坐标集合", example = "[[34.43848700623854, 117.49032390479873],[34.43848700623854, 117.49032390479873]]")
    private double[][] points;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getEpsg() {
        return epsg;
    }

    public void setEpsg(String epsg) {
        this.epsg = epsg;
    }

    public int getClong() {
        return clong;
    }

    public void setClong(int clong) {
        this.clong = clong;
    }

    public long getFe() {
        return fe;
    }

    public void setFe(long fe) {
        this.fe = fe;
    }

    public double[][] getPoints() {
        return points;
    }

    public void setPoints(double[][] points) {
        this.points = points;
    }
}
