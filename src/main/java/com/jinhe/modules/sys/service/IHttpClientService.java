package com.jinhe.modules.sys.service;

import com.alibaba.fastjson.JSON;
import org.springframework.http.HttpMethod;
import org.springframework.util.MultiValueMap;

/**
 * @author Administrator
 */
public interface IHttpClientService {
      JSON client(String url, HttpMethod method, MultiValueMap<String,Object> parasm);
}
