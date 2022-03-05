package com.unosystem.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import com.unosystem.entities.HojaKardex;
import com.unosystem.services.VeriticacionLista;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

//libreria para utilizar el Log4j
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Repository
@Transactional
public class HojaKardexDaoImp implements HojaKardexDao {

    // se instancia un objeto de tipo Logger para utilizar el log4j y poder
    // registrar excepciones o errores
    private static Logger logger = LogManager.getLogger(HojaKardexDaoImp.class);

    // EntityManager permite interactuar con la BD mediante metodos conocidos o
    // consultar por query propia
    @PersistenceContext
    EntityManager entityManager;

    @Autowired
    private ProductoDao productoDao;

    @Override
    @Transactional
    public List<HojaKardex> getHojasKardex() {

        List<HojaKardex> listaHojaKardex = null;
        String query = "FROM HojaKardex";

        try {
            listaHojaKardex = VeriticacionLista.castList(HojaKardex.class,
                    entityManager.createQuery(query).getResultList());
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("Error no controlado: " + e);
        }

        if (listaHojaKardex.isEmpty()) {
            return null;
        } else {
            return listaHojaKardex;
        }
    }

    @Override
    public String registrarHojaKardex(HojaKardex hojaKardex) {
        // intenta registrar una hoja kardex, si el producto existe
        try {

            if (productoDao.buscarUnProducto(hojaKardex.getId_producto()) != null) {
                // se guarda el hojaKardex en la base de datos con metodos del EntityManager
                entityManager.merge(hojaKardex);
                return "OK";
            } else {
                return "El producto no existe!";
            }

        } catch (Exception e) {
            e.printStackTrace();
            logger.error("Error no controlado: " + e);
        }
        return "Ingrese todos los valores o producto valido!";
    }

    @Override
    public HojaKardex encontrarHojaKardex(Long id) {
        HojaKardex hojaKardexEncontrada = null;

        try {
            // encuentra una hoja de calculo en la BD con el ID
            hojaKardexEncontrada = entityManager.find(HojaKardex.class, id);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("Error no controlado: " + e);
        }

        return hojaKardexEncontrada;
    }

}
