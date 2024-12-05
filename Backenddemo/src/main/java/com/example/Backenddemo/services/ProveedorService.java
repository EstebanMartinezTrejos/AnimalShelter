package com.example.Backenddemo.services;
import com.example.Backenddemo.model.Proveedor;
import com.example.Backenddemo.repository.ProveedorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service

public class ProveedorService {
    private final ProveedorRepository proveedorRepository;

    @Autowired
    public ProveedorService(ProveedorRepository proveedorRepository) {
        this.proveedorRepository = proveedorRepository;
    }

    // Método para crear un proveedor
    public Proveedor crearProveedor(Proveedor proveedor) {
        // Validaciones
        if (proveedor == null) {
            throw new IllegalArgumentException("El proveedor no puede ser nulo");
        }
        if (proveedor.getNombre() == null || proveedor.getNombre().trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre del proveedor es obligatorio");
        }
        if (proveedor.getRut() == null || proveedor.getRut().trim().isEmpty()) {
            throw new IllegalArgumentException("El RUT del proveedor es obligatorio");
        }
        if (existeRut(proveedor.getRut())) {
            throw new IllegalArgumentException("Ya existe un proveedor con este RUT");
        }

        return proveedorRepository.save(proveedor);
    }

    // Método para obtener todos los proveedores
    public List<Proveedor> obtenerTodosLosProveedores() {
        return proveedorRepository.findAll();
    }

    // Método para obtener proveedor por ID
    public Proveedor obtenerProveedorPorId(Long id) {
        return proveedorRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("No se encontró proveedor con ID: " + id));
    }

    // Método para buscar proveedores por nombre
    public List<Proveedor> buscarProveedoresPorNombre(String nombre) {
        return proveedorRepository.findByNombreContainingIgnoreCase(nombre);
    }

    // Método para obtener proveedor por RUT
    public Optional<Proveedor> obtenerProveedorPorRut(String rut) {
        return proveedorRepository.findByRut(rut);
    }

    // Método para actualizar proveedor
    public Proveedor actualizarProveedor(Long id, Proveedor proveedorActualizado) {
        Proveedor proveedorExistente = proveedorRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("No se encontró proveedor con ID: " + id));

        // Actualizar solo los campos no nulos
        if (proveedorActualizado.getNombre() != null) {
            proveedorExistente.setNombre(proveedorActualizado.getNombre());
        }
        if (proveedorActualizado.getRut() != null) {
            // Verificar que el nuevo RUT no exista (si es diferente al actual)
            if (!proveedorActualizado.getRut().equals(proveedorExistente.getRut()) && 
                existeRut(proveedorActualizado.getRut())) {
                throw new IllegalArgumentException("Ya existe un proveedor con este RUT");
            }
            proveedorExistente.setRut(proveedorActualizado.getRut());
        }
        if (proveedorActualizado.getDireccion() != null) {
            proveedorExistente.setDireccion(proveedorActualizado.getDireccion());
        }
        if (proveedorActualizado.getTelefono() != null) {
            proveedorExistente.setTelefono(proveedorActualizado.getTelefono());
        }
        if (proveedorActualizado.getEmail() != null) {
            proveedorExistente.setEmail(proveedorActualizado.getEmail());
        }

        return proveedorRepository.save(proveedorExistente);
    }

    // Método para eliminar proveedor
    public void eliminarProveedor(Long id) {
        if (!proveedorRepository.existsById(id)) {
            throw new IllegalArgumentException("No se encontró proveedor con ID: " + id);
        }
        proveedorRepository.deleteById(id);
    }

    // Método para verificar si existe un RUT
    public boolean existeRut(String rut) {
        return proveedorRepository.existsByRut(rut);
    }
}

