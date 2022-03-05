package com.unosystem.controllers;

import java.util.List;

import com.unosystem.dao.ProductoDao;
import com.unosystem.entities.Producto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*") // permite que se pueda consumir desde cualquier origen
@RequestMapping("/api")
public class ProductoController {

    @Autowired
    private ProductoDao productoDao;

    @GetMapping("/producto/listarTodos")
    public List<Producto> prueba() {

        return productoDao.getProductos();
    }

    @PostMapping("/producto/registrar")
    public void registrarUsuario(@RequestBody Producto producto) {

        try {

            productoDao.registrarProducto(producto);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
