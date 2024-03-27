package com.hixtrip.sample.app.api;

import com.hixtrip.sample.client.order.dto.CommandOderCreateDTO;
import com.hixtrip.sample.client.order.dto.CommandPayDTO;

/**
 * 订单的service层
 */
public interface OrderService {

    /**
     * 下单
     * @param commandOderCreateDTO 下单对象
     * @return
     */
    Long createOrder(CommandOderCreateDTO commandOderCreateDTO);

    /**
     * 订单支付回调操作
     * @param commandPayDTO 回调对象
     * @return 处理结果
     */
    boolean payCallback(CommandPayDTO commandPayDTO);

}
