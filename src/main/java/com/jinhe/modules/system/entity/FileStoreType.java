package com.jinhe.modules.system.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * <p>
 * 文件存储类型
 * </p>
 *
 * @author rls
 * @since 2020-11-27
 */
@ApiModel(value="FileStoreType对象", description="文件存储类型")
public class FileStoreType implements Serializable {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "主键")
    @TableId("ID")
    private Integer id;

    @ApiModelProperty(value = "后缀类型")
    @TableField("EXT")
    private String ext;

    @ApiModelProperty(value = "类型")
    @TableField("TYPE")
    private String type;

    @ApiModelProperty(value = "是否使用")
    @TableField("IS_USE")
    private Integer isUse;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getExt() {
        return ext;
    }

    public void setExt(String ext) {
        this.ext = ext;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getIsUse() {
        return isUse;
    }

    public void setIsUse(Integer isUse) {
        this.isUse = isUse;
    }

    @Override
    public String toString() {
        return "FileStoreType{" +
        "id=" + id +
        ", ext=" + ext +
        ", type=" + type +
        ", isUse=" + isUse +
        "}";
    }
}
