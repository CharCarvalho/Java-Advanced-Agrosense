package com.agrosense.fiap.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.agrosense.fiap.model.VegetaisModel;

@Repository
public interface VegetaisRepository extends JpaRepository<VegetaisModel, Long> {
    List<VegetaisModel> findByIdCliente(Long idCliente);
}
