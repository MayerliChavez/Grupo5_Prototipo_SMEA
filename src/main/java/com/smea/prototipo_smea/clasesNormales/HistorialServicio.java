package com.smea.prototipo_smea.clasesNormales;

import javafx.beans.property.*;

public class HistorialServicio {

    private final StringProperty nombreCliente;
    private final StringProperty tipoEquipo;
    private final StringProperty numeroSerie;
    private final StringProperty estado;
    private final StringProperty cedulaCliente;
    private final StringProperty fecha;
    private final StringProperty tipoServicio;
    private final StringProperty tecnico;
    private final DoubleProperty costo;

    public HistorialServicio(String nombreCliente,
                             String tipoEquipo,
                             String numeroSerie,
                             String estado,
                             String cedulaCliente,
                             String fecha,
                             String tipoServicio,
                             String tecnico,
                             double costo) {

        this.nombreCliente = new SimpleStringProperty(nombreCliente);
        this.tipoEquipo = new SimpleStringProperty(tipoEquipo);
        this.numeroSerie = new SimpleStringProperty(numeroSerie);
        this.estado = new SimpleStringProperty(estado);
        this.cedulaCliente = new SimpleStringProperty(cedulaCliente);
        this.fecha = new SimpleStringProperty(fecha);
        this.tipoServicio = new SimpleStringProperty(tipoServicio);
        this.tecnico = new SimpleStringProperty(tecnico);
        this.costo = new SimpleDoubleProperty(costo);
    }

    public StringProperty nombreClienteProperty() {
        return nombreCliente;
    }

    public StringProperty tipoEquipoProperty() {
        return tipoEquipo;
    }

    public StringProperty numeroSerieProperty() {
        return numeroSerie;
    }

    public StringProperty estadoProperty() {
        return estado;
    }

    public String getNombreCliente() {
        return nombreCliente.get();
    }

    public String getTipoEquipo() {
        return tipoEquipo.get();
    }

    public String getNumeroSerie() {
        return numeroSerie.get();
    }

    public String getEstado() {
        return estado.get();
    }

    public String getCedulaCliente() {
        return cedulaCliente.get();
    }

    public StringProperty fechaProperty() {
        return fecha;
    }

    public StringProperty tipoServicioProperty() {
        return tipoServicio;
    }

    public StringProperty tecnicoProperty() {
        return tecnico;
    }

    public DoubleProperty costoProperty() {
        return costo;
    }

    public StringProperty cedulaClienteProperty() {
        return cedulaCliente;
    }
}
