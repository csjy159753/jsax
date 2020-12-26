package com.jinhe.common.config;

import com.alibaba.fastjson.JSONObject;
import com.jinhe.common.util.SSLUtils;
import io.swagger.v3.oas.annotations.servers.Server;
import org.apache.http.*;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

@Service
public class HttpUtil {
    private static Logger log = LoggerFactory.getLogger(HttpUtil.class);

    @Autowired
    private RedirectUtil redirectUtil;

    public void httpGet(String url, HttpServletRequest request, HttpServletResponse response,
                        List<NameValuePair> additionalMap) {
        CloseableHttpClient httpClient = SSLUtils.createSSLClientDefault();
        HttpGet httpGet = new HttpGet(redirectUtil.getHost(url));
        Enumeration<String> headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String headerName = headerNames.nextElement();
            httpGet.addHeader(headerName, request.getHeader(headerName));
        }
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        Enumeration<String> parameterNames = request.getParameterNames();
        while (parameterNames.hasMoreElements()) {
            String parameterName = parameterNames.nextElement();
            for (String value : request.getParameterValues(parameterName)) {
                if (value != null && !value.equals("null")) {
                    params.add(new BasicNameValuePair(parameterName, value));
                }
            }
        }
        params.addAll(additionalMap);
        for (NameValuePair map : additionalMap) {

        }
        HttpResponse httpResponse = null;
        try {
//            UrlEncodedFormEntity entity = new UrlEncodedFormEntity(params, RedirectUtil.SERVER_CHARSET);
//            httpGet.setEntity(entity);
            httpResponse = httpClient.execute(httpGet);
            if (httpResponse != null) {
                HttpEntity responseEntity = httpResponse.getEntity();
                if (responseEntity != null) {
                    log.info(responseEntity.toString());
                    responseEntity.writeTo(response.getOutputStream());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (httpResponse != null) {
            response.setStatus(httpResponse.getStatusLine().getStatusCode());
            //logger.info(httpResponse.toString());
            HeaderIterator headerIterator = httpResponse.headerIterator();
            while (headerIterator.hasNext()) {
                Header header = headerIterator.nextHeader();
                if (header.getName().equals("Content-Type")) {
                    //response.addHeader(header.getName(), header.getValue());
                    response.setHeader(header.getName(), header.getValue());//或许可以解决重定向乱码(好像没影响)
                }
            }
            response.setHeader("Server", "nginx");
        }
    }

    public void httpPost(String url, HttpServletRequest request, HttpServletResponse response,
                         List<NameValuePair> additionalMap) throws IOException {
        CloseableHttpClient httpClient = SSLUtils.createSSLClientDefault();
        HttpPost httpPost = new HttpPost(redirectUtil.getHost(url));

        Enumeration<String> headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String headerName = headerNames.nextElement();
            httpPost.addHeader(headerName, request.getHeader(headerName));
        }
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        Enumeration<String> parameterNames = request.getParameterNames();
        while (parameterNames.hasMoreElements()) {
            String parameterName = parameterNames.nextElement();
            for (String value : request.getParameterValues(parameterName)) {
                if (value != null && !value.equals("null")) {
                    params.add(new BasicNameValuePair(parameterName, value));
                }
            }
        }
        String param = null;
        StringBuilder responseStrBuilder = new StringBuilder();
        try {
            BufferedReader streamReader = new BufferedReader(new InputStreamReader(request.getInputStream(), "UTF-8"));

            String inputStr;
            while ((inputStr = streamReader.readLine()) != null) {
                responseStrBuilder.append(inputStr);
            }

            JSONObject jsonObject = JSONObject.parseObject(responseStrBuilder.toString());
            param = jsonObject.toJSONString();
            System.out.println(param);
        } catch (Exception e) {
            e.printStackTrace();
        }


        for (NameValuePair map : additionalMap) {
            log.info(map.getName() + ":" + map.getValue());
        }
        HttpResponse httpResponse = null;
        try {
//            httpPost.addHeader("Content-Type", "application/json");
            httpPost.setEntity(new StringEntity(param));
            httpPost.removeHeaders("Content-Length");
            httpResponse = httpClient.execute(httpPost);
            if (httpResponse != null) {
                HttpEntity responseEntity = httpResponse.getEntity();
                if (responseEntity != null) {
                    log.info(responseEntity.toString());
                    responseEntity.writeTo(response.getOutputStream());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (httpResponse != null) {
            response.setStatus(httpResponse.getStatusLine().getStatusCode());
            //logger.info(httpResponse.toString());
            HeaderIterator headerIterator = httpResponse.headerIterator();
            while (headerIterator.hasNext()) {
                Header header = headerIterator.nextHeader();
                if (header.getName().equals("Content-Type")) {
                    //response.addHeader(header.getName(), header.getValue());
                    response.setHeader(header.getName(), header.getValue());//或许可以解决重定向乱码(好像没影响)
                }
            }
            response.setHeader("Server", "nginx");
        }
    }

    public void httpPut(String url, HttpServletRequest request, HttpServletResponse response,
                        List<NameValuePair> additionalMap) {
        CloseableHttpClient httpClient = SSLUtils.createSSLClientDefault();

        HttpPut httpPut = new HttpPut(redirectUtil.getHost(url));

        Enumeration<String> headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String headerName = headerNames.nextElement();
            httpPut.addHeader(headerName, request.getHeader(headerName));
        }
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        Enumeration<String> parameterNames = request.getParameterNames();
        while (parameterNames.hasMoreElements()) {
            String parameterName = parameterNames.nextElement();
            for (String value : request.getParameterValues(parameterName)) {
                if (value != null && !value.equals("null")) {
                    params.add(new BasicNameValuePair(parameterName, value));
                }
            }
        }

        String param = null;
        StringBuilder responseStrBuilder = new StringBuilder();
        try {
            BufferedReader streamReader = new BufferedReader(new InputStreamReader(request.getInputStream(), "UTF-8"));

            String inputStr;
            while ((inputStr = streamReader.readLine()) != null) {
                responseStrBuilder.append(inputStr);
            }

            JSONObject jsonObject = JSONObject.parseObject(responseStrBuilder.toString());
            param = jsonObject.toJSONString();
            System.out.println(param);
        } catch (Exception e) {
            e.printStackTrace();
        }


        params.addAll(additionalMap);
        for (NameValuePair map : additionalMap) {
            log.info(map.getName() + ":" + map.getValue());
        }
        HttpResponse httpResponse = null;
        try {
//            UrlEncodedFormEntity entity = new UrlEncodedFormEntity(params, redirectUtil.SERVER_CHARSET);
//            httpPut.setEntity(entity);
            httpPut.setEntity(new StringEntity(param));
            httpPut.removeHeaders("Content-Length");
            httpResponse = httpClient.execute(httpPut);
            if (httpResponse != null) {
                HttpEntity responseEntity = httpResponse.getEntity();
                if (responseEntity != null) {
                    log.info(responseEntity.toString());
                    responseEntity.writeTo(response.getOutputStream());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (httpResponse != null) {
            response.setStatus(httpResponse.getStatusLine().getStatusCode());
            //logger.info(httpResponse.toString());
            HeaderIterator headerIterator = httpResponse.headerIterator();
            while (headerIterator.hasNext()) {
                Header header = headerIterator.nextHeader();
                if (header.getName().equals("Content-Type")) {
                    //response.addHeader(header.getName(), header.getValue());
                    response.setHeader(header.getName(), header.getValue());//或许可以解决重定向乱码(好像没影响)
                }
            }
            response.setHeader("Server", "nginx");
        }
    }

    public void httpDelete(String url, HttpServletRequest request, HttpServletResponse response,
                           List<NameValuePair> additionalMap) {
        String method = request.getMethod();
        CloseableHttpClient httpClient = SSLUtils.createSSLClientDefault();

        HttpDelete httpDelete = new HttpDelete(redirectUtil.getHost(url));

        Enumeration<String> headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String headerName = headerNames.nextElement();
            httpDelete.addHeader(headerName, request.getHeader(headerName));
        }
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        Enumeration<String> parameterNames = request.getParameterNames();
        while (parameterNames.hasMoreElements()) {
            String parameterName = parameterNames.nextElement();
            for (String value : request.getParameterValues(parameterName)) {
                if (value != null && !value.equals("null")) {
                    params.add(new BasicNameValuePair(parameterName, value));
                }
            }
        }
        String param = null;
        StringBuilder responseStrBuilder = new StringBuilder();
        try {
            BufferedReader streamReader = new BufferedReader(new InputStreamReader(request.getInputStream(), "UTF-8"));

            String inputStr;
            while ((inputStr = streamReader.readLine()) != null) {
                responseStrBuilder.append(inputStr);
            }

            JSONObject jsonObject = JSONObject.parseObject(responseStrBuilder.toString());
            param = jsonObject.toJSONString();
            System.out.println(param);
        } catch (Exception e) {
            e.printStackTrace();
        }


        params.addAll(additionalMap);
        for (NameValuePair map : additionalMap) {
            log.info(map.getName() + ":" + map.getValue());
        }
        HttpResponse httpResponse = null;
        try {
//            UrlEncodedFormEntity entity = new UrlEncodedFormEntity(params, RedirectUtil.SERVER_CHARSET);
//            httpDelete.setEntity(entity);
            httpResponse = httpClient.execute(httpDelete);
            if (httpResponse != null) {
                HttpEntity responseEntity = httpResponse.getEntity();
                if (responseEntity != null) {
                    log.info(responseEntity.toString());
                    responseEntity.writeTo(response.getOutputStream());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (httpResponse != null) {
            response.setStatus(httpResponse.getStatusLine().getStatusCode());
            //logger.info(httpResponse.toString());
            HeaderIterator headerIterator = httpResponse.headerIterator();
            while (headerIterator.hasNext()) {
                Header header = headerIterator.nextHeader();
                if (header.getName().equals("Content-Type")) {
                    //response.addHeader(header.getName(), header.getValue());
                    response.setHeader(header.getName(), header.getValue());//或许可以解决重定向乱码(好像没影响)
                }
            }
            response.setHeader("Server", "nginx");
        }
    }

    public String httpRequest(String hostPrefix, String url, List<NameValuePair> params) {
        CloseableHttpClient httpClient = SSLUtils.createSSLClientDefault();
        log.info(url);
        HttpPost httpPost = new HttpPost(redirectUtil.getHost(hostPrefix, url));
        try {
            UrlEncodedFormEntity entity = new UrlEncodedFormEntity(params, redirectUtil.SERVER_CHARSET);
            httpPost.setEntity(entity);
            HttpResponse httpResponse = httpClient.execute(httpPost);
            if (httpResponse != null) {
                HttpEntity responseEntity = httpResponse.getEntity();
                if (responseEntity != null) {
                    String result = EntityUtils.toString(responseEntity, redirectUtil.SERVER_CHARSET);
                    log.info(result);
                    return result;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 使用httpclint 发送文件
     *
     * @param file 上传的文件
     * @return 响应结果
     * @author: qingfeng
     * @date: 2019-05-27
     */
    public void uploadFile(String url, MultipartFile file, String fileParamName, HttpServletRequest request) {

    }
}