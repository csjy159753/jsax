package com.jinhe.modules.system.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * <p>
 * 文件存储类
 * </p>
 *
 * @author rls
 * @since 2020-10-29
 */
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

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileId() {
        return fileId;
    }

    public void setFileId(String fileId) {
        this.fileId = fileId;
    }

    public String getOriginalFile() {
        return originalFile;
    }

    public void setOriginalFile(String originalFile) {
        this.originalFile = originalFile;
    }

    public String getModifiedImgae() {
        return modifiedImgae;
    }

    public void setModifiedImgae(String modifiedImgae) {
        this.modifiedImgae = modifiedImgae;
    }

    public String getLowImgae() {
        return lowImgae;
    }

    public void setLowImgae(String lowImgae) {
        this.lowImgae = lowImgae;
    }

    public String getThumbImgae() {
        return thumbImgae;
    }

    public void setThumbImgae(String thumbImgae) {
        this.thumbImgae = thumbImgae;
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

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getTableId() {
        return tableId;
    }

    public void setTableId(String tableId) {
        this.tableId = tableId;
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
}
