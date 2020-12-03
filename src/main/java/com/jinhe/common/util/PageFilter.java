package com.jinhe.common.util;

import io.swagger.annotations.ApiModelProperty;

/**
 * @author Administrator
 */
public class PageFilter {
    @ApiModelProperty(value = "关键字查询", example = "关键字", position = 0)
    private String keyWord;
    @ApiModelProperty(value = "第几页 默认1开始", example = "1", position = 1)
    private int start;
    @ApiModelProperty(value = "每页大小", example = "10", position = 2)
    private int length;
    @ApiModelProperty(value = "排序", example = "", position = 3)
    private String order;

    public String getKeyWord() {
        return keyWord;
    }

    public void setKeyWord(String keyWord) {
        this.keyWord = keyWord;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }
}
