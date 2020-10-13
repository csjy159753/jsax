package com.jinhe.common.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Data
public class Property {
    @Value("${spring.profiles.active}")
    private String springProfilesActive;
    @Value("${server.servlet.context-path}")
    private String contextPath;
    @Value("#{'${jinhe.filter:}'.empty ? null : '${jinhe.filter:}'.split(',')}")
    private List<String> filters;
}
