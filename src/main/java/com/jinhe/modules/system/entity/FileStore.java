package com.jinhe.modules.system.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 
 * </p>
 *
 * @author rls
 * @since 2020-04-27
 */
@Data
@ApiModel(value="FileStore对象", description="")
public class FileStore implements Serializable {

    @ApiModelProperty(value = "主键id")
    private String id;

    @ApiModelProperty(value = "文件名称")
    private String fileName;

    @ApiModelProperty(value = "fileId")
    private String fileId;

    @ApiModelProperty(value = "原文件路径")
    private String originalFile;

    @ApiModelProperty(value = "修正后的路径地址")
    private String modifiedImgae;

    @ApiModelProperty(value = "小图")
    private String lowImgae;

    @ApiModelProperty(value = "略缩图")
    private String thumbImgae;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "表名")
    private String tableName;

    @ApiModelProperty(value = "表ID")
    private String tableId;

    @ApiModelProperty(value = "文件格式后缀")
    private String mimeTypeExt;

    @ApiModelProperty(value = "文件类型")
    private String mimeTypeType;


}
