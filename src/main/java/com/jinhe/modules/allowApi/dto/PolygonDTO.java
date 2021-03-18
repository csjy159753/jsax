package com.jinhe.modules.allowApi.dto;

import com.jinhe.common.util.gemo.entity.PointG;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.List;

/**
 * @author Administrator
 */
@ApiModel(value="多边形",description="多边形")
public class PolygonDTO implements Serializable {
    private static final long serialVersionUID = 5380957333440689856L;

    @ApiModelProperty(value="点组成的面",name="pointList")
    private List<PointG> list;

    @ApiModelProperty(value = "间距", name = "distance")
    private Double distance;

    public List<PointG> getList() {
        return list;
    }

    public void setList(List<PointG> list) {
        this.list = list;
    }

    public Double getDistance() {
        return distance;
    }

    public void setDistance(Double distance) {
        this.distance = distance;
    }

    @Override
    public String toString() {
        return "PolygonDTO{" +
                "list=" + list +
                ", distance=" + distance +
                '}';
    }
}
