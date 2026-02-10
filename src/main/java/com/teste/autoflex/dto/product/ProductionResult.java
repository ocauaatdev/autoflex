package com.teste.autoflex.dto.product;

import java.math.BigDecimal;
import java.util.List;

public record ProductionResult(List<ProductProductionDTO> products, BigDecimal totalValue) {
}
