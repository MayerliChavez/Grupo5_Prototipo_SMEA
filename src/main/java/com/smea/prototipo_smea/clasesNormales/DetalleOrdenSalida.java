package com.smea.prototipo_smea.clasesNormales;

public class DetalleOrdenSalida {

    private String codigoItem;
    private String nombreItem;
    private int cantidad;

    public DetalleOrdenSalida(String codigoItem,
                              String nombreItem,
                              int cantidad) {
        this.codigoItem = codigoItem;
        this.nombreItem = nombreItem;
        this.cantidad = cantidad;
    }

    public String getCodigoItem() { return codigoItem; }
    public String getNombreItem() { return nombreItem; }
    public int getCantidad() { return cantidad; }
}

