package com.jinhe.modules.comm.controller;

import com.google.zxing.Result;
import com.jinhe.common.util.QRCodeUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 二维码调用前端控制器
 */
@RequestMapping("/Qrcode")
@RestController
public class QrcodeController {

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

}
