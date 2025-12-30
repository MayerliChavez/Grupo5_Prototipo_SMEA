package com.smea.prototipo_smea.clasesNormales;

import javafx.beans.property.SimpleStringProperty;

public class DocumentoRespaldo {

    private final SimpleStringProperty nombre;
    private final SimpleStringProperty tipo;
    private final SimpleStringProperty ruta;

    public DocumentoRespaldo(String nombre, String tipo, String ruta) {
        this.nombre = new SimpleStringProperty(nombre);
        this.tipo = new SimpleStringProperty(tipo);
        this.ruta = new SimpleStringProperty(ruta);
    }

    public String getNombre() {
        return nombre.get();
    }

    public String getTipo() {
        return tipo.get();
    }

    public String getRuta() {
        return ruta.get();
    }
}

