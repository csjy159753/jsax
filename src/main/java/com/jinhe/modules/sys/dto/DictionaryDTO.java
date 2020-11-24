package com.jinhe.modules.sys.dto;

import com.jinhe.common.util.Tree.Children;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.time.LocalDateTime;

public class DictionaryDTO extends Children<DictionaryDTO> implements Serializable {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "主键")
    private String id;

    @ApiModelProperty(value = "父id")
    private String parentId;

    @ApiModelProperty(value = "编码名称")
    private String name;

    @ApiModelProperty(value = "标识信息")
    private String type;

    @ApiModelProperty(value = "层级深度")
    private Integer levelInfo;

    @ApiModelProperty(value = "编码值")
    private String value;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "是否是系统字段0常用字典1系统字典")
    private Boolean isSystem;

    @ApiModelProperty(value = "排序")
    private Integer sortOrder;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getLevelInfo() {
        return levelInfo;
    }

    public void setLevelInfo(Integer levelInfo) {
        this.levelInfo = levelInfo;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
    }

    public Boolean getSystem() {
        return isSystem;
    }

    public void setSystem(Boolean isSystem) {
        this.isSystem = isSystem;
    }

    public Integer getSortOrder() {
        return sortOrder;
    }

    public void setSortOrder(Integer sortOrder) {
        this.sortOrder = sortOrder;
    }

    @Override
    public String toString() {
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
                "}";
    }
}
