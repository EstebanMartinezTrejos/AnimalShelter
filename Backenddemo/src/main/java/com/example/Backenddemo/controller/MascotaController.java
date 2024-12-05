package com.example.Backenddemo.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.example.Backenddemo.model.Mascota;
import com.example.Backenddemo.services.MascotaService;

import lombok.RequiredArgsConstructor;
import java.util.List;

@CrossOrigin(origins = "*")
@RequestMapping("/mascotas")
@RestController
@RequiredArgsConstructor
public class MascotaController {

    private final MascotaService mascotasService;

    // Crear una nueva mascota
    @PostMapping
    public ResponseEntity<String> createMascota(@RequestBody Mascota mascota) {
        if (mascota == null || mascota.getNombre() == null || mascota.getNombre().isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("El nombre de la mascota es obligatorio.");
        }
        try {
            mascotasService.createMascota(mascota);
            return ResponseEntity.ok("Mascota creada exitosamente.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al crear la mascota: " + e.getMessage());
        }
    }

    // Obtener mascota por id
    @GetMapping("/{idmascotas}")
    public ResponseEntity<?> obtenerMascotasPorId(@PathVariable Long idmascotas) {
        Mascota mascota = mascotasService.obtenerMascotasPorId(idmascotas);
        if (mascota != null) {
            return ResponseEntity.ok(mascota); // Retorna 200 OK con la mascota encontrada
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Mascota no encontrada con ID: " + idmascotas); // Retorna 404
        }
    }

    // Obtener todas las mascotas
    @GetMapping
    public ResponseEntity<?> obtenerTodasLasMascotas() {
        try {
            List<Mascota> mascotas = mascotasService.obtenerTodasLasMascotas();
            return ResponseEntity.ok(mascotas);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Error al obtener las mascotas: " + e.getMessage());
        }
    }

    // Actualizar una mascota
    @PutMapping("/{idmascotas}")
    public ResponseEntity<String> actualizarMascota(@PathVariable Long idmascotas, @RequestBody Mascota mascota) {
        return mascotasService.actualizarMascota(idmascotas, mascota);
    }

    // Eliminar una mascota por ID
    @DeleteMapping("/{idmascotas}")
    public ResponseEntity<String> eliminarMascota(@PathVariable Long idmascotas) {
        try {
            mascotasService.eliminarMascota(idmascotas);
            return ResponseEntity.ok("Mascota eliminada exitosamente.");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Error: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al eliminar la mascota: " + e.getMessage());
        }
    }
}
