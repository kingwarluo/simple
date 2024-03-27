package com.hixtrip.sample.domain.pay.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CommandPay {

    /**
     * 订单id
     */
    private Long orderId;

    /**
     * 支付状态 (1-支付成功 2-支付失败 3-重复支付)
     */
    private Integer payStatus;
}