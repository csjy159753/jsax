package com.jinhe.common.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Data
public class Property {
    @Value("${spring.profiles.active}")
    private String springProfilesActive;
    @Value("${server.servlet.context-path}")
    private String contextPath;
}
