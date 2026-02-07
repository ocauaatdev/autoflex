package com.teste.autoflex.repository;

import com.teste.autoflex.model.RawMaterial;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RawMaterialRepository extends JpaRepository<RawMaterial, Long> {

    Optional<RawMaterial> findByNameIgnoreCase(String name);

    Optional<RawMaterial> findByCode(Long code);
}
