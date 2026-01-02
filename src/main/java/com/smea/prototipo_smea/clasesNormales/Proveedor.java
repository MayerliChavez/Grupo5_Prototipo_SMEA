package com.smea.prototipo_smea.clasesNormales;

import javafx.beans.property.SimpleStringProperty;

public class Proveedor {



    private final SimpleStringProperty nombre;
    private final SimpleStringProperty razonSocial;
    private final SimpleStringProperty ruc;
    private final SimpleStringProperty estado;
    private  final SimpleStringProperty telefono;
    private final SimpleStringProperty email;
    private final SimpleStringProperty direccion;

    public Proveedor(String nombre, String razonSocial, String ruc, String estado, String telefono, String email, String direccion) {
        this.nombre = new SimpleStringProperty(nombre);
        this.razonSocial = new SimpleStringProperty(razonSocial);
        this.ruc = new SimpleStringProperty(ruc);
        this.estado = new SimpleStringProperty(estado);
        this.telefono = new SimpleStringProperty(telefono);
        this.email = new SimpleStringProperty(email);
        this.direccion = new SimpleStringProperty(direccion);
    }

    public String getNombre() { return nombre.get(); }
    public String getRazonSocial() { return razonSocial.get(); }
    public String getRuc() { return ruc.get(); }
    public String getEstado() { return estado.get(); }
    public String getTelefono() { return telefono.get(); }
    public String getEmail() { return email.get(); }
    public String getDireccion() { return direccion.get(); }

    public void setEstado(String nuevoEstado) {
        estado.set(nuevoEstado);
    }

    public void setTelefono(String nuevoTelefono) {
        telefono.set(nuevoTelefono);
    }

    public void setEmail(String nuevoEmail) {
        email.set(nuevoEmail);
    }

    public void setDireccion(String nuevoDireccion) {
        direccion.set(nuevoDireccion);
    }

    public void setRazonSocial(String nuevoRazonSocial) {
        razonSocial.set(nuevoRazonSocial);
    }

    public void setRuc(String nuevoRuc) {
        ruc.set(nuevoRuc);
    }

    public void setNombre(String nuevoNombre) {
        nombre.set(nuevoNombre);
    }

    public SimpleStringProperty nombreProperty() { return nombre; }
    public SimpleStringProperty razonSocialProperty() { return razonSocial; }
    public SimpleStringProperty rucProperty() { return ruc; }
    public SimpleStringProperty estadoProperty() { return estado; }
}
