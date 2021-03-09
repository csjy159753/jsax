package com.jinhe.modules.allowApi.dto;

import com.alibaba.fastjson.JSON;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

/**
 * @author Administrator
 */
@ApiModel(value="请求点与面的位置",description="请求点与面的位置")
public class LocationRequestDTO implements Serializable {
    private static final long serialVersionUID = 3246289838021627221L;
    /**
     * 点组成的面
     */
    @ApiModelProperty(value="点组成的面",name="pointList")
    private List<PointDTO> pointList;

    /**
     * 点
     */
    @ApiModelProperty(value="点",name="point",example="{x:1.0001,y:1.0032}")
    private PointDTO point;

    public List<PointDTO> getPointList() {
        return pointList;
    }

    public void setPointList(List<PointDTO> pointList) {
        this.pointList = pointList;
    }

    public PointDTO getPoint() {
        return point;
    }

    public void setPoint(PointDTO point) {
        this.point = point;
    }

    @Override
    public String toString() {
        return "LocationRequsetDTO{" +
                "pointList=" + pointList +
                ", point=" + point +
                '}';
    }

}
