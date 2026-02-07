package com.teste.autoflex.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long code;

    private String name;

    private Double value;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ProductRawMaterial> rawMaterials = new ArrayList<>();

    public void addRawMaterial(RawMaterial rawMaterial, Integer quantity) {
        ProductRawMaterial productRawMaterial = new ProductRawMaterial();
        productRawMaterial.setProduct(this);
        productRawMaterial.setRawMaterial(rawMaterial);
        productRawMaterial.setQuantityRequired(quantity);
        rawMaterials.add(productRawMaterial);
    }

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

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public List<ProductRawMaterial> getRawMaterials() {
        return rawMaterials;
    }

    public void setRawMaterials(List<ProductRawMaterial> rawMaterials) {
        this.rawMaterials = rawMaterials;
    }
}
