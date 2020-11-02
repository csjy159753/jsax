package com.jinhe.modules.system.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 *
 * </p>
 *
 * @author rls
 * @since 2020-04-27
 */
@Data
@ApiModel(value = "FileStore对象", description = "")
public class FileStoreDTO implements Serializable {

    @ApiModelProperty(value = "fileId")
    private String fileId;

    @ApiModelProperty(value = "文件名称")
    private String fileName;


    @ApiModelProperty(value = "文件格式后缀")
    private String mimeTypeExt;

    @ApiModelProperty(value = "文件类型")
    private String mimeTypeType;
    @ApiModelProperty(value = "前端访问文件路径")
    private String path;
    @ApiModelProperty(value = "文件大小")
    private String size;


}
