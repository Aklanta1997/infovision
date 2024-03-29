package com.infovision.demo.entity;

import java.math.BigDecimal;

import jakarta.persistence.Id;
import lombok.Data;

@Data
public class Product {
	@Id
    private Long id;
    private String name;
    private BigDecimal price;
    // other attributes

    // getters and setters
}

