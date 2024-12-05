package com.example.Backenddemo.services;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.example.Backenddemo.model.Mascota;
import com.example.Backenddemo.repository.MascotaRepository;

import java.util.List;

@Service
public class MascotaService {

    private final MascotaRepository mascotaRepository;

    public MascotaService(MascotaRepository mascotaRepository) {
        this.mascotaRepository = mascotaRepository;
    }

    // Método para crear una nueva mascota
    public void createMascota(Mascota mascota) {
        if(mascota == null){
            throw new IllegalArgumentException("La mascota no puede ser nula");
        }
        if(mascota.getNombre() == null ){
            throw new IllegalArgumentException("El nombre de la mascota es obligatoria");
        }
        if (mascota.getRaza()== null) {
            throw new IllegalArgumentException("La Raza de la mascota es obligatoria");
        }
        if (mascota.getPeso()<= 0) {
            throw new IllegalArgumentException("El peso de la mascota debe ser mayor a 0");
        }
        if (mascota.getEdad() < 0) {
            throw new IllegalArgumentException("La edad de la mascota no puede ser negativa");
        }
        mascotaRepository.save(mascota);
    }


    // Método para eliminar mascota
    public void eliminarMascota(Long idmascotas){
        //verifica si la mascota existe antes de eliminarla
        if (!mascotaRepository.existsById(idmascotas)) {
            throw new IllegalArgumentException("No se encontro una mascota con el ID especificado: " +idmascotas);
        }
        //elimina la mascota
        mascotaRepository.deleteById(idmascotas);
    }

    // Método para obtener una mascota por ID
    public Mascota obtenerMascotasPorId(Long idmascotas) {
        return mascotaRepository.findById(idmascotas).orElse(null);
    }

    // Método para obtener todas las mascotas
    public List<Mascota> obtenerTodasLasMascotas() {
        return mascotaRepository.findAll();
    }

    //metodo para actualizar una mascota por id
    public ResponseEntity<String> actualizarMascota(Long idmascotas, Mascota mascota) {
        if(!mascotaRepository.existsById(idmascotas)){
            return ResponseEntity.status(org.springframework.http.HttpStatus.NOT_FOUND).body("No se encontro una mascota con el ID especificado: " + idmascotas);
        }
        
        // Obtener la mascota existente
        Mascota mascotaExistente = mascotaRepository.findById(idmascotas)
                .orElseThrow(() -> new IllegalArgumentException("Mascota no encontrada"));
                
        // Actualizar solo los campos no nulos
        if (mascota.getNombre() != null) mascotaExistente.setNombre(mascota.getNombre());
        if (mascota.getRaza() != null) mascotaExistente.setRaza(mascota.getRaza());
        if (mascota.getPeso() > 0) mascotaExistente.setPeso(mascota.getPeso());
        if (mascota.getEdad() >= 0) mascotaExistente.setEdad(mascota.getEdad());
        
        // Mantener el ID original
        mascotaExistente.setIdmascotas(idmascotas);
        
        // Guardar la mascota actualizada
        mascotaRepository.save(mascotaExistente);
        return ResponseEntity.ok("Mascota actualizada correctamente");
    }
}
