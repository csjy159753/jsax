package com.jinhe.modules.pay.service;



public abstract class AbstractPayService {
    abstract public <T> T createOrder(T unifiedOrderRequest);
}
