package com.teste.autoflex.service;

import com.teste.autoflex.dto.product.ProductRequestDTO;
import com.teste.autoflex.dto.product.ProductResponseDTO;
import com.teste.autoflex.dto.raw_material.RawMaterialItemDTO;
import com.teste.autoflex.exceptions.BusinessException;
import com.teste.autoflex.model.Product;
import com.teste.autoflex.model.RawMaterial;
import com.teste.autoflex.repository.ProductRepository;
import com.teste.autoflex.repository.RawMaterialRepository;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service

public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private RawMaterialRepository rawMaterialRepository;

    @Transactional
    public ProductResponseDTO register(ProductRequestDTO productDto) {
        Product product = new Product();
        product.setName(productDto.name());
        product.setValue(productDto.value());

        if (productDto.rawMaterials() != null){
            for (RawMaterialItemDTO item : productDto.rawMaterials()){
                RawMaterial rawMaterial = rawMaterialRepository.findByCode(item.code())
                        .orElseThrow(() -> new BusinessException("Material not found: " + item.code()));

                product.addRawMaterial(rawMaterial, item.quantity());
            }
        } else {
            throw new BusinessException("Product must have at least one raw material.");
        }

        productRepository.save(product);
        return new ProductResponseDTO(product.getCode(), product.getName(), product.getValue());

    }

    @Transactional
    public ProductResponseDTO update (Long codeToUpdate, ProductRequestDTO dto){
        var existingProduct = productRepository.findById(codeToUpdate)
                .orElseThrow(() -> new BusinessException("Product not found."));

        if (dto.name() != null && !dto.name().isBlank()){
            String normalizedName = dto.name().trim();
            existingProduct.setName(normalizedName);
        }

        if (dto.value() != null){
            existingProduct.setValue(dto.value());
        }

        if(dto.rawMaterials() != null){
            existingProduct.getRawMaterials().clear();

            for (RawMaterialItemDTO item : dto.rawMaterials()){
                RawMaterial rawMaterial = rawMaterialRepository.findByCode(item.code())
                        .orElseThrow(() -> new BusinessException("Material not found: " + item.code()));

                existingProduct.addRawMaterial(rawMaterial, item.quantity());
            }

        }

        productRepository.save(existingProduct);
        return new ProductResponseDTO(existingProduct.getCode(), existingProduct.getName(), existingProduct.getValue());

    }

    @Transactional
    public void delete (Long code){
        var existingProduct = productRepository.findById(code)
                .orElseThrow(() -> new IllegalArgumentException("Product not found."));

        productRepository.delete(existingProduct);
    }

    @Transactional(readOnly = true)
    public List<ProductResponseDTO> getProductsByValue(){
        return productRepository.getProductsHighValue().stream()
                .map(p -> new ProductResponseDTO(p.getCode(), p.getName(), p.getValue()))
                .toList();
    }

    public void quantityProductsToProduce(){

    }
}
