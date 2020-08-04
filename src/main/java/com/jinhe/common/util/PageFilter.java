package com.jinhe.common.util;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class PageFilter {
    @ApiModelProperty(value = "关键字查询", position = 0)
    private String keyWord;
    @ApiModelProperty(value = "第几页", example = "0", position = 1)
    private int start;
    @ApiModelProperty(value = "每页大小", example = "10", position = 2)
    private int length;
    @ApiModelProperty(value = "排序", example = "", position = 3)
    private String order;
}
