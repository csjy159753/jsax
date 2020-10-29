package com.jinhe.modules.system.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 文件存储类
 * </p>
 *
 * @author rls
 * @since 2020-10-29
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="FileStore对象", description="文件存储类")
public class FileStore implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键id")
    @TableId("ID")
    private String id;

    @ApiModelProperty(value = "文件名称")
    @TableField("FILE_NAME")
    private String fileName;

    @ApiModelProperty(value = "fileId")
    @TableField("FILE_ID")
    private String fileId;

    @ApiModelProperty(value = "原文件路径")
    @TableField("ORIGINAL_FILE")
    private String originalFile;

    @ApiModelProperty(value = "修正后的路径地址")
    @TableField("MODIFIED_IMGAE")
    private String modifiedImgae;

    @ApiModelProperty(value = "小图")
    @TableField("LOW_IMGAE")
    private String lowImgae;

    @ApiModelProperty(value = "略缩图")
    @TableField("THUMB_IMGAE")
    private String thumbImgae;

    @ApiModelProperty(value = "创建时间")
    @TableField("CREATE_TIME")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "更新时间")
    @TableField("UPDATE_TIME")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "表名")
    @TableField("TABLE_NAME")
    private String tableName;

    @ApiModelProperty(value = "表ID")
    @TableField("TABLE_ID")
    private String tableId;

    @ApiModelProperty(value = "文件格式后缀")
    @TableField("MIME_TYPE_EXT")
    private String mimeTypeExt;

    @ApiModelProperty(value = "文件类型")
    @TableField("MIME_TYPE_TYPE")
    private String mimeTypeType;


}
