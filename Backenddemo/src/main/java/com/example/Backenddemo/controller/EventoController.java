package com.example.Backenddemo.controller;

import java.util.Collections;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Backenddemo.model.Evento;
import com.example.Backenddemo.services.EventoService;

import lombok.RequiredArgsConstructor;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/eventos")
@RequiredArgsConstructor
public class EventoController {

    private final EventoService eventoService;

    // Crear un nuevo evento
    @PostMapping
    public ResponseEntity<String> createEvento(@RequestBody Evento evento) {
        try {
            eventoService.createEvento(evento);
            return ResponseEntity.ok("Evento creado correctamente");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Error al crear el evento: " + e.getMessage());
        }
    }
    

    //obtener evento por id
    @GetMapping("/{idevento}")
    public ResponseEntity<?> obtenerEventoPorId(@PathVariable Long idevento) {
        Evento evento = eventoService.obtenerEventoPorId(idevento);
        if (evento != null) {
            return ResponseEntity.ok(evento);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Evento no encontrado con ID: " + idevento);
        }
    }



    //obtener todos los eventos
    @GetMapping
    public ResponseEntity<?> obtenerTodosLosEventos() {
        try {
            List<Evento> eventos = eventoService.obtenerTodosLosEventos();
            return ResponseEntity.ok(eventos != null ? eventos : Collections.emptyList());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(Collections.singletonMap("error", "Error al obtener los eventos: " + e.getMessage()));
        }
    }
    
    //actualizar evento por id
    @PutMapping("/{idevento}")
    public ResponseEntity<?> actualizarEvento(@PathVariable Long idevento, @RequestBody Evento evento) {
        return eventoService.actualizarEvento(idevento, evento);
        }

    //eliminar evento por id
    @DeleteMapping("/{idevento}")
    public ResponseEntity<String> eliminarEvento(@PathVariable Long idevento) {
        try{
            eventoService.eliminarEvento(idevento);
            return ResponseEntity.ok("Evento eliminado correctamente");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al eliminar el evento: " + e.getMessage());
        }
    }
}
