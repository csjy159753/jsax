package com.jinhe.modules.system.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author rls
 * @since 2020-06-30
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("FILE_STORE_TYPE")
@ApiModel(value="FileStoreType对象", description="")
public class FileStoreType implements Serializable {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "主键")
    @TableId(value = "ID",type = IdType.ID_WORKER )
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


}
