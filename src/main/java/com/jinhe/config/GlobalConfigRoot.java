package com.jinhe.config;

import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.File;

/**
 * @ClassName: GlobalConfig
 * @Description: TODO
 * @Author: liujianfu
 * @Date: 2020/08/04 19:47:28
 * @Version: V1.0
 **/
@Configuration
public class GlobalConfigRoot {
    @Bean
    public WebServerFactoryCustomizer<TomcatServletWebServerFactory> customizer() {
         return (factory) -> {
            factory.addContextCustomizers((context) -> {
                        //模块中webapp相对路径
                        String relativePath = "Api/src/main/resources/static";
                        File docBaseFile = new File(relativePath);
                        // 如果路径不存在，则把这个路径加入进去
                        if (docBaseFile.exists()) {
                            context.setDocBase(docBaseFile.getAbsolutePath());
                        }
                    }
            );
        };
    }
}