package com.jinhe.modules.pay.service;

/**
 *
 */
public interface PayService {

      /**
   * 调用统一下单接口，并组装生成支付所需参数对象.
   *
   * @param <T>
   * @param unifiedOrderRequest 统一下单请求参数
   * @return 返回
   * @throws
   */
  <T> T createOrder(T unifiedOrderRequest);
}
