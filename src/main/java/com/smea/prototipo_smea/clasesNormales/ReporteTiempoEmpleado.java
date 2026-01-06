package com.smea.prototipo_smea.clasesNormales;

import javafx.beans.property.*;
import java.time.LocalDate;

public class ReporteTiempoEmpleado {

    private StringProperty empleado;
    private StringProperty orden;
    private ObjectProperty<LocalDate> fecha;
    private DoubleProperty horas;
    private StringProperty actividad;

    public ReporteTiempoEmpleado(String empleado, String orden,
                                 LocalDate fecha, double horas, String actividad) {
        this.empleado = new SimpleStringProperty(empleado);
        this.orden = new SimpleStringProperty(orden);
        this.fecha = new SimpleObjectProperty<>(fecha);
        this.horas = new SimpleDoubleProperty(horas);
        this.actividad = new SimpleStringProperty(actividad);
    }

    public StringProperty empleadoProperty() { return empleado; }
    public StringProperty ordenProperty() { return orden; }
    public ObjectProperty<LocalDate> fechaProperty() { return fecha; }
    public DoubleProperty horasProperty() { return horas; }
    public StringProperty actividadProperty() { return actividad; }

    public LocalDate getFecha() { return fecha.get(); }
    public String getEmpleado() { return empleado.get(); }
}

