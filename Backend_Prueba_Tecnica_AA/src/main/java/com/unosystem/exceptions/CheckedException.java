package com.unosystem.exceptions;

//se añade la clase CheckedException, la cual permitira lanzar mensajes a excepciones de tipo checked
//las cuales son todas las que se generan excepto en RuntimeException
public class CheckedException extends Exception {

    public CheckedException(String mensaje) {
        super(mensaje);

    }
}
