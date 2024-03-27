package com.hixtrip.sample.domain.order.repository;

import com.hixtrip.sample.domain.order.model.Order;
import com.hixtrip.sample.domain.pay.model.CommandPay;

/**
 *
 */
public interface OrderRepository {

    /**
     * 支付成功操作，更新订单状态
     * @param commandPay
     * @return 更新条数
     */
    int orderPaySuccess(CommandPay commandPay);

    /**
     * 支付失败操作，更新订单状态
     * @param commandPay
     * @return 更新条数
     */
    int orderPayFail(CommandPay commandPay);

    /**
     * 保存订单
     * @param order 订单实体
     * @return
     */
    int saveOrder(Order order);
}
