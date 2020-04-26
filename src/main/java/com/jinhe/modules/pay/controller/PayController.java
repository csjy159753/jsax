package com.jinhe.modules.pay.controller;

import com.jinhe.common.util.SpringContextUtils;
import com.jinhe.modules.pay.entity.UnifiedOrderRequest;
import com.jinhe.modules.pay.service.PayService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping("/pay")
public class PayController {

   @RequestMapping("/{payType}")
   public String routPay(@PathVariable String payType){
       UnifiedOrderRequest unifiedOrderRequest = new UnifiedOrderRequest();
       if(PayStratey.payTypeHashMap.containsKey(payType)){
           Class clazz = PayStratey.payTypeHashMap.get(payType);
           PayService payService = (PayService)SpringContextUtils.getBean(clazz);
           payService.createOrder(unifiedOrderRequest);
       }else {
           throw new IllegalArgumentException("not found payType for type");
       }

      return payType;
    }




}
