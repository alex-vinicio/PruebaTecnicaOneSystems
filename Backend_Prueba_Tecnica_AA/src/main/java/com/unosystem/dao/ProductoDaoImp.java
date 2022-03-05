package com.unosystem.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import com.unosystem.entities.Producto;
import com.unosystem.services.VeriticacionLista;

import org.springframework.stereotype.Repository;

@Repository
@Transactional
public class ProductoDaoImp implements ProductoDao {
    // EntityManager permite interactuar con la BD mediante metodos conocidos o
    // consultar por query propia
    @PersistenceContext
    EntityManager entityManager;

    @Override
    @Transactional
    public List<Producto> getProductos() {
        List<Producto> listaProductos = null;
        String query = "FROM Producto";

        try {
            listaProductos = VeriticacionLista.castList(Producto.class,
                    entityManager.createQuery(query).getResultList());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return listaProductos;
    }

    @Override
    public void registrarProducto(Producto producto) {
        try {
            // se guarda el producto en la base de datos con metodos del EntityManager
            entityManager.merge(producto);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Producto buscarUnProducto(Long id) {
        Producto productoEncontrado = null;

        try {
            productoEncontrado = entityManager.find(Producto.class, id);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return productoEncontrado;
    }

}
