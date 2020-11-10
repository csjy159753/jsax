package com.jinhe.common.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author Administrator
 */
@Data
@ConfigurationProperties(prefix = "jinhe")
@Component
public class ConfigModules {
    private Map<String, String> modules;
}
