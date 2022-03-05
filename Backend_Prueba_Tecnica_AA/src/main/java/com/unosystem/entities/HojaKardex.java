package com.unosystem.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "hoja_kardex")
public class HojaKardex {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "ubicacion")
    private String ubicacion;

    @Column(name = "nombre_proveedor")
    private String nombreProveedor;

    @Column(name = "referencia")
    private String referencia;

    // metodo para aplicar en el kardex de la
    // ponderado/PEPS
    @Column(name = "metodo")
    private String metodo;

    @Column(name = "unidad")
    private String unidad;

    @Column(name = "cant_minima")
    private Long cantidadMinima;

    @Column(name = "cant_maxima")
    private Long cantidadMaxima;

    @Column(name = "id_producto")
    private Long id_producto;

    // constructor vacio
    public HojaKardex() {
    }

    // setters y getters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public String getNombreProveedor() {
        return nombreProveedor;
    }

    public void setNombreProveedor(String nombreProveedor) {
        this.nombreProveedor = nombreProveedor;
    }

    public String getReferencia() {
        return referencia;
    }

    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }

    public String getMetodo() {
        return metodo;
    }

    public void setMetodo(String metodo) {
        this.metodo = metodo;
    }

    public String getUnidad() {
        return unidad;
    }

    public void setUnidad(String unidad) {
        this.unidad = unidad;
    }

    public Long getCantidadMinima() {
        return cantidadMinima;
    }

    public void setCantidadMinima(Long cantidadMinima) {
        this.cantidadMinima = cantidadMinima;
    }

    public Long getCantidadMaxima() {
        return cantidadMaxima;
    }

    public void setCantidadMaxima(Long cantidadMaxima) {
        this.cantidadMaxima = cantidadMaxima;
    }

    public Long getId_producto() {
        return id_producto;
    }

    public void setId_producto(Long id_producto) {
        this.id_producto = id_producto;
    }

}
