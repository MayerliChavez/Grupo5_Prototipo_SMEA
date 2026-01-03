package com.smea.prototipo_smea.clasesNormales;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class ElementoCatalogo {

    private StringProperty codigo;
    private StringProperty nombre;
    private StringProperty tipoElemento;
    private IntegerProperty cantidad;
    private StringProperty descripcion;
    private StringProperty ubicacion;
    private StringProperty fechaModificacion;

    public String getCategoria() {
        return categoria.get();
    }

    public StringProperty categoriaProperty() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria.set(categoria);
    }

    private StringProperty categoria;
    private StringProperty estado;


    public  ElementoCatalogo(String codigo, String nombre, String tipoElemento,
                                 int cantidad, String descripcion, String ubicacion,
                                 String fechaModificacion, String categoria, String estado) {
        this.codigo = new SimpleStringProperty(codigo);
        this.nombre = new SimpleStringProperty(nombre);
        this.tipoElemento = new SimpleStringProperty(tipoElemento);
        this.cantidad = new SimpleIntegerProperty(cantidad);
        this.descripcion = new SimpleStringProperty(descripcion);
        this.ubicacion = new SimpleStringProperty(ubicacion);
        this.fechaModificacion = new SimpleStringProperty(fechaModificacion);
        this.categoria = new SimpleStringProperty(categoria);
        this.estado = new SimpleStringProperty(estado);
    }

    public String getCodigo() {
        return codigo.get();
    }

    public StringProperty codigoProperty() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo.set(codigo);
    }

    public String getNombre() {
        return nombre.get();
    }

    public StringProperty nombreProperty() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre.set(nombre);
    }

    public String getTipoElemento() {
        return tipoElemento.get();
    }

    public StringProperty tipoElementoProperty() {
        return tipoElemento;
    }

    public void setTipoElemento(String tipoElemento) {
        this.tipoElemento.set(tipoElemento);
    }

    public int getCantidad() {
        return cantidad.get();
    }

    public IntegerProperty cantidadProperty() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad.set(cantidad);
    }

    public String getDescripcion() {
        return descripcion.get();
    }

    public StringProperty descripcionProperty() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion.set(descripcion);
    }

    public String getUbicacion() {
        return ubicacion.get();
    }

    public StringProperty ubicacionProperty() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion.set(ubicacion);
    }

    public String getFechaModificacion() {
        return fechaModificacion.get();
    }

    public StringProperty fechaModificacionProperty() {
        return fechaModificacion;
    }

    public void setFechaModificacion(String fechaModificacion) {
        this.fechaModificacion.set(fechaModificacion);
    }

    public String getEstado() {
        return estado.get();
    }

    public StringProperty estadoProperty() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado.set(estado);
    }

}
