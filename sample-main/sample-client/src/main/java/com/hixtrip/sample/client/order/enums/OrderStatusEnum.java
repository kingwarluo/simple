package com.hixtrip.sample.client.order.enums;

import lombok.AllArgsConstructor;

/**
 * 订单状态枚举
 *
 * @author jianhua.luo
 * @date 2024/3/26
 */
@AllArgsConstructor
public enum OrderStatusEnum {

    WAIT_PAY(0, "待支付"),

    PAY_SUC(1, "支付成功"),

    PAY_FAIL(2, "支付失败");

    private int status;

    private String desc;

    public Integer getStatus() {
        return status;
    }

}
