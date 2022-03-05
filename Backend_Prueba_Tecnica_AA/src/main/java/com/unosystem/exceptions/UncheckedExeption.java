package com.unosystem.exceptions;

//lanza mensaje de excepciones de tipo unchecked, las cuales de las variantes de runtimeException como
//nullPointerException ArrayIndex...
public class UncheckedExeption extends RuntimeException {

    public UncheckedExeption(String mensaje) {
        super(mensaje);
    }
}
