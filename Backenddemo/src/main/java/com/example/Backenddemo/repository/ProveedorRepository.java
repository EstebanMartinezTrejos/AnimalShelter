package com.example.Backenddemo.repository;
import com.example.Backenddemo.model.Proveedor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface ProveedorRepository extends JpaRepository<Proveedor, Long> {
    
    // Buscar por nombre
    List<Proveedor> findByNombreContainingIgnoreCase(String nombre);
    
    // Buscar por RUT
    Optional<Proveedor> findByRut(String rut);
    
    // Verificar si existe un proveedor con el RUT dado
    boolean existsByRut(String rut);
    
    // Buscar por email
    Optional<Proveedor> findByEmail(String email);
}