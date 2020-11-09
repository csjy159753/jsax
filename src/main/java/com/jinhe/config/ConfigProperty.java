package com.jinhe.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.File;

/**
 * @author Administrator
 */
@Component
@Data
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
}
