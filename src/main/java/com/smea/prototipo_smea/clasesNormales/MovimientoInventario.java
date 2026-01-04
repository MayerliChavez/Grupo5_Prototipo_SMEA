package com.smea.prototipo_smea.clasesNormales;

import java.time.LocalDate;

public class MovimientoInventario {

    private LocalDate fecha;
    private String tipoMovimiento;   // Entrada | Salida | Devolución
    private String item;
    private int cantidad;
    private String responsable;

    public MovimientoInventario(LocalDate fecha, String tipoMovimiento,
                                String item, int cantidad, String responsable) {
        this.fecha = fecha;
        this.tipoMovimiento = tipoMovimiento;
        this.item = item;
        this.cantidad = cantidad;
        this.responsable = responsable;
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
