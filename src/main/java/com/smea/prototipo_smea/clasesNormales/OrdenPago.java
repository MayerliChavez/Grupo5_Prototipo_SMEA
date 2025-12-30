package com.smea.prototipo_smea.clasesNormales;

import javafx.beans.property.*;
import javafx.beans.value.ObservableValue;

import java.time.LocalDate;

public class OrdenPago {

    private final StringProperty codigo;
    private final StringProperty proveedor;
    private final ObjectProperty<LocalDate> fechaEmision;
    private final DoubleProperty monto;
    private final StringProperty tipoPago;
    private final StringProperty observaciones;


    public OrdenPago(String codigo, String proveedor, LocalDate fechaEmision, double monto, String tipoPago, String observaciones) {
        this.codigo = new SimpleStringProperty(codigo);
        this.proveedor = new SimpleStringProperty(proveedor);
        this.fechaEmision = new SimpleObjectProperty<>(fechaEmision);
        this.monto = new SimpleDoubleProperty(monto);
        this.tipoPago = new SimpleStringProperty(tipoPago);
        this.observaciones = new SimpleStringProperty(observaciones);
    }

    public String getCodigo() { return codigo.get(); }
    public StringProperty codigoProperty() { return codigo; }

    public String getProveedor() { return proveedor.get(); }
    public StringProperty proveedorProperty() { return proveedor; }

    public LocalDate getFechaEmision() { return fechaEmision.get(); }
    public ObjectProperty<LocalDate> fechaEmisionProperty() { return fechaEmision; }
    public String getTipoPago() { return tipoPago.get(); }

    public double getMonto() { return monto.get(); }
    public DoubleProperty montoProperty() { return monto; }

    public ObservableValue<LocalDate> fechaProperty() {
        return fechaEmision;
    }

    public ObservableValue<String> tipoPagoProperty() {
        return tipoPago;
    }

    public ObservableValue<String> observacionesProperty() {
        return observaciones;
    }
}
