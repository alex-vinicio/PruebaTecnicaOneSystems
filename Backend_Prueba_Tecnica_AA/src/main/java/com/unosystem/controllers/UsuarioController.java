package com.unosystem.controllers;

import java.util.List;

import com.unosystem.dao.UsuarioDao;
import com.unosystem.entities.Usuario;
import com.unosystem.services.UsuarioServicio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*") // permite que se pueda consumir desde cualquier origen
@RequestMapping("/api")
public class UsuarioController {

    // inyeccion de dependencias
    // crea el objeto y guarda en la varaible, el objeto comparte en memoria
    @Autowired
    private UsuarioDao usuarioDao;

    @Autowired
    private UsuarioServicio usuarioServicio;

    @GetMapping("/usuario/listarTodos")
    public List<Usuario> prueba() {

        return usuarioDao.getUsuarios();
    }

    @PostMapping("/usuario/registrar")
    public void registrarUsuario(@RequestBody Usuario usuario) {

        try {
            usuario.setContrasenia(usuarioServicio.generarValoresHash(usuario.getContrasenia()));
            usuario.setNumeroTarjeta(usuarioServicio.generarValoresHash(usuario.getNumeroTarjeta()));

            usuarioDao.registrarUsuario(usuario);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @DeleteMapping("/usuario/eliminar/{ci}")
    public void eliminarUsuario(/* @RequestHeader(value = "Authorization") String token, */
            @PathVariable String ci) {

        if (ci != null) {
            usuarioDao.eliminarUsuario(ci);
        } else {
            System.out.println("No tiene permisos para eliminar");
        }
    }
}
