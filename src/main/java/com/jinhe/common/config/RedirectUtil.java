package com.jinhe.common.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

@Component
@ConfigurationProperties(prefix = "redirect.config")
public class RedirectUtil {

    public String SERVER_HOST = "localhost";
    public int SERVER_PORT = 8085;
    public String SERVER_PROTOCOL = "http";
    public String SERVER_CHARSET = "UTF-8";

    public String getHost(String hostPrefix) {
        return getHost(hostPrefix, "");
    }

    public String getHost(String hostPrefix, String url) {
        String host = SERVER_HOST;
        if (hostPrefix != null && !hostPrefix.isEmpty()) {
            url = "/" + hostPrefix;//例如: / + image + /image/report/getReportList
        }
        try {
            return new URL(SERVER_PROTOCOL, host, SERVER_PORT, url).toString();
        } catch (MalformedURLException e) {
            e.printStackTrace();
            return SERVER_PROTOCOL + "://" + host + ":" + SERVER_PORT + "url";
        }
    }
}