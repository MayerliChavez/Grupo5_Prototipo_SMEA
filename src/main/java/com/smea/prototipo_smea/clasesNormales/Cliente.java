package com.smea.prototipo_smea.clasesNormales;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Cliente {

    private final StringProperty nombre;
    private final StringProperty apellido;
    private final StringProperty cedulaRuc;
    private final StringProperty direccion;
    private final StringProperty telefono;
    private final StringProperty email;
    private final StringProperty estado;


    public Cliente(String nombre, String apellido, String cedula, String direccion, String telefono, String email, String estado) {
        this.nombre = new SimpleStringProperty(nombre);
        this.apellido = new SimpleStringProperty(apellido);
        this.cedulaRuc = new SimpleStringProperty(cedula);
        this.direccion = new SimpleStringProperty(direccion);
        this.telefono = new SimpleStringProperty(telefono);
        this.email = new SimpleStringProperty(email);
        this.estado = new SimpleStringProperty(estado);
    }

    // ===== Getters =====
    public String getNombre() {
        return nombre.get();
    }

    public String getCedulaRuc() {
        return cedulaRuc.get();
    }

    public String getTelefono() {
        return telefono.get();
    }

    public String getEstado() {
        return estado.get();
    }

    // ===== Properties =====
    public StringProperty nombreProperty() {
        return nombre;
    }

    public StringProperty cedulaRucProperty() {
        return cedulaRuc;
    }

    public StringProperty telefonoProperty() {
        return telefono;
    }

    public StringProperty estadoProperty() {
        return estado;
    }
}
