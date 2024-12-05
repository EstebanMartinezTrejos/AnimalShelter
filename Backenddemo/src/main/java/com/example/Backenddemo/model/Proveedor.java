package com.example.Backenddemo.model;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "proveedores")
public class Proveedor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idproveedores;
    @Column(nullable = false)    
    private String nombre;
    @Column(nullable = false, unique = true)
    private String rut;
    @Column(name = "direccion")
    private String direccion;
    
    @Column(name = "telefono")
    private String telefono;
    
    @Column(name = "email")
    private String email;
    
    // Constructor vacío requerido por JPA
    public Proveedor() {
    }
    // Constructor con parámetros
    public Proveedor(String nombre, String rut, String direccion, String telefono, String email) {
        this.nombre = nombre;
        this.rut = rut;
        this.direccion = direccion;
        this.telefono = telefono;
        this.email = email;
    }
}

