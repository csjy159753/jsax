package com.jinhe.common.config;

import com.jinhe.common.config.TokenInterceptor;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Resource
    private TokenInterceptor tokenInterceptor ;
    public void addInterceptors(InterceptorRegistry registry) {
        List<String> list=new ArrayList<>();
//        list.add("/uploadFile/**");
//        list.add("/upload/**");
        registry.addInterceptor(tokenInterceptor).addPathPatterns("/**").excludePathPatterns(list);
    }
    /**
     * @desc 注册自定义跨域过滤器
     * @author guozhongyao
     * @date 2020/3/30 15:52
     */
//    @Bean
//    public FilterRegistrationBean registerFilter(){
//        FilterRegistrationBean bean = new FilterRegistrationBean();
//        bean.addUrlPatterns("/*");
//        bean.setFilter(new CrosFilter());
//        return bean;
//    }
}