package com.jinhe.modules.demo.controller;

import com.google.zxing.Result;
import com.jinhe.common.config.QRCodeProperties;
import com.jinhe.common.util.QRCodeUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 二维码调用前端控制器
 */

@RestController
@CrossOrigin
@Api(tags = {"demo"})
@RequestMapping("/demo/qrcode")
public class QrcodeController {
    @Autowired
    QRCodeProperties qrCodeProperties;

    /**
     * 生成二维码
     */
    @GetMapping
    public void productcode() {
        QRCodeUtil.onlineCodeCreate("", "",500,"");
    }


    /**
     * 解析二维码
     */
    @GetMapping("/test")
    public void analysiscode() {
        Result result = QRCodeUtil.onlineCodeAnalyze("");
        System.err.println("二维码解析内容："+result.toString());
    }
    @ApiOperation(value="二维码下载", notes="下载二维码信息")
    @GetMapping("downloadQrCode")
    public void downloadQrCode(List<String> contentList, HttpServletResponse response){
        String path = qrCodeProperties.getPath();
        String zipPath = qrCodeProperties.getZipPath();
        for (String s : contentList) {
            boolean b = QRCodeUtil.onlineCodeCreate(s, path, 250, null);
            if (b = false){

            }
        }

    }

}
