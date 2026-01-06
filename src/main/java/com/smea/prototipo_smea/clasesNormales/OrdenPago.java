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
    private final StringProperty cliente;
    private final StringProperty direccion;
    private final StringProperty tipoTrabajo;
    private final  StringProperty prioridad;
    private final StringProperty tecnico;
    private final StringProperty estado;


    public OrdenPago(String codigo, String proveedor, LocalDate fechaEmision, double monto, String tipoPago, String observaciones, String cliente, String direccion, String tipoTrabajo, String prioridad, String tecnico, String estado) {
        this.codigo = new SimpleStringProperty(codigo);
        this.proveedor = new SimpleStringProperty(proveedor);
        this.fechaEmision = new SimpleObjectProperty<>(fechaEmision);
        this.monto = new SimpleDoubleProperty(monto);
        this.tipoPago = new SimpleStringProperty(tipoPago);
        this.observaciones = new SimpleStringProperty(observaciones);
        this.cliente = new SimpleStringProperty(cliente);
        this.direccion = new SimpleStringProperty(direccion);
        this.tipoTrabajo = new SimpleStringProperty(tipoTrabajo);
        this.prioridad =new SimpleStringProperty(prioridad);
        this.tecnico = new SimpleStringProperty(tecnico);
        this.estado = new SimpleStringProperty(estado);
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

    public String getObservaciones() {
        return observaciones.get();
    }

    public String getCliente() {
        return cliente.get();
    }

    public StringProperty clienteProperty() {
        return cliente;
    }

    public String getDireccion() {
        return direccion.get();
    }

    public StringProperty direccionProperty() {
        return direccion;
    }

    public String getTipoTrabajo() {
        return tipoTrabajo.get();
    }

    public StringProperty tipoTrabajoProperty() {
        return tipoTrabajo;
    }

    public String getPrioridad() {
        return prioridad.get();
    }

    public StringProperty prioridadProperty() {
        return prioridad;
    }

    public String getTecnico() {
        return tecnico.get();
    }

    public StringProperty tecnicoProperty() {
        return tecnico;
    }

    public String getEstado() {
        return estado.get();
    }

    public StringProperty estadoProperty() {
        return estado;
    }

    public void setCodigo(String codigo) {
        this.codigo.set(codigo);
    }

    public void setCliente(String cliente) {
        this.cliente.set(cliente);
    }

    public void setDireccion(String direccion) {
        this.direccion.set(direccion);
    }

    public void setTipoTrabajo(String tipoTrabajo) {
        this.tipoTrabajo.set(tipoTrabajo);
    }

    public void setPrioridad(String prioridad) {
        this.prioridad.set(prioridad);
    }

    public void setObservaciones(String observaciones) {
        this.observaciones.set(observaciones);
    }

    public void setMonto(double monto) {
        this.monto.set(monto);
    }

    public void setTipoPago(String tipoPago) {
        this.tipoPago.set(tipoPago);
    }

    public void setTecnico(String tecnico) {
        this.tecnico.set(tecnico);
    }

    public void setEstado(String estado) {
        this.estado.set(estado);
    }

    public void setFechaEmision(LocalDate fechaEmision) {
        this.fechaEmision.set(fechaEmision);
    }
}
