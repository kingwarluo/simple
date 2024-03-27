package com.hixtrip.sample.app.service.paycallback;

import com.hixtrip.sample.client.order.enums.OrderCallBackStatusEnum;
import com.hixtrip.sample.domain.pay.model.CommandPay;
import org.springframework.stereotype.Service;

/**
 * 回调重复支付策略
 *
 * @author jianhua.luo
 * @date 2024/3/26
 */
@Service
public class RepeatOperation implements Operation {

    @Override
    public Integer supportStatus() {
        return OrderCallBackStatusEnum.REPEAT_PAY.getStatus();
    }

    @Override
    public boolean operate(CommandPay commandPay) {
        return true;
    }
}
