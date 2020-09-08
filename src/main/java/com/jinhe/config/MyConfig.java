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


    /*
     * 静态资源本地映射
     * img是虚拟路径
     * 映射到本地D盘javaspace下的tomcatpath下
     * 浏览器访问:localhost:8080/img/xxx文件
     * */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        String uploadFile = configProperty.GetAbsolutelyUploadFile();
        String upload = configProperty.GetAbsolutelyUpload();
        registry.addResourceHandler("/uploadFile/**").addResourceLocations("file:" + uploadFile + "/");
        registry.addResourceHandler("/upload/**").addResourceLocations("file:" + upload + "/");
    }

    //前端跨域
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")//设置允许跨域的路径
                .allowedOrigins("*")//设置允许跨域请求的域名
                .allowCredentials(true)//是否允许证书 不再默认开启
                .allowedMethods("GET", "POST", "PUT", "DELETE")//设置允许的方法
                .maxAge(3600);//跨域允许时间
    }


}
