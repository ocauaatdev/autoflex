package com.teste.autoflex.repository;

import com.teste.autoflex.model.Product;
import com.teste.autoflex.model.RawMaterial;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {
    Optional<Product> findByNameIgnoreCase(String name);

    @Query("SELECT p FROM Product p ORDER BY p.value DESC")
    List<Product> getProductsHighValue();
}
