package com.unosystem.controllers;

import java.util.List;

import com.unosystem.dao.HojaKardexDao;
import com.unosystem.entities.HojaKardex;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*") // permite que se pueda consumir desde cualquier origen
@RequestMapping("/api")
public class HojaKardexController {
    @Autowired
    private HojaKardexDao hojaKardexDao;

    @GetMapping("/Kardex/listar")
    public List<HojaKardex> listarKardex() {

        return hojaKardexDao.getHojasKardex();
    }

    @PostMapping("/Kardex/registrar")
    public String registrarKardex(@RequestBody HojaKardex hojaKardex) {

        return hojaKardexDao.registrarHojaKardex(hojaKardex);
    }
}
