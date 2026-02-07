package com.teste.autoflex.service;

import com.teste.autoflex.dto.raw_material.RawMaterialResponseDTO;
import com.teste.autoflex.exceptions.BusinessException;
import com.teste.autoflex.model.RawMaterial;
import com.teste.autoflex.repository.RawMaterialRepository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RawMaterialService {

    @Autowired
    private RawMaterialRepository repository;

    @Transactional
    public RawMaterialResponseDTO register (RawMaterial rawMaterial) {

        if (rawMaterial.getName() == null || rawMaterial.getName().isBlank()){
            throw new BusinessException("Raw material name cannot be empty.");
        }

        String normalizedName = rawMaterial.getName().trim();
        rawMaterial.setName(normalizedName);

        if (repository.findByNameIgnoreCase(rawMaterial.getName()).isPresent()){
            throw new BusinessException("Raw material with the same name already exists.");
        }

        if (rawMaterial.getStockQuantity() == null){
            rawMaterial.setStockQuantity(0);
        }

        repository.save(rawMaterial);
        return new RawMaterialResponseDTO(rawMaterial.getCode(), rawMaterial.getName(), rawMaterial.getStockQuantity());
    }

    @Transactional
    public void delete (Long code){
        var existingRawMaterial = repository.findById(code)
                .orElseThrow(() -> new BusinessException("Raw material not found."));

        repository.delete(existingRawMaterial);
    }

    @Transactional
    public RawMaterialResponseDTO update (Long codeToUpdate, RawMaterial rawMaterial){
        var existingRawMaterial = repository.findById(codeToUpdate)
                .orElseThrow(() -> new BusinessException("Raw material not found."));

        if (rawMaterial.getName() != null && !rawMaterial.getName().isBlank()) {
            String normalizedName = rawMaterial.getName().trim();
            existingRawMaterial.setName(normalizedName);
        }

        if (rawMaterial.getStockQuantity() != null) {
            existingRawMaterial.setStockQuantity(rawMaterial.getStockQuantity());
        }

        repository.save(existingRawMaterial);

        return new RawMaterialResponseDTO(existingRawMaterial.getCode(), existingRawMaterial.getName(), existingRawMaterial.getStockQuantity());
    }

    @Transactional(readOnly = true)
    public List<RawMaterial> getAllRawMaterials() {
        return repository.findAll();
    }
}
