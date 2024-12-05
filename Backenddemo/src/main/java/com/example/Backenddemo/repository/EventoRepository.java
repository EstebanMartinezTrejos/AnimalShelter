package com.example.Backenddemo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.Backenddemo.model.Evento;

public interface EventoRepository extends JpaRepository<Evento, Long> {
    
}
