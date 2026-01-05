package com.smea.prototipo_smea.clasesNormales;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Rol {

    private final SimpleIntegerProperty id;
    private final SimpleStringProperty nombre;
    private final SimpleStringProperty descripcion;
    private final SimpleBooleanProperty activo;

    // ================== CONSTRUCTORES ==================

    public Rol(int id, String nombre, String descripcion, boolean activo) {
        this.id = new SimpleIntegerProperty(id);
        this.nombre = new SimpleStringProperty(nombre);
        this.descripcion = new SimpleStringProperty(descripcion);
        this.activo = new SimpleBooleanProperty(activo);
    }

    // ================== GETTERS ==================

    public int getId() {
        return id.get();
    }

    public String getNombre() {
        return nombre.get();
    }

    public String getDescripcion() {
        return descripcion.get();
    }

    public boolean isActivo() {
        return activo.get();
    }

    // ================== SETTERS ==================

    public void setNombre(String nombre) {
        this.nombre.set(nombre);
    }

    public void setDescripcion(String descripcion) {
        this.descripcion.set(descripcion);
    }

    public void setActivo(boolean activo) {
        this.activo.set(activo);
    }

    // ================== PROPERTIES (JavaFX) ==================

    public SimpleIntegerProperty idProperty() {
        return id;
    }

    public SimpleStringProperty nombreProperty() {
        return nombre;
    }

    public SimpleStringProperty descripcionProperty() {
        return descripcion;
    }

    public SimpleBooleanProperty activoProperty() {
        return activo;
    }

    // ================== UTIL ==================

    @Override
    public String toString() {
        return nombre.get();
    }
}
