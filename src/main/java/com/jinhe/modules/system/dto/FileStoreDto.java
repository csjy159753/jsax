package com.jinhe.modules.system.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

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
public class FileStoreDto implements Serializable {

    @ApiModelProperty(value = "fileId")
    private String fileId;

    @ApiModelProperty(value = "文件名称")
    private String fileName;


    @ApiModelProperty(value = "文件格式后缀")
    private String mimeTypeExt;

    @ApiModelProperty(value = "文件类型")
    private String mimeTypeType;

    private String path;//前端所需路径

    private String size;//文件大小



}
