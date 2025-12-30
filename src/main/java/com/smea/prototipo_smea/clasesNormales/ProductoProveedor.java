package com.smea.prototipo_smea.clasesNormales;

import javafx.beans.property.*;

public class ProductoProveedor {

    private final SimpleStringProperty proveedor;
    private final SimpleStringProperty nombreProducto;
    private final SimpleDoubleProperty costo;
    private final SimpleStringProperty estado;
    private final SimpleStringProperty categoria;
    private final SimpleStringProperty descripcionDelProducto;

    public ProductoProveedor(String proveedor, String nombreProducto,
                             double costo, String estado, String categoria, String descripcionDelProducto) {
        this.proveedor = new SimpleStringProperty(proveedor);
        this.nombreProducto = new SimpleStringProperty(nombreProducto);
        this.costo = new SimpleDoubleProperty(costo);
        this.estado = new SimpleStringProperty(estado);
        this.categoria = new SimpleStringProperty(categoria);
        this.descripcionDelProducto = new SimpleStringProperty(descripcionDelProducto);
    }

    public String getProveedor() { return proveedor.get(); }
    public String getNombreProducto() { return nombreProducto.get(); }
    public double getCosto() { return costo.get(); }
    public String getEstado() { return estado.get(); }
    public String getDescripcionDelProducto() {return descripcionDelProducto.get();}
    public String getCategoria() {return categoria.get();}

    public void setEstado(String estado) {
        this.estado.set(estado);
    }

    public StringProperty proveedorProperty() { return proveedor; }
    public StringProperty nombreProductoProperty() { return nombreProducto; }
    public DoubleProperty costoProperty() { return costo; }
    public StringProperty estadoProperty() { return estado; }
}
