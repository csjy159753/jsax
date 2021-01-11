package com.jinhe.modules.allowApi;

import com.jinhe.common.util.Result;
import com.jinhe.common.util.ResultUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpMethod;
import org.springframework.http.client.ClientHttpRequest;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * 微信获取唯一唯一凭证
 *
 * @author Administrator
 */
@RestController
@RequestMapping("/allowApi/WeChat")
@Api(tags = "allowApi")
@Transactional(rollbackFor = Exception.class)
public class WeChatController {

    @ApiOperation(value = "获取openid11111aaaa", notes = "获取openid")
    @GetMapping(value = "JsCode2Session")
    public Result JsCode2Session(String appid, String secret, String jsCode) {
        String str = "";
        String grantType = "authorization_code";
        try {
            URI uri = new URI("https://api.weixin.qq.com/sns/jscode2session?appid=" + appid + "&secret=" + secret + "&js_code=" + jsCode + "&grant_type=" + grantType + "");
            SimpleClientHttpRequestFactory schr = new SimpleClientHttpRequestFactory();
            ClientHttpRequest chr = schr.createRequest(uri, HttpMethod.POST);
            ClientHttpResponse res = chr.execute();
            //获得返回数据,注意这里是个流
            InputStream is = res.getBody();
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader br = new BufferedReader(isr);
            while ((str = br.readLine()) != null) {
                return ResultUtil.success(str);
            }
        } catch (URISyntaxException e1) {
            e1.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ResultUtil.success(str);
    }
}
