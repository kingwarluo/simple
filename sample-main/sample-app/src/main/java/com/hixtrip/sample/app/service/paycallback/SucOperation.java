package com.hixtrip.sample.app.service.paycallback;

import com.hixtrip.sample.client.order.enums.OrderCallBackStatusEnum;
import com.hixtrip.sample.domain.order.OrderDomainService;
import com.hixtrip.sample.domain.pay.model.CommandPay;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 回调支付成功策略
 *
 * @author jianhua.luo
 * @date 2024/3/26
 */
@Service
public class SucOperation implements Operation {

    @Autowired
    private OrderDomainService orderDomainService;

    @Override
    public Integer supportStatus() {
        return OrderCallBackStatusEnum.PAY_SUC.getStatus();
    }

    @Override
    public boolean operate(CommandPay commandPay) {
        return orderDomainService.orderPaySuccess(commandPay) > 0;
    }
}
