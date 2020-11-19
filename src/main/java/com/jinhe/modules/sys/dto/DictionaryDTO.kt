package com.jinhe.modules.sys.dto

import com.jinhe.common.util.Tree.Children
import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty
import java.io.Serializable
import java.time.LocalDateTime
/**
 * <p>
 * 数据字典
 * </p>
 *
 * @author rls
 * @since 2020-11-18
 */
@ApiModel(value="Dictionary对象", description="数据字典")
class DictionaryDTO  : Serializable, Children<DictionaryDTO>() {

    @ApiModelProperty(value = "主键")
    var id: String? = null
    @ApiModelProperty(value = "父id")
    var parentId: String? = null
    @ApiModelProperty(value = "编码名称")
    var name: String? = null
    @ApiModelProperty(value = "标识信息")
    var type: String? = null
    @ApiModelProperty(value = "层级深度")
    var levelInfo: Int? = null
    @ApiModelProperty(value = "编码值")
    var value: String? = null
    @ApiModelProperty(value = "创建时间")
    var createTime: LocalDateTime? = null
    @ApiModelProperty(value = "更新时间")
    var updateTime: LocalDateTime? = null
    @ApiModelProperty(value = "是否是系统字段1常用字典2系统字典")
    var isSystem: Boolean? = null
    @ApiModelProperty(value = "排序")
    var sortOrder: Int? = null


    override fun toString(): String {
        return "Dictionary{" +
                "id=" + id +
                ", parentId=" + parentId +
                ", name=" + name +
                ", type=" + type +
                ", levelInfo=" + levelInfo +
                ", value=" + value +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", isSystem=" + isSystem +
                ", sortOrder=" + sortOrder +
                "}"
    }
}
