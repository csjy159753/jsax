package com.jinhe.common.config;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.jinhe.common.util.SSLUtils;
import org.apache.http.*;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

public class HttpUtil {
    private static Logger log = LoggerFactory.getLogger(HttpUtil.class);
    public String SERVER_HOST = "localhost";
    public int SERVER_PORT = 8085;
    public String SERVER_PROTOCOL = "http";
    public String SERVER_CHARSET = "UTF-8";

    private HttpUtil() {
    }

    public HttpUtil(String SERVER_HOST, int SERVER_PORT) {
        this.SERVER_HOST = SERVER_HOST;
        this.SERVER_PORT = SERVER_PORT;
    }

    public HttpUtil(String SERVER_PROTOCOL, String SERVER_HOST, int SERVER_PORT) {
        this.SERVER_HOST = SERVER_HOST;
        this.SERVER_PORT = SERVER_PORT;
        this.SERVER_PROTOCOL = SERVER_PROTOCOL;
    }

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


    public void httpGet(String url, HttpServletRequest request, HttpServletResponse response,
                        List<NameValuePair> additionalMap) throws URISyntaxException {
        CloseableHttpClient httpClient = SSLUtils.createSSLClientDefault();
        URIBuilder URIBuilder = new URIBuilder(getHost(url));


        List<NameValuePair> params = new ArrayList<NameValuePair>();
        Enumeration<String> parameterNames = request.getParameterNames();
        while (parameterNames.hasMoreElements()) {
            String parameterName = parameterNames.nextElement();
            for (String value : request.getParameterValues(parameterName)) {
                if (value != null && !value.equals("null")) {
                    params.add(new BasicNameValuePair(parameterName, value));
                    URIBuilder.addParameter(parameterName, value);
                }
            }
        }
        params.addAll(additionalMap);
        for (NameValuePair map : additionalMap) {

        }
        HttpGet httpGet = new HttpGet(URIBuilder.build());
        Enumeration<String> headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String headerName = headerNames.nextElement();
            httpGet.addHeader(headerName, request.getHeader(headerName));
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

                    for (Header header : httpResponse.getAllHeaders()) {
                        response.addHeader(header.getName(), header.getValue());
                    }
                    responseEntity.writeTo(response.getOutputStream());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (httpResponse != null) {
            response.setStatus(httpResponse.getStatusLine().getStatusCode());
            HeaderIterator headerIterator = httpResponse.headerIterator();
            while (headerIterator.hasNext()) {
                Header header = headerIterator.nextHeader();
                if (header.getName().equals("Content-Type")) {
                    response.setHeader(header.getName(), header.getValue());//或许可以解决重定向乱码(好像没影响)
                }
            }
            response.setHeader("Server", "nginx");

        }
    }

    public void httpPost(String url, HttpServletRequest request, HttpServletResponse response,
                         List<NameValuePair> additionalMap) throws IOException {
        CloseableHttpClient httpClient = SSLUtils.createSSLClientDefault();
        HttpPost httpPost = new HttpPost(getHost(url));

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
            if (responseStrBuilder.toString().trim().startsWith("[")) {
                JSONArray jsonObject = JSONObject.parseArray(responseStrBuilder.toString());
                param = jsonObject.toJSONString();
                System.out.println(param);
            } else {
                JSONObject jsonObject = JSONObject.parseObject(responseStrBuilder.toString());
                param = jsonObject.toJSONString();
                System.out.println(param);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


        for (NameValuePair map : additionalMap) {
            log.info(map.getName() + ":" + map.getValue());
        }
        HttpResponse httpResponse = null;
        try {
            httpPost.setEntity(new StringEntity(param, Charset.forName("UTF-8")));
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
                    response.setHeader(header.getName(), header.getValue());//或许可以解决重定向乱码(好像没影响)
                }
            }
            response.setHeader("Server", "nginx");
        }
    }

    public void httpPut(String url, HttpServletRequest request, HttpServletResponse response,
                        List<NameValuePair> additionalMap) {
        CloseableHttpClient httpClient = SSLUtils.createSSLClientDefault();

        HttpPut httpPut = new HttpPut(getHost(url));

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

            if (responseStrBuilder.toString().trim().startsWith("[")) {
                JSONArray jsonObject = JSONObject.parseArray(responseStrBuilder.toString());
                param = jsonObject.toJSONString();
                System.out.println(param);
            } else {
                JSONObject jsonObject = JSONObject.parseObject(responseStrBuilder.toString());
                param = jsonObject.toJSONString();
                System.out.println(param);
            }
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
            httpPut.setEntity(new StringEntity(param, Charset.forName("UTF-8")));
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

        HttpDelete httpDelete = new HttpDelete(getHost(url) + "?" + request.getQueryString());

        Enumeration<String> headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String headerName = headerNames.nextElement();
            httpDelete.addHeader(headerName, request.getHeader(headerName));
        }
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        List<HttpParams> lmp = new ArrayList<>();
        Enumeration<String> parameterNames = request.getParameterNames();
        while (parameterNames.hasMoreElements()) {
            String parameterName = parameterNames.nextElement();
            for (String value : request.getParameterValues(parameterName)) {
                if (value != null && !value.equals("null")) {
                    params.add(new BasicNameValuePair(parameterName, value));
                    httpDelete.getParams().setParameter(parameterName, value);
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
            if (jsonObject != null) {
                param = jsonObject.toJSONString();
            }

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
            UrlEncodedFormEntity entity = new UrlEncodedFormEntity(params, SERVER_CHARSET);

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
        HttpPost httpPost = new HttpPost(getHost(hostPrefix, url));
        try {
            UrlEncodedFormEntity entity = new UrlEncodedFormEntity(params, SERVER_CHARSET);
            httpPost.setEntity(entity);
            HttpResponse httpResponse = httpClient.execute(httpPost);
            if (httpResponse != null) {
                HttpEntity responseEntity = httpResponse.getEntity();
                if (responseEntity != null) {
                    String result = EntityUtils.toString(responseEntity, SERVER_CHARSET);
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
    public void uploadFile(String url, MultipartFile file, String fileParamName, HttpServletRequest request, HttpServletResponse response) {
        try {
            //转换成文件流
            InputStream is = file.getInputStream();

            //接收文件的服务器地址
            String uploadURL = getHost(url);

            //创建HttpClient
            CloseableHttpClient httpClient = HttpClients.createDefault();
            HttpPost httpPost = new HttpPost(uploadURL);
            MultipartEntityBuilder builder = MultipartEntityBuilder.create();
            /*绑定文件参数，传入文件流和contenttype，此处也可以继续添加其他formdata参数*/
            builder.addBinaryBody("filePath", is, ContentType.MULTIPART_FORM_DATA, file.getOriginalFilename());
            HttpEntity entity = builder.build();
            httpPost.setEntity(entity);

            //执行提交
            HttpResponse httpResponse = httpClient.execute(httpPost);
            HttpEntity responseEntity = httpResponse.getEntity();

            if (responseEntity != null) {
                log.info(responseEntity.toString());
                responseEntity.writeTo(response.getOutputStream());
            }
            if (is != null) {
                is.close();
            }
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
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }
}