package com.example.Backenddemo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.Backenddemo.model.Mascota;

@Repository
public interface MascotaRepository extends JpaRepository<Mascota, Long> {
    
}
