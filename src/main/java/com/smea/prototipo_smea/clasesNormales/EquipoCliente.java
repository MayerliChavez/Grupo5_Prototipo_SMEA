package com.smea.prototipo_smea.clasesNormales;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class EquipoCliente {

    private final StringProperty cliente;
    private final StringProperty tipoEquipo;
    private final StringProperty marca;
    private final StringProperty modelo;
    private final StringProperty numeroSerie;
    private final StringProperty estado;
    private final StringProperty resultados;

    public EquipoCliente(String cliente, String tipoEquipo,
                         String marca, String modelo,
                         String numeroSerie, String estado, String resultados) {
        this.cliente = new SimpleStringProperty(cliente);
        this.tipoEquipo = new SimpleStringProperty(tipoEquipo);
        this.marca = new SimpleStringProperty(marca);
        this.modelo = new SimpleStringProperty(modelo);
        this.numeroSerie = new SimpleStringProperty(numeroSerie);
        this.estado = new SimpleStringProperty(estado);
        this.resultados = new  SimpleStringProperty(resultados);
    }

    public String getCliente() {
        return cliente.get();
    }

    public void setCliente(String cliente) {
        this.cliente.set(cliente);
    }

    public String getTipoEquipo() {
        return tipoEquipo.get();
    }
    public void setTipoEquipo(String tipoEquipo) {
        this.tipoEquipo.set(tipoEquipo);
    }

    public String getMarca() {
        return marca.get();
    }
    public void setMarca(String marca) {
        this.marca.set(marca);
    }

    public String getModelo() {
        return modelo.get();
    }
    public void setModelo(String modelo) {
        this.modelo.set(modelo);
    }

    public String getNumeroSerie() {
        return numeroSerie.get();
    }
    public void setNumeroSerie(String numeroSerie) {
        this.numeroSerie.set(numeroSerie);
    }

    public String getResultados(){
        return resultados.get();
    }

    public void setResultados(String resultados) {
        this.resultados.set(resultados);
    }

    public StringProperty clienteProperty() { return cliente; }
    public StringProperty tipoEquipoProperty() { return tipoEquipo; }
    public StringProperty marcaProperty() { return marca; }
    public StringProperty modeloProperty() { return modelo; }
    public StringProperty numeroSerieProperty() { return numeroSerie; }
    public StringProperty estadoProperty() { return estado; }

    public String getEstado() { return estado.get(); }
    public void setEstado(String estado) { this.estado.set(estado); }
}
