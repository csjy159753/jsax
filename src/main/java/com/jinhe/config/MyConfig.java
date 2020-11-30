package com.jinhe.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.servlet.config.annotation.*;

/**
 * @author Administrator
 */
@Configuration
public class MyConfig implements WebMvcConfigurer {

    @Autowired
    private ConfigProperty configProperty;

    /**
     * 静态资源本地映射
     * img是虚拟路径
     * 映射到本地D盘javaspace下的tomcatpath下
     * 浏览器访问:localhost:8080/img/xxx文件
     *
     * @param registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        String uploadFile = configProperty.GetAbsolutelyUploadFile();
        String upload = configProperty.GetAbsolutelyUpload();
        registry.addResourceHandler("/uploadFile/**").addResourceLocations("file:" + uploadFile + "/");
        registry.addResourceHandler("/upload/**").addResourceLocations("file:" + upload + "/");

    }

    /**
     * 前端跨域
     *
     * @param registry
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        //设置允许跨域的路径
        registry.addMapping("/**")
                //设置允许跨域请求的域名
                .allowedOrigins("*")
                //是否允许证书 不再默认开启
                .allowCredentials(true)
                //设置允许的方法
                .allowedMethods("GET", "POST", "PUT", "DELETE")
                .maxAge(3600);//跨域允许时间
    }


}