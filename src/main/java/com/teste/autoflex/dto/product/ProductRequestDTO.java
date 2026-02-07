package com.teste.autoflex.dto.product;

import com.teste.autoflex.dto.raw_material.RawMaterialItemDTO;

import java.util.List;

public record ProductRequestDTO(String name, Double value, List<RawMaterialItemDTO> rawMaterials) {
}
