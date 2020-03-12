package com.jinhe.modules.pay.service.impl;


import com.jinhe.modules.pay.annotation.PayType;
import com.jinhe.modules.pay.service.WeixinPayService;
import org.springframework.stereotype.Service;

@Service("weixinPayService")
@PayType(value = "weixin")
public class WeixinPayServiceImpl implements WeixinPayService {

    @Override
    public <T> T createOrder(T unifiedOrderRequest) {
        System.out.println("------------------------------weixin");
        return null;
    }
}
