package com.smea.prototipo_smea.clasesNormales;

import javafx.beans.property.*;
import java.time.LocalDate;

public class ServicioReporte {

    private StringProperty codigo;
    private ObjectProperty<LocalDate> fecha;
    private StringProperty tipo;
    private StringProperty descripcion;
    private StringProperty responsable;
    private StringProperty estado;

    public ServicioReporte(String codigo, LocalDate fecha, String tipo,
                           String descripcion, String responsable, String estado) {
        this.codigo = new SimpleStringProperty(codigo);
        this.fecha = new SimpleObjectProperty<>(fecha);
        this.tipo = new SimpleStringProperty(tipo);
        this.descripcion = new SimpleStringProperty(descripcion);
        this.responsable = new SimpleStringProperty(responsable);
        this.estado = new SimpleStringProperty(estado);
    }

    public StringProperty codigoProperty() { return codigo; }
    public ObjectProperty<LocalDate> fechaProperty() { return fecha; }
    public StringProperty tipoProperty() { return tipo; }
    public StringProperty descripcionProperty() { return descripcion; }
    public StringProperty responsableProperty() { return responsable; }
    public StringProperty estadoProperty() { return estado; }

    public LocalDate getFecha() { return fecha.get(); }
    public String getTipo() { return tipo.get(); }
    public String getEstado() { return estado.get(); }
}


