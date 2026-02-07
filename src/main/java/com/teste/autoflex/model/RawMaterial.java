package com.teste.autoflex.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
public class RawMaterial {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long code;

    private String name;

    private Integer stockQuantity;

    @OneToMany(mappedBy = "rawMaterial")
    private List<ProductRawMaterial> products;

    public Long getCode() {
        return code;
    }

    public void setCode(Long code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getStockQuantity() {
        return stockQuantity;
    }

    public void setStockQuantity(Integer stockQuantity) {
        this.stockQuantity = stockQuantity;
    }

    public List<ProductRawMaterial> getProducts() {
        return products;
    }

    public void setProducts(List<ProductRawMaterial> products) {
        this.products = products;
    }
}
