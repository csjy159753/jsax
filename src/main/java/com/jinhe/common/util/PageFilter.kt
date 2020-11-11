package com.jinhe.common.util

import io.swagger.annotations.ApiModelProperty
import lombok.Data

/**
 * @author Administrator
 */
@Data
class PageFilter {
    @ApiModelProperty(value = "关键字查询", position = 0)
    var keyWord: String? = null

    @ApiModelProperty(value = "第几页", example = "0", position = 1)
    var start: Int = 0

    @ApiModelProperty(value = "每页大小", example = "10", position = 2)
    var length: Int = 10

    @ApiModelProperty(value = "排序", example = "", position = 3)
    var order: String? = null
}