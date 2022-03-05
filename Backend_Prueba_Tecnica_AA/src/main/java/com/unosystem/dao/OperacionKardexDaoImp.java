package com.unosystem.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import com.unosystem.entities.OperacionKardex;
import com.unosystem.services.VeriticacionLista;

//libreria para utilizar el Log4j
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public class OperacionKardexDaoImp implements OperacionKardexDao {
    // se instancia un objeto de tipo Logger para utilizar el log4j y poder
    // registrar excepciones o errores
    private static Logger logger = LogManager.getLogger(OperacionKardexDaoImp.class);

    // EntityManager permite interactuar con la BD mediante metodos conocidos o
    // consultar por query propia
    @PersistenceContext
    EntityManager entityManager;

    @Override
    @Transactional
    public List<OperacionKardex> getOperacionesKardex() {

        List<OperacionKardex> listaOperacionKardex = null;
        String query = "FROM OperacionKardex"; // el nombre de la tabla debe coincidir con el nombre de la entidad

        try {
            listaOperacionKardex = VeriticacionLista.castList(OperacionKardex.class,
                    entityManager.createQuery(query).getResultList());
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("Error no controlado: " + e);
        }

        return listaOperacionKardex;
    }

    @Override
    public void registrarOperacionKardex(OperacionKardex operacionKardex) {
        try {
            // se guarda la OperacionKardex en la base de datos con metodos del
            // EntityManager
            entityManager.merge(operacionKardex);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("Error no controlado: " + e);
        }
    }

    @Override
    public List<OperacionKardex> lisatOperacionesDeUnkardex(Long id) {
        String query = "FROM OperacionKardex WHERE idHojaKardex = :idHojaKardex";
        List<OperacionKardex> listaOprKardex = null;

        try {

            listaOprKardex = VeriticacionLista.castList(OperacionKardex.class,
                    entityManager.createQuery(query).setParameter("idHojaKardex", id).getResultList());

            if (listaOprKardex.isEmpty()) {
                return null;
            } else {
                return listaOprKardex;
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("Error no controlado: " + e);
        }

        return listaOprKardex;
    }
}
