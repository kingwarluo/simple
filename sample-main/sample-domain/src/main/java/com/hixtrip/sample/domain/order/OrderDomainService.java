package com.hixtrip.sample.domain.order;

import com.hixtrip.sample.domain.order.model.Order;
import com.hixtrip.sample.domain.order.repository.OrderRepository;
import com.hixtrip.sample.domain.pay.model.CommandPay;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * 订单领域服务
 */
@Component
public class OrderDomainService {

    @Autowired
    private OrderRepository orderRepository;

    private Integer NOT_DELETE = 0;

    /**
     * 创建待付款订单
     */
    public void createOrder(Order order) {
        LocalDateTime now = LocalDateTime.now();
        order.setDelFlag(NOT_DELETE);
        order.setCreateBy(order.getUserId());
        order.setCreateTime(now);
        order.setUpdateBy(order.getUserId());
        order.setUpdateTime(now);
        orderRepository.saveOrder(order);
    }

    /**
     * 待付款订单支付成功
     * @param commandPay
     */
    public int orderPaySuccess(CommandPay commandPay) {
        return orderRepository.orderPaySuccess(commandPay);
    }

    /**
     * 待付款订单支付失败
     * @param commandPay
     */
    public int orderPayFail(CommandPay commandPay) {
        return orderRepository.orderPayFail(commandPay);
    }
}
