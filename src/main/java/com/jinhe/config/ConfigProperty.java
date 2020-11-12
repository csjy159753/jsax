package com.jinhe.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.File;

/**
 * @author Administrator
 */
@Component
public class ConfigProperty {
    @Value("${file.root.upload}")
    private String upload;
    @Value("${file.root.uploadFile}")
    private String uploadFile;
    @Value("${file.root.watermark.path}")
    private String watermarkPath;
    @Value("${file.root.watermark.enabled}")
    private Boolean watermarkEnabled;
    @Value("${file.root.img-compression.modified}")
    private double imgCompressionModified;
    @Value("${file.root.img-compression.lowFile}")
    private double imgCompressionLowFile;
    @Value("${file.root.img-compression.thumb}")
    private double imgCompressionThumb;

    @Value("${file.root.pksFile}")
    private String pksFile;
    @Value("${version}")
    private String version;

    public String GetAbsolutelyUpload() {
        return System.getProperty("user.dir") + File.separator + upload;
    }
    public String GetAbsolutelyDir() {
        return System.getProperty("user.dir");
    }
    public String GetAbsolutelyUploadFile() {
        return System.getProperty("user.dir") + File.separator + uploadFile;
    }

    public String GetAbsolutelyWatermarkPath() {
        return System.getProperty("user.dir") + File.separator + watermarkPath;
    }

    public String getUpload() {
        return upload;
    }

    public void setUpload(String upload) {
        this.upload = upload;
    }

    public String getUploadFile() {
        return uploadFile;
    }

    public void setUploadFile(String uploadFile) {
        this.uploadFile = uploadFile;
    }

    public String getWatermarkPath() {
        return watermarkPath;
    }

    public void setWatermarkPath(String watermarkPath) {
        this.watermarkPath = watermarkPath;
    }

    public Boolean getWatermarkEnabled() {
        return watermarkEnabled;
    }

    public void setWatermarkEnabled(Boolean watermarkEnabled) {
        this.watermarkEnabled = watermarkEnabled;
    }

    public double getImgCompressionModified() {
        return imgCompressionModified;
    }

    public void setImgCompressionModified(double imgCompressionModified) {
        this.imgCompressionModified = imgCompressionModified;
    }

    public double getImgCompressionLowFile() {
        return imgCompressionLowFile;
    }

    public void setImgCompressionLowFile(double imgCompressionLowFile) {
        this.imgCompressionLowFile = imgCompressionLowFile;
    }

    public double getImgCompressionThumb() {
        return imgCompressionThumb;
    }

    public void setImgCompressionThumb(double imgCompressionThumb) {
        this.imgCompressionThumb = imgCompressionThumb;
    }

    public String getPksFile() {
        return pksFile;
    }

    public void setPksFile(String pksFile) {
        this.pksFile = pksFile;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }
}
