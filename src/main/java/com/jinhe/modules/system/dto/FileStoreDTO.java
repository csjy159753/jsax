package com.jinhe.modules.system.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * <p>
 *
 * </p>
 *
 * @author rls
 * @since 2020-04-27
 */
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

    public String getFileId() {
        return fileId;
    }

    public void setFileId(String fileId) {
        this.fileId = fileId;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getMimeTypeExt() {
        return mimeTypeExt;
    }

    public void setMimeTypeExt(String mimeTypeExt) {
        this.mimeTypeExt = mimeTypeExt;
    }

    public String getMimeTypeType() {
        return mimeTypeType;
    }

    public void setMimeTypeType(String mimeTypeType) {
        this.mimeTypeType = mimeTypeType;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }
}
