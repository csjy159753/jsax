package com.jinhe.modules.allowApi.dto;

import com.jinhe.common.util.StringUtils;
import com.jinhe.common.util.gemo.Point;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author Administrator
 */
@ApiModel(value="坐标",description="坐标")
public class PointDTO implements Serializable {
    private static final long serialVersionUID = 7308777432834186849L;
    /**
     * 经度
     */
    @ApiModelProperty(value="经度",name="x",example="1.0001")
    private String x;

    /**
     * 纬度
     */
    @ApiModelProperty(value="纬度",name="y",example="1.0001")
    private String y;

    public PointDTO() {
    }

    public PointDTO(String y, String x) {
        this.x = x;
        this.y = y;
    }

    /**
     * 数据转换
     * @return
     */
    public Point toPoint() {
        Point point = new Point(new BigDecimal(this.y), new BigDecimal(this.x));
        return point;
    }

    /**
     * 数据转换
     * @return
     */
    public Point.PointF toPointF() {
        Point.PointF point = new Point.PointF(Double.valueOf(this.y), Double.valueOf(this.x));
        return point;
    }

    /**
     * 属性检查
     * @return
     */
    public boolean checkValue() {
        int num = 4;
        return pointNumber(this.x, num) && pointNumber(this.y, num);
    }

    private boolean pointNumber(String str, int num) {
        // 校验是否是数字
        boolean pointCheck = StringUtils.isNumber(str);
        if (pointCheck){
            String[] strArray = str.split("\\.");
            if (strArray.length < 2) {
                return false;
            }
            if (strArray[1].length() < num) {
                return false;
            }
        }
        return pointCheck;
    }

    public String getX() {
        return x;
    }

    public void setX(String x) {
        this.x = x;
    }

    public String getY() {
        return y;
    }

    public void setY(String y) {
        this.y = y;
    }

    @Override
    public String toString() {
        return "PointDTO{" +
                "x='" + x + '\'' +
                ", y='" + y + '\'' +
                '}';
    }
}
