package com.jinhe.modules.pay.service.impl;


import com.jinhe.modules.pay.annotation.PayType;
import com.jinhe.modules.pay.service.AlipayPayService;
import org.springframework.stereotype.Service;

@Service("alipayPayService")
@PayType(value = "alipay")
public class AlipayPayServiceImpl implements AlipayPayService {

    @Override
    public <T> T createOrder(T unifiedOrderRequest) {
        System.out.println("------------------------------alipay");
        return null;
    }
}
