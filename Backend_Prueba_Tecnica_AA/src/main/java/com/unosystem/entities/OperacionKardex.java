package com.unosystem.entities;

import java.util.Date;

import javax.persistence.*;

@Entity
@Table(name = "operacion_Kardex")
public class OperacionKardex {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "detalle_operacion")
    private String detalleOperacion;

    // entrada/salida/inicial
    @Column(name = "tipo_operacion")
    private String tipoOperacion;

    @Column(name = "fecha_operacion")
    private Date fechaOperacion;

    @Column(name = "cantidad")
    private Long cantidad;

    @Column(name = "precio_unitario")
    private double precioUnitario;

    @Column(name = "saldo_total")
    private double saldoTotal;

    @Column(name = "id_hoja_kardex")
    private Long idHojaKardex;

    // constructor vacio
    public OperacionKardex() {

    }

    // setters y getters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDetalleOperacion() {
        return detalleOperacion;
    }

    public void setDetalleOperacion(String detalleOperacion) {
        this.detalleOperacion = detalleOperacion;
    }

    public String getTipoOperacion() {
        return tipoOperacion;
    }

    public void setTipoOperacion(String tipoOperacion) {
        this.tipoOperacion = tipoOperacion;
    }

    public Date getFechaOperacion() {
        return fechaOperacion;
    }

    public void setFechaOperacion(Date fechaOperacion) {
        this.fechaOperacion = fechaOperacion;
    }

    public Long getCantidad() {
        return cantidad;
    }

    public void setCantidad(Long cantidad) {
        this.cantidad = cantidad;
    }

    public double getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(double valorUnit) {
        this.precioUnitario = valorUnit;
    }

    public double getSaldoTotal() {
        return saldoTotal;
    }

    public void setSaldoTotal(double d) {
        this.saldoTotal = d;
    }

    public Long getIdHojaKardex() {
        return idHojaKardex;
    }

    public void setIdHojaKardex(Long idHojaKardex) {
        this.idHojaKardex = idHojaKardex;
    }

}
