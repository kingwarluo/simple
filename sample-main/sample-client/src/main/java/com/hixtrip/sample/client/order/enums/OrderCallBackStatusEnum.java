package com.hixtrip.sample.client.order.enums;

import lombok.AllArgsConstructor;

/**
 * 订单回调状态枚举
 *
 * @author jianhua.luo
 * @date 2024/3/26
 */
@AllArgsConstructor
public enum OrderCallBackStatusEnum {

    PAY_SUC(1, "支付成功"),

    PAY_FAIL(2, "支付失败"),

    REPEAT_PAY(3, "重复支付");

    private int status;

    private String desc;

    public Integer getStatus() {
        return status;
    }

}
