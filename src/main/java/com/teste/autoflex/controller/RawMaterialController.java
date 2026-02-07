package com.teste.autoflex.controller;

import com.teste.autoflex.model.RawMaterial;
import com.teste.autoflex.service.RawMaterialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/raw-materials")
public class RawMaterialController {

    @Autowired
    private RawMaterialService service;

    @PostMapping("/add")
    public ResponseEntity<Object> registerRawMaterial(@Validated @RequestBody RawMaterial rawMaterial){
        try {
            var result = service.register(rawMaterial);
            return ResponseEntity.ok().body(result);
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/update/{code}")
    public ResponseEntity<Object> update (@PathVariable Long code, @RequestBody RawMaterial rawMaterial){
        try {
            var result = service.update(code, rawMaterial);
            return ResponseEntity.ok().body(result);
        } catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/delete/{code}")
    public ResponseEntity<Object> delete (@PathVariable Long code){
        try {
            service.delete(code);
            return ResponseEntity.ok().body("Raw material deleted successfully.");
        } catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/all")
    public ResponseEntity<Object> getAllRawMaterials(){
        try {
            var result = service.getAllRawMaterials();
            return ResponseEntity.ok().body(result);
        } catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
