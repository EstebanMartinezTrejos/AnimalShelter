package com.example.Backenddemo.controller;

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

import com.example.Backenddemo.model.Producto;
import com.example.Backenddemo.services.ProductoService;

import lombok.RequiredArgsConstructor;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/productos")
@RequiredArgsConstructor
public class ProductoController {

    private final ProductoService productoService;

    //metodo para crear producto
    @PostMapping
    public ResponseEntity<String> createProducto (@RequestBody com.example.Backenddemo.model.Producto producto){
        try{
            productoService.createProducto(producto);
            return ResponseEntity.ok("Producto creado correctamente");
        }catch(IllegalArgumentException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Error al crear el producto: " + e.getMessage());
        }
    }

    //obtener todos los productos
    @GetMapping
    public ResponseEntity<?> obtenerTodosLosProductos(){
        try{
            List<Producto> productos = productoService.obtenerTodosLosProductos();
            return ResponseEntity.ok(productos != null ? productos : java.util.Collections.emptyList());
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(java.util.Collections.singletonMap("error", "Error al obtener los productos: " + e.getMessage()));
        }
    }

    //obtener producto por id
    @GetMapping("/{idproducto}")
    public ResponseEntity<?> obtenerProductoPorId(@PathVariable Long idproducto){
        Producto producto = productoService.obtenerProductoPorId(idproducto);
        if(producto != null){
            return ResponseEntity.ok(producto);
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Producto no encontrado con ID: " + idproducto);
        }
    }

    //actualizar producto por id
    @PutMapping("/{idproducto}")
    public ResponseEntity<?> actualizarProducto(@PathVariable Long idproducto, @RequestBody Producto producto){
        try {
            productoService.actualizarProducto(idproducto, producto);
            Producto productoActualizado = productoService.obtenerProductoPorId(idproducto);
            if (productoActualizado != null) {
                return ResponseEntity.ok(productoActualizado);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No se encontró el producto con ID: " + idproducto);
            }
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body("Error al actualizar el producto: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Error interno al actualizar el producto: " + e.getMessage());
        }
    }

    //eliminar producto por id
    @DeleteMapping("/{idproducto}")
    public ResponseEntity<String> eliminarProducto(@PathVariable Long idproducto){
        try{
            productoService.eliminarProducto(idproducto);
            return ResponseEntity.ok("Producto eliminado correctamente");
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al eliminar el producto: " + e.getMessage());
        }
    }
}
