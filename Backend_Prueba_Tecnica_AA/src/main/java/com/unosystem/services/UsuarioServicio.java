package com.unosystem.services;

import org.springframework.stereotype.Service;

import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;

//clase que permite integrar logica de alguna entidad
@Service
public class UsuarioServicio {

    public UsuarioServicio() {

    }

    // metodo que permite generar un hash de una cadena de texto utilizando el
    // algoritmo Argon2
    public String generarValoresHash(String valorEncriptar) {

        Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);
        String hashPasword = argon2.hash(1, 1024, 1, valorEncriptar);

        return hashPasword;
    }
}