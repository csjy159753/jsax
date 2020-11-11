package com.jinhe.testdemo;

import net.sf.json.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import java.io.IOException;
import java.net.MalformedURLException;

public class RestfulClient {
    public static void main(String[] args) {
        try {
            HttpClient httpClient=new DefaultHttpClient();
            String url="http://202.119.113.228:8888/1598342632602/get_1ea8bdcd8c454873b93d77a43ba2cb7f";
            JSONObject jsonObject=new JSONObject();
            jsonObject.put("page",1);
            jsonObject.put("pagesize", 10);
            //paramString可参考API说明，paramString传参为JSON格式的String字符串，且需用实力进制toHexString转换后传递
            JSONObject paramString = new JSONObject();
            jsonObject.put("paramString", toHexString(paramString.toString()));
            StringEntity entity = new StringEntity(jsonObject.toString(), "UTF-8");
            entity.setContentEncoding("UTF-8");
            entity.setContentType("application/json");
            HttpPost method = new HttpPost(url);
//            method.setEntity(entity);
            //密钥传递applyId、secretKey
            //method.setHeader("serviceNum", "******");
            method.setHeader("applyId", "20140622_1593682202102");
            method.setHeader("secretKey", "d4b471f3-6ca2-4609-845d-b38f1b4778ec");
            HttpResponse result = httpClient.execute(method);
            HttpEntity ret = result.getEntity();
            String retStr = EntityUtils.toString(ret);
            System.out.println("输出结果：\n"+retStr);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static String toHexString(String s){
        String str="";
        for (int i=0;i < s.length();i++){
            int ch = (int)s.charAt(i);
            str = str + Integer.toHexString(ch);
        }
        return str;
    }
}