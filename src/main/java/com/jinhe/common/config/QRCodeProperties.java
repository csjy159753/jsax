package com.jinhe.common.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Data
@Configuration
@PropertySource("classpath:config/QRCode.properties")
@ConfigurationProperties(prefix = "qrcode")
public class QRCodeProperties {

    //logo所在路径
    private String logopath;
    //最终QRCode存放路径
    private String path;
    //QRCode路径用于与logo合成
    private String temppath;
}
