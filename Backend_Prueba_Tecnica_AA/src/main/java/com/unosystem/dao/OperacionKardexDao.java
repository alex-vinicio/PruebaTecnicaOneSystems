package com.unosystem.dao;

import java.util.List;

import com.unosystem.entities.OperacionKardex;

public interface OperacionKardexDao {

    List<OperacionKardex> getOperacionesKardex();

    void registrarOperacionKardex(OperacionKardex operacionKardex);

    List<OperacionKardex> lisatOperacionesDeUnkardex(Long idHojaKardex);

}
