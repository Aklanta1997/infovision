package com.infovision.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.infovision.demo.entity.Product;
import com.infovision.demo.repository.ProductRepository;

@Service
public class ProductService {

    @Autowired
    private ProductCacheService productCacheService;

    @Autowired
    private ProductRepository productRepository;

    public Product getProductById(Long productId, long expirationInSeconds) {
        Product product = productCacheService.getProductFromCache(productId);
        if (product == null) {
            product = productRepository.findById(productId).orElse(null);
            if (product != null) {
                productCacheService.cacheProduct(product, expirationInSeconds);
            }
        }
        return product;
    }
}

