package com.hixtrip.sample.domain.inventory.repository;

/**
 *
 */
public interface InventoryRepository {

    /**
     * 获取商品库存
     * @param skuId skuId
     * @return 库存数
     */
    Integer getInventory(String skuId);

    /**
     * 扣减库存
     * @param skuId
     * @param occupiedQuantity
     * @return 是否扣减成功
     */
    boolean occupyInventory(String skuId, Integer occupiedQuantity);
}
