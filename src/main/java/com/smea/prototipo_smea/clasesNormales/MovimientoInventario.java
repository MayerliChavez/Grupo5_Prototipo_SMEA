package com.smea.prototipo_smea.clasesNormales;

import java.time.LocalDate;

public class MovimientoInventario {

    private LocalDate fecha;
    private String tipoMovimiento;   // Entrada | Salida | Devolución
    private String item;
    private int cantidad;
    private String responsable;
    private String usuario;
    private String rol;

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public String getProducto() {
        return producto;
    }

    public void setProducto(String producto) {
        this.producto = producto;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getAccion() {
        return accion;
    }

    public void setAccion(String accion) {
        this.accion = accion;
    }

    public String getDetalle() {
        return detalle;
    }

    public void setDetalle(String detalle) {
        this.detalle = detalle;
    }

    private String producto;
    private String tipo;
    private String accion;
    private String detalle;

    public MovimientoInventario(LocalDate fecha, String tipoMovimiento,
                                String item, int cantidad, String responsable) {
        this.fecha = fecha;
        this.tipoMovimiento = tipoMovimiento;
        this.item = item;
        this.cantidad = cantidad;
        this.responsable = responsable;
    }

    public MovimientoInventario(String usuario, String rol, String producto,
                                String tipo, int cantidad,
                                String accion, LocalDate fecha, String detalle) {
        this.usuario = usuario;
        this.rol = rol;
        this.producto = producto;
        this.tipo = tipo;
        this.cantidad = cantidad;
        this.accion = accion;
        this.fecha = fecha;
        this.detalle = detalle;
    }


    public LocalDate getFecha() {
        return fecha;
    }

    public String getTipoMovimiento() {
        return tipoMovimiento;
    }

    public String getItem() {
        return item;
    }

    public int getCantidad() {
        return cantidad;
    }

    public String getResponsable() {
        return responsable;
    }
}
