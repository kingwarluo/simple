package com.hixtrip.sample.infra.db.mapper;

import com.hixtrip.sample.domain.order.model.Order;
import com.hixtrip.sample.domain.pay.model.CommandPay;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface OrderMapper {

    /**
     * 支付成功操作，更新订单状态
     * @param commandPay
     * @return
     */
    int orderPaySuccess(@Param("commandPay") CommandPay commandPay);

    /**
     * 支付失败操作，更新订单状态
     * @param commandPay
     * @return
     */
    int orderPayFail(@Param("commandPay") CommandPay commandPay);

    /**
     * 保存订单
     * @param order 订单实体
     * @return
     */
    int saveOrder(@Param("order") Order order);
}
