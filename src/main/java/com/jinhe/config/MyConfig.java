package com.jinhe.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MyConfig implements WebMvcConfigurer {

    @Autowired
    private ConfigProperty configProperty;
    /**
     * 虚拟路径配置
     *
     * @param registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        String uploadFile = configProperty.GetAbsolutelyUploadFile();
        String upload = configProperty.GetAbsolutelyUpload();
        registry.addResourceHandler("/uploadFile/**").addResourceLocations("file:" + uploadFile + "/");
        registry.addResourceHandler("/upload/**").addResourceLocations("file:" + upload + "/");
        WebMvcConfigurer.super.addResourceHandlers(registry);
    }

}
