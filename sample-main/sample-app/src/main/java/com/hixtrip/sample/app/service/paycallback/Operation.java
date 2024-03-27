package com.hixtrip.sample.app.service.paycallback;

import com.hixtrip.sample.domain.pay.model.CommandPay;

/**
 * 支付回调操作
 *
 * @author jianhua.luo
 * @date 2024/3/26
 */
public interface Operation {

    /**
     * 支持的状态
     * @return
     */
    Integer supportStatus();

    /**
     * 回调操作
     * @param commandPay 支付结果
     * @return
     */
    boolean operate(CommandPay commandPay);

}
