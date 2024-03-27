package com.hixtrip.sample.infra;

import com.hixtrip.sample.domain.order.model.Order;
import com.hixtrip.sample.domain.order.repository.OrderRepository;
import com.hixtrip.sample.domain.pay.model.CommandPay;
import com.hixtrip.sample.infra.db.mapper.OrderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * infra层是domain定义的接口具体的实现
 */
@Component
public class OrderRepositoryImpl implements OrderRepository {

    @Autowired
    private OrderMapper orderMapper;

    /**
     * 支付成功操作，更新订单状态
     * @param commandPay
     * @return 更新成功条数
     */
    @Override
    public int orderPaySuccess(CommandPay commandPay) {
        return orderMapper.orderPaySuccess(commandPay);
    }

    /**
     * 支付失败操作，更新订单状态
     * @param commandPay
     * @return 更新条数
     */
    @Override
    public int orderPayFail(CommandPay commandPay) {
        return orderMapper.orderPayFail(commandPay);
    }

    @Override
    public int saveOrder(Order order) {
        return orderMapper.saveOrder(order);
    }
}
