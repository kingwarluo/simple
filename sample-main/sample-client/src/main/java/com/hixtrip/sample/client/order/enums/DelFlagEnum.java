package com.hixtrip.sample.client.order.enums;

import lombok.AllArgsConstructor;

/**
 * 删除状态枚举
 *
 * @author jianhua.luo
 * @date 2024/3/26
 */
@AllArgsConstructor
public enum DelFlagEnum {

    EXIST(0, "未删除"),

    DELETED(1, "已删除");

    private int code;

    private String desc;

    public Integer getCode() {
        return code;
    }

}
