package com.teste.autoflex.controller;

import com.teste.autoflex.dto.product.ProductRequestDTO;
import com.teste.autoflex.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService service;

    @PostMapping("/add")
    public ResponseEntity<Object> addProduct(@RequestBody ProductRequestDTO dto){
        var result = service.register(dto);
        try {
            return ResponseEntity.ok().body(result);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/update/{code}")
    public ResponseEntity<Object> update(@PathVariable Long code, @RequestBody ProductRequestDTO dto){
        var result = service.update(code, dto);
        try {
            return ResponseEntity.ok().body(result);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/delete/{code}")
    public ResponseEntity<Object> delete(@PathVariable Long code){
        try {
            service.delete(code);
            return ResponseEntity.ok().body("Product deleted successfully.");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/")
    public ResponseEntity<Object> getProductsByValue(){
        var result = service.getProductsByValue();
        try {
            return ResponseEntity.ok().body(result);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/production")
    public ResponseEntity<Object> getQuantityProductsToProduce(){
        var result = service.quantityProductsToProduce();
        try {
            return ResponseEntity.ok().body(result);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
