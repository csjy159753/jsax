//package com.jinhe.common.config;
//
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.cors.CorsConfiguration;
//import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
//import org.springframework.web.filter.CorsFilter;
//import org.springframework.web.servlet.config.annotation.CorsRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//
///**
// * 实现基本的跨域请求
// *
// * @author linhongcun
// */
//@Configuration
//public class CorsConfigFilter {
//    @Bean
//    public WebMvcConfigurer corsConfigurer() {
//        return new WebMvcConfigurer() {
//            @Override
//            public void addCorsMappings(CorsRegistry registry) {
//                registry.addMapping("/**")
//                        .allowedOrigins("*")
//                        .allowedHeaders("*")
//                        .allowedMethods("*");//允许域名访问，如果*，代表所有域名
//            }
//        };
//    }
//
////    private CorsConfiguration buildConfig() {
////        CorsConfiguration corsConfiguration = new CorsConfiguration();
////        corsConfiguration.addAllowedOrigin("*"); // 允许任何域名使用
////        corsConfiguration.addAllowedHeader("*"); // 允许任何头
////        corsConfiguration.addAllowedMethod("*"); // 允许任何方法（post、get等）
////        return corsConfiguration;
////    }
////
////    @Bean
////    public CorsFilter corsFilter() {
////        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
////        source.registerCorsConfiguration("/**", buildConfig()); // 对接口配置跨域设置
////        return new CorsFilter(source);
////    }
//}