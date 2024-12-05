package com.example.Backenddemo.services;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.Backenddemo.model.Evento;
import com.example.Backenddemo.repository.EventoRepository;

@Service
public class EventoService {

    private final EventoRepository eventoRepository;

    public EventoService(EventoRepository eventoRepository) {
        this.eventoRepository = eventoRepository;
    }

    // Método para crear un evento
    public void createEvento(Evento evento) {
        //se hace las validaciones
        if(evento == null){
            throw new IllegalArgumentException("El evento no puede ser nulo");
        }
        if(evento.getNombre() == null){
            throw new IllegalArgumentException("El nombre del evento es obligatorio");
        }
        if(evento.getDescripcion() == null){
            throw new IllegalArgumentException("La descripcion del evento es obligatoria");
        }
        if(evento.getFecha() == null){
            throw new IllegalArgumentException("La fecha del evento es obligatoria");
        }
        //aqui guardamos el evento en la base de datos
        eventoRepository.save(evento);
    }


    // Método para obtener un evento por id
    public Evento obtenerEventoPorId(Long idevento) {
        return eventoRepository.findById(idevento).orElse(null);
    }

    // Método para listar todos los eventos
    public List<Evento> obtenerTodosLosEventos() {
        return eventoRepository.findAll();
    }

    //metodo para actualizar un evento por id
    public ResponseEntity<String> actualizarEvento(Long idevento, Evento evento) {
        if (!eventoRepository.existsById(idevento)) {
            return ResponseEntity.status(org.springframework.http.HttpStatus.NOT_FOUND).body("No se encontro un evento con el ID especificado: " + idevento);
        }
        //actualizar solo los campos no nulos
        Evento eventoExistente = eventoRepository.findById(idevento).get();
        if (evento.getNombre() != null) eventoExistente.setNombre(evento.getNombre());
        if (evento.getDescripcion() != null) eventoExistente.setDescripcion(evento.getDescripcion());
        if (evento.getFecha() != null) eventoExistente.setFecha(evento.getFecha());
        eventoRepository.save(eventoExistente);
        return ResponseEntity.ok("Evento actualizado correctamente");
    }

    //metodo para eliminar un evento por id
    public void eliminarEvento(Long idevento){
        if (!eventoRepository.existsById(idevento)) {
            throw new IllegalArgumentException("No se encontro un evento con el ID especificado: " + idevento);
        }
        //eliminar el evento
        eventoRepository.deleteById(idevento);
    }
}

