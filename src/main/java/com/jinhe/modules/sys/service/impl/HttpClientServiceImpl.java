package com.jinhe.modules.sys.service.impl;

import com.alibaba.fastjson.JSON;
import com.jinhe.modules.sys.service.IHttpClientService;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
@Service
@Transactional(rollbackFor = Exception.class)
public class HttpClientServiceImpl implements IHttpClientService {
    @Override
    public JSON client(String url, HttpMethod method, MultiValueMap<String, Object> parasm) {
        RestTemplate template = new RestTemplate();
        ResponseEntity<JSON> response = template.getForEntity(url, JSON.class, method, parasm);
        return response.getBody();
    }
}
