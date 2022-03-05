package com.unosystem.controllers;

import java.util.List;

import com.unosystem.dao.OperacionKardexDao;
import com.unosystem.entities.OperacionKardex;
import com.unosystem.services.OperacionesKardexServicio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*") // permite que se pueda consumir desde cualquier origen
@RequestMapping("/api/kardex")
public class OperacionKardexController {

    @Autowired
    private OperacionKardexDao operacionKardexDao;

    @Autowired
    private OperacionesKardexServicio operacionesKardexServicio;

    @GetMapping("/Kardex/listar")
    public List<OperacionKardex> getOperacionKardex() {

        return operacionKardexDao.getOperacionesKardex();
    }

    @PostMapping("/Kardex/transacciones")
    public String registrarTransacciones(@RequestBody OperacionKardex operacionKardex) {

        return operacionesKardexServicio.aplicarTipoMetodo(operacionKardex);

    }
}
