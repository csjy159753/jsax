package com.jinhe.modules.sys.controller;


import com.alibaba.fastjson.JSON;
import com.jinhe.common.config.HttpUtil;
import com.jinhe.modules.base.UserController;
import com.jinhe.modules.sys.service.IHttpClientService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.http.NameValuePair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author rls
 * @since 2020-11-09
 */
@RestController
@RequestMapping("/sys/redirect")
@Api(tags = "sys")
@Transactional(rollbackFor = Exception.class)
public class SysRedirectController extends UserController {
    private static Logger log = LoggerFactory.getLogger(HttpUtil.class);

    @Value("${redirect.config1.SERVER_HOST}")
    private String SERVER_HOST;
    @Value("${redirect.config1.SERVER_PORT}")
    private Integer SERVER_PORT;

    @Autowired
    private IHttpClientService iHttpClient;
    String fix = "/sys/redirect/general/";

    /**
     * 通用地址配置转发仅支持 GET POST PUT DELETE
     **/
    @ApiOperation(value = "通用地址配置转发重定向", notes = "通用地址配置转发重定向")
    @RequestMapping(value = "/general/**", method = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
    public void general(HttpServletRequest request, HttpServletResponse response) throws Exception {
        HttpUtil httpUtil = new HttpUtil(SERVER_HOST, SERVER_PORT);
        String method = request.getMethod();
        String url = request.getRequestURI();
        String contentType = request.getContentType();
        int base = request.getContextPath().length();
        url = url.replace(fix, "");
        if ("GET".equals(method)) {
            httpUtil.httpGet(url, request, response, new ArrayList<NameValuePair>());
        } else if ("POST".equals(method)) {
            if (contentType != null && contentType.toLowerCase().startsWith("multipart/")) {
                MultipartHttpServletRequest multipartRequest =
                        WebUtils.getNativeRequest(request, MultipartHttpServletRequest.class);
                MultipartFile file = multipartRequest.getFile("file");
                httpUtil.uploadFile(url, file, "file", request, response);
            } else {
                httpUtil.httpPost(url, request, response, new ArrayList<NameValuePair>());
            }
        } else if ("PUT".equals(method)) {
            httpUtil.httpPut(url, request, response, new ArrayList<NameValuePair>());
        } else if ("DELETE".equals(method)) {
            httpUtil.httpDelete(url, request, response, new ArrayList<NameValuePair>());
        }
        response.getOutputStream().flush();
        response.getOutputStream().close();
    }

    /**
     * 自定义转发
     **/
    @ApiOperation(value = "自定义转发重定向", notes = "自定义转发重定向")
    @RequestMapping(value = "/url/**", method = {RequestMethod.GET})
    public JSON urlGet(String url, MultiValueMap<String, Object> parasm) {
        HttpMethod method = HttpMethod.GET;
        iHttpClient.client(url, method, parasm);
        return null;
    }
}

