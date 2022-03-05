package com.unosystem.dao;

import java.util.List;

import com.unosystem.entities.HojaKardex;

public interface HojaKardexDao {

    List<HojaKardex> getHojasKardex();

    String registrarHojaKardex(HojaKardex hojaKardex);

    HojaKardex encontrarHojaKardex(Long idHojaKardex);
}
