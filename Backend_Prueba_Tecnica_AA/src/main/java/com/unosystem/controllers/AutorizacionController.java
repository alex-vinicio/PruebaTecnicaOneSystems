package com.unosystem.controllers;

import com.unosystem.dao.UsuarioDao;
import com.unosystem.entities.Usuario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*") // permite que se pueda consumir desde cualquier origen
@RequestMapping("api")
public class AutorizacionController {

    @Autowired
    private UsuarioDao usuarioDao;

    @PostMapping("/login/{correo}/{contrasenia}")
    public String usuarioLogin(@RequestParam("correo") String username, @RequestParam("contrasenia") String password) {

        Usuario usuarioLogeado = usuarioDao.validarUsuarioConCredencial(username, password);
        if (usuarioLogeado != null) {
            // creacion del token
            return "OK";
        } else {
            return "FAIL";
        }
    }
}