package com.smea.prototipo_smea.clasesNormales;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class ServicioHistorial {

    private final StringProperty codigoServicio;
    private final StringProperty cliente;
    private final StringProperty cedulaCliente;
    private final StringProperty equipo;
    private final StringProperty tipoServicio;
    private final StringProperty fecha;
    private final StringProperty tecnico;
    private final StringProperty estado;

    // ===== CONSTRUCTOR =====
    public ServicioHistorial(
            String codigoServicio,
            String cliente,
            String cedulaCliente,
            String equipo,
            String tipoServicio,
            String fecha,
            String tecnico,
            String estado
    ) {
        this.codigoServicio = new SimpleStringProperty(codigoServicio);
        this.cliente = new SimpleStringProperty(cliente);
        this.cedulaCliente = new SimpleStringProperty(cedulaCliente);
        this.equipo = new SimpleStringProperty(equipo);
        this.tipoServicio = new SimpleStringProperty(tipoServicio);
        this.fecha = new SimpleStringProperty(fecha);
        this.tecnico = new SimpleStringProperty(tecnico);
        this.estado = new SimpleStringProperty(estado);
    }

    // ===== GETTERS =====
    public String getCodigoServicio() {
        return codigoServicio.get();
    }

    public String getCliente() {
        return cliente.get();
    }

    public String getCedulaCliente() {
        return cedulaCliente.get();
    }

    public String getEquipo() {
        return equipo.get();
    }

    public String getTipoServicio() {
        return tipoServicio.get();
    }

    public String getFecha() {
        return fecha.get();
    }

    public String getTecnico() {
        return tecnico.get();
    }

    public String getEstado() {
        return estado.get();
    }

    // ===== SETTERS =====
    public void setCodigoServicio(String codigoServicio) {
        this.codigoServicio.set(codigoServicio);
    }

    public void setCliente(String cliente) {
        this.cliente.set(cliente);
    }

    public void setCedulaCliente(String cedulaCliente) {
        this.cedulaCliente.set(cedulaCliente);
    }

    public void setEquipo(String equipo) {
        this.equipo.set(equipo);
    }

    public void setTipoServicio(String tipoServicio) {
        this.tipoServicio.set(tipoServicio);
    }

    public void setFecha(String fecha) {
        this.fecha.set(fecha);
    }

    public void setTecnico(String tecnico) {
        this.tecnico.set(tecnico);
    }

    public void setEstado(String estado) {
        this.estado.set(estado);
    }

    // ===== PROPERTIES (JavaFX) =====
    public StringProperty codigoServicioProperty() {
        return codigoServicio;
    }

    public StringProperty clienteProperty() {
        return cliente;
    }

    public StringProperty cedulaClienteProperty() {
        return cedulaCliente;
    }

    public StringProperty equipoProperty() {
        return equipo;
    }

    public StringProperty tipoServicioProperty() {
        return tipoServicio;
    }

    public StringProperty fechaProperty() {
        return fecha;
    }

    public StringProperty tecnicoProperty() {
        return tecnico;
    }

    public StringProperty estadoProperty() {
        return estado;
    }
}
