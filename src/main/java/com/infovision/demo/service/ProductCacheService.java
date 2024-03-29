package com.infovision.demo.service;

import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.infovision.demo.entity.Product;

@Service
public class ProductCacheService {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    public void cacheProduct(Product product, long expirationInSeconds) {
        redisTemplate.opsForHash().put("products", product.getId().toString(), product);
        redisTemplate.expire("products", expirationInSeconds, TimeUnit.SECONDS);
    }

    public Product getProductFromCache(Long productId) {
        return (Product) redisTemplate.opsForHash().get("products", productId.toString());
    }

    public void removeFromCache(Long productId) {
        redisTemplate.opsForHash().delete("products", productId.toString());
    }
}

