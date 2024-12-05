package com.example.Backenddemo.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.Backenddemo.model.Producto;
import com.example.Backenddemo.repository.ProductoRepository;

@Service
public class ProductoService {

    private final ProductoRepository productoRepository;

    //constructor con inyeccion de dependencias 
    public ProductoService(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }

    //validacion de producto
    public void createProducto(Producto producto){
        if(producto == null){
            throw new IllegalArgumentException("El producto no puede ser nulo");
        }
        if(producto.getNombre() == null){
            throw new IllegalArgumentException("El nombre del producto es obligatorio");
        }
        if(producto.getPrecio() <= 0){
            throw new IllegalArgumentException("El precio del producto es obligatorio");
        }
        if(producto.getCantidad() <= 0){
            throw new IllegalArgumentException("La cantidad del producto es obligatoria");
        }
        if(producto.getImagen() == null){
            throw new IllegalArgumentException("La imagen del producto es obligatoria");
        }
        if(producto.getCategoria() == null){
            throw new IllegalArgumentException("La categoria del producto es obligatoria");
        }
        if(producto.getTipo() == null){
            throw new IllegalArgumentException("El tipo del producto es obligatorio");
        }
        //guardar el producto en la base de datos
        productoRepository.save(producto);
    }

    //metodo para actualizar producto
    public void actualizarProducto(Long idproducto, Producto producto){
        if(!productoRepository.existsById(idproducto)){
            throw new IllegalArgumentException("No se encontro un producto con el ID especificado: " + idproducto);
        }
        Producto productoExistente = productoRepository.findById(idproducto).get();
        if(producto.getNombre() != null) productoExistente.setNombre(producto.getNombre());
        if(producto.getPrecio() > 0) productoExistente.setPrecio(producto.getPrecio());
        if(producto.getCategoria() != null) productoExistente.setCategoria(producto.getCategoria());
        if(producto.getTipo() != null) productoExistente.setTipo(producto.getTipo());
        if(producto.getCantidad() > 0) productoExistente.setCantidad(producto.getCantidad());
        if(producto.getImagen() != null) productoExistente.setImagen(producto.getImagen());
        productoRepository.save(productoExistente);
    }

    //metodo para eliminar producto
    public void eliminarProducto(Long idproducto){
        if(!productoRepository.existsById(idproducto)){
            throw new IllegalArgumentException("No se encontro un producto con el ID especificado: " + idproducto);
        }
        productoRepository.deleteById(idproducto);
    }

    //metodo para obtener producto por id
    public Producto obtenerProductoPorId(Long idproducto){
        return productoRepository.findById(idproducto).orElse(null);
    }

    //metodo para obtener todos los productos
    public List<Producto> obtenerTodosLosProductos(){
        return productoRepository.findAll();
    }
    
}
