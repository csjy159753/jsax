package com.jinhe.common.config;

import com.jinhe.common.config.TokenInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.servlet.config.annotation.*;

import javax.annotation.Resource;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Resource
    private TokenInterceptor tokenInterceptor;
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("forward:/views/default");
        registry.setOrder(Ordered.HIGHEST_PRECEDENCE);
    }
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(tokenInterceptor).addPathPatterns("/**").excludePathPatterns("/resources/**",
                "/main/uscc/**", "/lang/**", "/**/js/**", "/**/css/**", "/**/*.xml", "/**/bootstrap/**", "/**/plugins/**",
                "/**/404", "/**/500", "/**/error", "/webcontent/**", "/files/**", "/main/mobile/**", "/mobile/**");
    }

    /**
     * 自定义静态资源映射
     */
    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**").addResourceLocations("classpath:/META-INF/resources/",
                "classpath:/resources/", "classpath:/static/", "classpath:/public/");
        registry.addResourceHandler("/views/**").addResourceLocations("classpath:/static/main/");
        // 指到 webapp 目录下
        registry.addResourceHandler("/webcontent/**").addResourceLocations("/webcontent/");
    }

}