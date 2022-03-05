package com.unosystem.dao;

import java.util.List;

import com.unosystem.entities.Producto;

public interface ProductoDao {

    List<Producto> getProductos();

    void registrarProducto(Producto producto);

    Producto buscarUnProducto(Long id);
}
