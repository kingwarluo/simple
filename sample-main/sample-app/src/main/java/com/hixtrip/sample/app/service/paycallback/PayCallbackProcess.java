package com.hixtrip.sample.app.service.paycallback;

import com.hixtrip.sample.app.convertor.PayConvertor;
import com.hixtrip.sample.client.order.dto.CommandPayDTO;
import com.hixtrip.sample.domain.pay.PayDomainService;
import com.hixtrip.sample.domain.pay.model.CommandPay;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 回调处理流程
 *
 * @author jianhua.luo
 * @date 2024/3/26
 */
@Service
public class PayCallbackProcess {

    @Autowired
    private PayDomainService payDomainService;

    @Autowired
    private List<Operation> operationList;

    /**
     * 回调流程处理
     *
     * @param commandPayDTO
     * @return
     */
    public boolean process(CommandPayDTO commandPayDTO) {
        CommandPay commandPay = PayConvertor.INSTANCE.payDTOToPay(commandPayDTO);
        // 保存回调记录
        payDomainService.payRecord(commandPay);
        // 根据回调状态->处理回调业务逻辑
        for (Operation operation : operationList) {
            if (operation.supportStatus().equals(commandPay.getPayStatus())) {
                return operation.operate(commandPay);
            }
        }
        return false;
    }

}
