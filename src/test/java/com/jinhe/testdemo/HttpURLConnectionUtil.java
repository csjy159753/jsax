package com.jinhe.testdemo;

import javax.activation.MimetypesFileTypeMap;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.UUID;

public class HttpURLConnectionUtil {

    public static void main(String[] args) throws Exception {
        // key
        String url ="";
        String key = "" + UUID.randomUUID();
        System.out.println(key);
        String bucket = "";
        String token = "";
        String path = "D://3016548173.jpg";

        Map<String, String> textList = new HashMap<String, String>();
        Map<String, String> fileList = new HashMap<String, String>();
        textList.put("key", key);
        textList.put("bucket", bucket);
        textList.put("token", token);
        fileList.put("file", path);
        String s = postFileByForm(url, textList, fileList);
        System.out.println("result is:"+s);
    }


    /**
     *
     * @param urlStr  url地址
     * @param textMap 附带信息
     * @param fileMap 文件列表
     * @return 返回json的报文 如果失败，则为空
     */

public static String postFileByForm(String urlStr, Map<String, String> textMap,
            Map<String, String> fileMap){
        String res = "";
        HttpURLConnection conn = null;
        String BOUNDARY = "---------------------------"; //boundary就是request头和上传文件内容的分隔符
        try {
            URL url = new URL(urlStr);
            conn = (HttpURLConnection) url.openConnection();
            conn.setConnectTimeout(5000);
            conn.setReadTimeout(30000);
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setUseCaches(false);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Connection", "Keep-Alive");
            conn.setRequestProperty("User-Agent",
                            "Mozilla/5.0 (Windows; U; Windows NT 6.1; zh-CN; rv:1.9.2.6)");
            conn.setRequestProperty("Content-Type",
                    "multipart/form-data; boundary=" + BOUNDARY);

            OutputStream out = new DataOutputStream(conn.getOutputStream());
            // text
            if (textMap != null) {
                StringBuffer strBuf = new StringBuffer();
                Iterator iter = textMap.entrySet().iterator();
                while (iter.hasNext()) {
                    Map.Entry entry = (Map.Entry) iter.next();
                    String inputName = (String) entry.getKey();
                    String inputValue = (String) entry.getValue();
                    if (inputValue == null) {
                        continue;
                    }
                    strBuf.append("\r\n").append("--").append(BOUNDARY).append(
                            "\r\n");
                    strBuf.append("Content-Disposition: form-data; name=\""
                            + inputName + "\"\r\n\r\n");
                    strBuf.append(inputValue);
                }
                out.write(strBuf.toString().getBytes());
            }

            // file
            if (fileMap != null) {
                Iterator iter = fileMap.entrySet().iterator();
                while (iter.hasNext()) {
                    Map.Entry entry = (Map.Entry) iter.next();
                    String inputName = (String) entry.getKey();
                    String inputValue = (String) entry.getValue();
                    if (inputValue == null) {
                        continue;
                    }
                    File file = new File(inputValue);
                    String filename = file.getName();
                    String contentType = new MimetypesFileTypeMap()
                            .getContentType(file);
                    if (contentType == null || contentType.equals("")) {
                        contentType = "application/octet-stream";
                    }

                    StringBuffer strBuf = new StringBuffer();
                    strBuf.append("\r\n").append("--").append(BOUNDARY).append(
                            "\r\n");
                    strBuf.append("Content-Disposition: form-data; name=\""
                            + inputName + "\"; filename=\"" + filename
                            + "\"\r\n");
                    strBuf.append("Content-Type:" + contentType + "\r\n\r\n");

                    out.write(strBuf.toString().getBytes());

                    DataInputStream in = new DataInputStream(
                            new FileInputStream(file));
                    int bytes = 0;
                    byte[] bufferOut = new byte[1024];
                    while ((bytes = in.read(bufferOut)) != -1) {
                        out.write(bufferOut, 0, bytes);
                    }
                    in.close();
                }
            }

            byte[] endData = ("\r\n--" + BOUNDARY + "--\r\n").getBytes();
            out.write(endData);
            out.flush();
            out.close();

            int statusCode = conn.getResponseCode();
            System.out.println("状态响应码"+statusCode);
            // 读取返回数据
            StringBuffer strBuf = new StringBuffer();
            BufferedReader reader = new BufferedReader(new InputStreamReader(
                    conn.getInputStream()));
            String line = null;
            while ((line = reader.readLine()) != null) {
                strBuf.append(line).append("\n");
            }
            res = strBuf.toString();
            reader.close();
            reader = null;
        } catch (Exception e) {
           //日志处理
            res = "";
        } finally {
            if (conn != null) {
                conn.disconnect();
                conn = null;
            }
        }

        return res;
    }


        /**
     * get请求
     *
     * @param url
     * @param param
     * @return
     */
    public static String get(String url,Map<String, Object> param) {
        StringBuilder builder=new StringBuilder();
        try {
            StringBuilder params=new StringBuilder();
            for(Map.Entry<String, Object> entry:param.entrySet()){
                params.append(entry.getKey());
                params.append("=");
                params.append(entry.getValue().toString());
                params.append("&");
            }
            if(params.length()>0){
                params.deleteCharAt(params.lastIndexOf("&"));
            }
            URL restServiceURL = new URL(url+(params.length()>0 ? "?"+params.toString() : ""));
            HttpURLConnection httpConnection = (HttpURLConnection) restServiceURL.openConnection();
            httpConnection.setRequestMethod("GET");
            httpConnection.setRequestProperty("Accept", "application/json");
            if (httpConnection.getResponseCode() != 200) {
                throw new RuntimeException("HTTP GET Request Failed with Error code : "
                              + httpConnection.getResponseCode());
         }
            InputStream inStrm = httpConnection.getInputStream();
            byte []b=new byte[1024];
            int length=-1;
            while((length=inStrm.read(b))!=-1){
                builder.append(new String(b,0,length));
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return builder.toString();
    }
    /**
     * post    请求
     *
     * @param url
     * @param param
     * @return
     */
    public static String post(String url,Map<String, Object> param) {
        StringBuilder builder=new StringBuilder();
        try {
            StringBuilder params=new StringBuilder();
            for(Map.Entry<String, Object> entry:param.entrySet()){
                params.append(entry.getKey());
                params.append("=");
                params.append(entry.getValue().toString());
                params.append("&");
            }
            if(params.length()>0){
                params.deleteCharAt(params.lastIndexOf("&"));
            }
            URL restServiceURL = new URL(url+(params.length()>0 ? "?"+params.toString() : ""));
            HttpURLConnection httpConnection = (HttpURLConnection) restServiceURL.openConnection();
            httpConnection.setRequestMethod("POST");
            httpConnection.setRequestProperty("Accept", "application/json");
            // 设置是否从httpUrlConnection读入，默认情况下是true;
            httpConnection.setDoInput(true);
            // Post 请求不能使用缓存
            httpConnection.setUseCaches(false);
            if (httpConnection.getResponseCode() != 200) {
                throw new RuntimeException("HTTP POST Request Failed with Error code : "
                              + httpConnection.getResponseCode());
         }
            InputStream inStrm = httpConnection.getInputStream();
            byte []b=new byte[1024];
            int length=-1;
            while((length=inStrm.read(b))!=-1){
                builder.append(new String(b,0,length));
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return builder.toString();
    }

}
