package com.hixtrip.sample.app.service;

import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.hixtrip.sample.app.api.OrderService;
import com.hixtrip.sample.app.service.paycallback.PayCallbackProcess;
import com.hixtrip.sample.client.order.dto.CommandOderCreateDTO;
import com.hixtrip.sample.client.order.dto.CommandPayDTO;
import com.hixtrip.sample.client.order.enums.OrderStatusEnum;
import com.hixtrip.sample.domain.commodity.CommodityDomainService;
import com.hixtrip.sample.domain.inventory.InventoryDomainService;
import com.hixtrip.sample.domain.order.OrderDomainService;
import com.hixtrip.sample.domain.order.model.Order;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * app层负责处理request请求，调用领域服务
 */
@Slf4j
@Component
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderDomainService orderDomainService;
    @Autowired
    private CommodityDomainService commodityDomainService;
    @Autowired
    private InventoryDomainService inventoryDomainService;
    @Autowired
    private PayCallbackProcess payCallbackProcess;

    /**
     * 下单
     * @param commandOderCreateDTO 下单对象
     * @return
     */
    @Override
    public Long createOrder(CommandOderCreateDTO commandOderCreateDTO) {
        // 采用redis做库存缓存，下单扣减库存；订单超时时间10分钟，取消后回退库存
        boolean changeInventory = inventoryDomainService.changeInventory(commandOderCreateDTO.getSkuId(), commandOderCreateDTO.getAmount());
        if (!changeInventory) {
            log.info("商品已售罄");
            return 0L;
        }
        Order order = new Order();
        order.setId(IdWorker.getId());
        order.setUserId(commandOderCreateDTO.getUserId());
        order.setSkuId(commandOderCreateDTO.getSkuId());
        order.setAmount(commandOderCreateDTO.getAmount());
        order.setMoney(commodityDomainService.getSkuPrice(order.getSkuId()));
        order.setPayTime(LocalDateTime.now());
        order.setPayStatus(OrderStatusEnum.WAIT_PAY.getStatus());
        orderDomainService.createOrder(order);
        return order.getId();
    }

    /**
     * 订单支付回调操作
     * @param commandPayDTO 回调对象
     * @return 处理结果
     */
    @Override
    public boolean payCallback(CommandPayDTO commandPayDTO) {
        return payCallbackProcess.process(commandPayDTO);
    }
}
