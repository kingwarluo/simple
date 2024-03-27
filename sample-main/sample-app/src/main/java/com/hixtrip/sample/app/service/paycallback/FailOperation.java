package com.hixtrip.sample.app.service.paycallback;

import com.hixtrip.sample.client.order.enums.OrderCallBackStatusEnum;
import com.hixtrip.sample.domain.order.OrderDomainService;
import com.hixtrip.sample.domain.pay.model.CommandPay;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 回调支付失败策略
 *
 * @author jianhua.luo
 * @date 2024/3/26
 */
@Service
public class FailOperation implements Operation {

    @Autowired
    private OrderDomainService orderDomainService;

    @Override
    public Integer supportStatus() {
        return OrderCallBackStatusEnum.PAY_FAIL.getStatus();
    }

    @Override
    public boolean operate(CommandPay commandPay) {
        return orderDomainService.orderPayFail(commandPay) > 0;
    }
}
