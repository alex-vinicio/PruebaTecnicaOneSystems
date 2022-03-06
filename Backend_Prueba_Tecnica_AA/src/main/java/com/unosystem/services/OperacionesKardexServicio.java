package com.unosystem.services;

import java.util.List;

import com.unosystem.dao.HojaKardexDao;
import com.unosystem.dao.OperacionKardexDao;
import com.unosystem.entities.HojaKardex;
import com.unosystem.entities.OperacionKardex;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//libreria para utilizar el Log4j
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

//clase que permite integrar logica de alguna entidad
@Service
public class OperacionesKardexServicio {
    // se instancia un objeto de tipo Logger para utilizar el log4j y poder
    // registrar excepciones o errores
    private static Logger logger = LogManager.getLogger(OperacionesKardexServicio.class);

    @Autowired
    private HojaKardexDao hojaKardexDao;

    @Autowired
    private OperacionKardexDao operacionKardexDao;

    public String aplicarTipoMetodo(OperacionKardex operacionKardex) {
        HojaKardex hojaKardexDeOperacion;
        double[] valAnteriores = new double[2];
        // contro para validar que la cantidad y el valor unitario sean positivos
        if (operacionKardex.getCantidad() <= 0) {
            return "El V.U. o la cantidad deben ser mayores a 0 y positivos!";
        } else if (operacionKardex.getPrecioUnitario() <= 0) {
            return "El V.U. o la cantidad deben ser mayores a 0 y positivos!";
        }

        try {
            Long idHK = operacionKardex.getIdHojaKardex();
            hojaKardexDeOperacion = hojaKardexDao.encontrarHojaKardex(idHK);
            // valida que el tipo de operacion sea inicial/entrada/salida
            if (operacionKardex.getTipoOperacion().equals("inicial")) {
                // valida que la operacion pertenesca a una hoja de kardex
                if (hojaKardexDeOperacion != null) {
                    valAnteriores = recuperarValoresAnteriores(operacionKardex.getIdHojaKardex());
                    // valida que no debe haber operaciones anteriores para ser Inicial
                    if (valAnteriores == null) {
                        // se actualiza setSaldoTotal, stock y precio U de la hoja de kardex
                        operacionKardex
                                .setSaldoTotal(operacionKardex.getCantidad() * operacionKardex.getPrecioUnitario());
                        operacionKardexDao.registrarOperacionKardex(operacionKardex);
                        return "OK";
                    } else {
                        return "La operacion no puede ser inicial!";
                    }
                } else {
                    return "No existe la hoja de kardex";
                }

            } else if (operacionKardex.getTipoOperacion().equals("entrada")) {
                // valida que la operacion pertenesca a una hoja de kardex
                if (hojaKardexDeOperacion != null) {
                    valAnteriores = recuperarValoresAnteriores(operacionKardex.getIdHojaKardex());
                    // valida que debe haber operaciones anteriores para ser Inicial
                    if (valAnteriores != null) {
                        double valorUnit = promedioPonderado(((int) valAnteriores[0]), valAnteriores[1],
                                operacionKardex.getCantidad(), operacionKardex.getPrecioUnitario());
                        operacionKardex.setCantidad(((int) valAnteriores[0]) + operacionKardex.getCantidad());
                        operacionKardex.setPrecioUnitario(valorUnit);
                        operacionKardex
                                .setSaldoTotal(operacionKardex.getCantidad() * operacionKardex.getPrecioUnitario());
                        // se actualiza setSaldoTotal, stock y precio U de la hoja de kardex
                        operacionKardexDao.registrarOperacionKardex(operacionKardex);
                        return "OK";
                    } else {
                        return "Hoja de kardex para valores anteriores no encontrado";
                    }
                } else {
                    return "No existe la hoja de kardex";
                }
            } else if (operacionKardex.getTipoOperacion().equals("salida")) {
                // valida que la operacion pertenesca a una hoja de kardex
                if (hojaKardexDeOperacion != null) {
                    valAnteriores = recuperarValoresAnteriores(operacionKardex.getIdHojaKardex());
                    // valida que debe haber operaciones anteriores para ser Inicial
                    if (valAnteriores != null) {
                        if (operacionKardex.getCantidad() <= valAnteriores[0]) {
                            operacionKardex.setCantidad((int) valAnteriores[0] - operacionKardex.getCantidad());
                            operacionKardex
                                    .setSaldoTotal(operacionKardex.getCantidad() * operacionKardex.getPrecioUnitario());

                            operacionKardex.setPrecioUnitario(valAnteriores[1]);
                            // se actualiza setSaldoTotal, stock y precio U de la hoja de kardex
                            operacionKardexDao.registrarOperacionKardex(operacionKardex);
                            return "OK";
                        } else {
                            return "No hay suficiente stock";
                        }
                    } else {
                        return "Hoja de kardex para valores anteriores no encontrado";
                    }
                } else {
                    return "No existe la hoja de kardex";
                }
            } else {
                return "No existe la operacion de tipo E/S/I";
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("Error no controlado: " + e);
            return "FAIL";
        }

    }

    // funcion que calcula el promedio ponderado de una operacion anterior con la
    // actual a actualizar
    public double promedioPonderado(double cantidad1, double valor1, double cantidad2, double valor2) {
        double promedio = 0;
        double suma = 0;
        double cantidad = cantidad1 + cantidad2;
        suma = (cantidad1 * valor1) + (cantidad2 * valor2);
        promedio = suma / cantidad;
        System.out.println(cantidad1 + " " + valor1 + " " + cantidad2 + " " + valor2);
        return promedio;
    }

    // obtener ultimo valor de la lista de operacionees de un cardex en especifico
    public double[] recuperarValoresAnteriores(long id) {
        List<OperacionKardex> opKardex = operacionKardexDao.lisatOperacionesDeUnkardex(id);
        double[] valoresAnt = new double[2];

        if (opKardex == null) {
            return null;
        } else {
            try {
                // se optine el ultimo indice de la lista
                int indexPK = opKardex.size() - 1;
                // valida si la lista no esta vacia en el indice especifico
                if (opKardex.get(indexPK) != null) {
                    valoresAnt[0] = opKardex.get(indexPK).getCantidad();
                    valoresAnt[1] = opKardex.get(indexPK).getPrecioUnitario();

                    return valoresAnt;
                } else {
                    return null;
                }
            } catch (Exception e) {
                e.printStackTrace();
                logger.error("Error no controlado: " + e);
            }
            return valoresAnt;
        }
    }
}
