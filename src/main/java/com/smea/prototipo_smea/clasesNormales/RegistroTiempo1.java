package com.smea.prototipo_smea.clasesNormales;

import javafx.beans.property.*;
import java.time.LocalDate;

public class RegistroTiempo1 {

    private final StringProperty empleado;
    private final StringProperty orden;
    private final ObjectProperty<LocalDate> fecha;
    private final StringProperty actividad;
    private final DoubleProperty horas;

    public RegistroTiempo1(String empleado, String orden, LocalDate fecha,
                          String actividad, double horas) {
        this.empleado = new SimpleStringProperty(empleado);
        this.orden = new SimpleStringProperty(orden);
        this.fecha = new SimpleObjectProperty<>(fecha);
        this.actividad = new SimpleStringProperty(actividad);
        this.horas = new SimpleDoubleProperty(horas);
    }

    public String getEmpleado() { return empleado.get(); }
    public StringProperty empleadoProperty() { return empleado; }

    public String getOrden() { return orden.get(); }
    public StringProperty ordenProperty() { return orden; }

    public LocalDate getFecha() { return fecha.get(); }
    public ObjectProperty<LocalDate> fechaProperty() { return fecha; }

    public String getActividad() { return actividad.get(); }
    public StringProperty actividadProperty() { return actividad; }

    public double getHoras() { return horas.get(); }
    public DoubleProperty horasProperty() { return horas; }
}
