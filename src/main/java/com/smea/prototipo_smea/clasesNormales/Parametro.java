package com.smea.prototipo_smea.clasesNormales;

public class Parametro {

    private String nombre;
    private String valor;
    private String tipo;
    private String descripcion;

    public Parametro(String nombre, String valor, String tipo, String descripcion) {
        this.nombre = nombre;
        this.valor = valor;
        this.tipo = tipo;
        this.descripcion = descripcion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

}

