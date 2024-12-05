package com.example.Backenddemo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Backenddemo.model.Producto;

public interface ProductoRepository extends JpaRepository<Producto, Long> {
    
}
