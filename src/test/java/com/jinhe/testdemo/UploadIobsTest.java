package com.jinhe.testdemo;

import java.io.File;
import java.util.UUID;

import com.jinhe.common.util.Mapper;
import com.jinhe.modules.system.dto.SysUserDTO;
import com.jinhe.modules.system.entity.SysUser;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class UploadIobsTest {
    public static void main(String[] args) throws Exception {
        SysUser sysUser = new SysUser();
        sysUser.setId("aaaa");
        SysUserDTO sysUserDTO = Mapper.ModelToModel(sysUser, SysUserDTO.class);
//        // 文件路径
//        String path = "D://3016548173.jpg";
//        File file = new File(path);
//        CloseableHttpClient httpclient = HttpClients.createDefault();
//        // 请求地址
//        HttpPost httpPost = new HttpPost("http://stg-iobs-upload.pingan.com.cn/upload");
//        // key
//        String key = "" + UUID.randomUUID();
//        String bucket = "icore-ahcs-dmz-stg-pri";
//        String token = "FY2DVC22JdCdJIKI9KdVF2dI90CWV09W:if2M_6dJras223xOv485anUPv3w=:eyJzY29wZSI6Imljb3JlLWFoY3MtZG16LXN0Zy1wcmkiLCJkZWFkbGluZSI6MTU2OTQ4MjUwM30=";
//        MultipartEntityBuilder multipartEntityBuilder = MultipartEntityBuilder.create();
//        multipartEntityBuilder.addTextBody("bucket", bucket);
//        multipartEntityBuilder.addTextBody("token", token);
//        multipartEntityBuilder.addBinaryBody("file", file, ContentType.MULTIPART_FORM_DATA, file.getName());
//        multipartEntityBuilder.addTextBody("key", key);
//        System.err.println("key=" + key);
//        HttpEntity reqEntity = multipartEntityBuilder.build();
//        httpPost.setEntity(reqEntity);
//        try {
//            CloseableHttpResponse response = httpclient.execute(httpPost);
//            try {
//                int code = response.getStatusLine().getStatusCode();
//                String info = response.getStatusLine().getReasonPhrase();
//                if (code != 200) {
//                    throw new Exception(String.valueOf(code) + "&" + info);
//                }
//                System.out.println(response.getStatusLine());
//                HttpEntity resEntity = response.getEntity();
//                if (resEntity != null) {
//                    System.out.println("Response content: " + resEntity.getContent());
//                    System.out.println("Response content length: " + resEntity.getContentLength());
//                }
//                EntityUtils.consume(resEntity);
//            } finally {
//                response.close();
//            }
//        } finally {
//            httpclient.close();
//        }
    }
}