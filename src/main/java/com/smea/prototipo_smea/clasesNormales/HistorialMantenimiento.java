package com.smea.prototipo_smea.clasesNormales;

import javafx.beans.property.*;

public class HistorialMantenimiento {
    private StringProperty codigo;
    private StringProperty fecha;
    private StringProperty tipo;
    private StringProperty descripcion;
    private StringProperty responsable;
    private IntegerProperty cantidad;
    private StringProperty estadoPrioridad;

    public HistorialMantenimiento(String codigo, String fecha, String tipo, String descripcion, String responsable, String cantidad, String estadoPrioridad) {
        this.codigo = new SimpleStringProperty(codigo);
        this.fecha = new SimpleStringProperty(fecha);
        this.tipo = new SimpleStringProperty(tipo);
        this.descripcion = new SimpleStringProperty(descripcion);
        this.responsable = new SimpleStringProperty(responsable);
        this.cantidad = new SimpleIntegerProperty(Integer.parseInt(cantidad));
        this.estadoPrioridad = new SimpleStringProperty(estadoPrioridad);
    }

    public StringProperty codigoProperty() { return codigo; }
    public StringProperty fechaProperty() { return fecha; }
    public StringProperty tipoProperty() { return tipo; }
    public StringProperty descripcionProperty() { return descripcion; }
    public StringProperty responsableProperty() { return responsable; }
    public IntegerProperty cantidadProperty() { return cantidad; }
    public StringProperty estadoPrioridadProperty() { return estadoPrioridad; }
}

