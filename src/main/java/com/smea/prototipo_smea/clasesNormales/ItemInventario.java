package com.smea.prototipo_smea.clasesNormales;

public class ItemInventario {

    private String codigo;
    private String nombre;
    private int stock;
    private int cantidad; // usada solo en la orden de salida

    // =========================
    // CONSTRUCTORES
    // =========================
    public ItemInventario(String codigo, String nombre, int stock) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.stock = stock;
        this.cantidad = 0;
    }

    public ItemInventario(String codigo, String nombre, int stock, int cantidad) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.stock = stock;
        this.cantidad = cantidad;
    }

    // =========================
    // GETTERS & SETTERS
    // =========================
    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
}
