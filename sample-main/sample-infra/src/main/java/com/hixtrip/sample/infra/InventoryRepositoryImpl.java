package com.hixtrip.sample.infra;

import com.hixtrip.sample.domain.inventory.repository.InventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * infra层是domain定义的接口具体的实现
 */
@Component
public class InventoryRepositoryImpl implements InventoryRepository {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    private static String SKU_INVENTORY_KEY = "sku:inventory:%s";

    /**
     * 获取商品库存
     * @param skuId skuId
     * @return 库存数
     */
    @Override
    public Integer getInventory(String skuId) {
        return (Integer) redisTemplate.opsForValue().get(getSkuInventoryKey(skuId));
    }

    /**
     * 占用库存
     * @param skuId
     * @param occupiedQuantity
     * @return
     */
    @Override
    public boolean occupyInventory(String skuId, Integer occupiedQuantity) {
        String script = "if tonumber(redis.call('get', KEYS[1])) >= tonumber(ARGV[1]) then return redis.call('decrby', KEYS[1], ARGV[1]) else return 0 end";
        DefaultRedisScript<Long> redisScript = new DefaultRedisScript();
        redisScript.setScriptText(script);
        redisScript.setResultType(Long.class);

        List<String> keyList = new ArrayList<>();
        keyList.add(getSkuInventoryKey(skuId));
        return redisTemplate.execute(redisScript, keyList, occupiedQuantity) > 0L;
    }

    /**
     * 释放占用库存
     * @param skuId
     * @param occupiedQuantity
     * @return
     */
    @Override
    public void releaseInventory(String skuId, Integer occupiedQuantity) {
        redisTemplate.opsForValue().increment(getSkuInventoryKey(skuId), occupiedQuantity);
    }

    /**
     * redis key 构造
     * @param skuId
     * @return
     */
    public String getSkuInventoryKey(String skuId) {
        return String.format(SKU_INVENTORY_KEY, skuId);
    }
}
