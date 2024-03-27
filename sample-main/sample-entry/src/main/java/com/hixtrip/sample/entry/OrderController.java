package com.hixtrip.sample.entry;

import com.hixtrip.sample.app.api.OrderService;
import com.hixtrip.sample.app.service.paycallback.PayCallbackProcess;
import com.hixtrip.sample.client.order.dto.CommandOderCreateDTO;
import com.hixtrip.sample.client.order.dto.CommandPayDTO;
import com.hixtrip.sample.entry.common.ApiResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * todo 这是你要实现的
 */
@RestController
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private PayCallbackProcess payCallbackProcess;

    /**
     * 下单接口
     *
     * @param commandOderCreateDTO 入参对象
     * @return 订单id
     */
    @PostMapping(path = "/command/order/create")
    public ApiResult<Long> order(@RequestBody CommandOderCreateDTO commandOderCreateDTO) {
        //登录信息可以在这里模拟
        Long userId = 1L;
        commandOderCreateDTO.setUserId(userId);
        return ApiResult.ok(orderService.createOrder(commandOderCreateDTO));
    }

    /**
     * 创建订单后，支付结果的回调通知
     * 【中、高级要求】需要使用策略模式处理至少三种场景：支付成功、支付失败、重复支付(自行设计回调报文进行重复判定)
     *
     * @param commandPayDTO 入参对象
     * @return 是否成功
     */
    @PostMapping(path = "/command/order/pay/callback")
    public ApiResult<Boolean> payCallback(@RequestBody CommandPayDTO commandPayDTO) {
        return ApiResult.ok(payCallbackProcess.process(commandPayDTO));
    }

}
