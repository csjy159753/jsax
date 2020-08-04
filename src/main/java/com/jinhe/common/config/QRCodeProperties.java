package com.jinhe.common.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Data
@Configuration
@PropertySource("classpath:config/QRCode.properties")
@ConfigurationProperties("qrcode")
public class QRCodeProperties {

    //logo所在路径
    private String logoPath;
    //最终QRCode存放路径
    private String Path;
    //QRCode路径用于与logo合成
    private String tempPath;
    //zip所在文件夹的位置
    private String zipPath;
}
