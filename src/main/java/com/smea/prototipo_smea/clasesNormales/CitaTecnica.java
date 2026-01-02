package com.smea.prototipo_smea.clasesNormales;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class CitaTecnica {

    private final StringProperty codigo;
    private final StringProperty cliente;
    private final StringProperty fecha;
    private final StringProperty hora;
    private final StringProperty estado;
    private final StringProperty tipoMantenimiento;
    private final StringProperty tecnicoAsignado;

    public CitaTecnica(String codigo, String cliente, String fecha, String hora, String estado, String tipoMantenimiento, String tecnicoAsignado) {
        this.codigo = new SimpleStringProperty(codigo);
        this.cliente = new SimpleStringProperty(cliente);
        this.fecha = new SimpleStringProperty(fecha);
        this.hora = new SimpleStringProperty(hora);
        this.estado = new SimpleStringProperty(estado);
        this.tipoMantenimiento = new SimpleStringProperty(tipoMantenimiento);
        this.tecnicoAsignado = new SimpleStringProperty(tecnicoAsignado);
    }

    public String getCodigo() { return codigo.get(); }
    public StringProperty codigoProperty() { return codigo; }

    public String getCliente() { return cliente.get(); }
    public StringProperty clienteProperty() { return cliente; }

    public String getFecha() { return fecha.get(); }
    public StringProperty fechaProperty() { return fecha; }

    public String getHora() { return hora.get(); }
    public StringProperty horaProperty() { return hora; }

    public String getEstado() { return estado.get(); }
    public StringProperty estadoProperty() { return estado; }

    public String getTipoMantenimiento() { return tipoMantenimiento.get(); }
    public StringProperty tipoMantenimientoProperty() { return tipoMantenimiento; }
    public String getTecnicoAsignado() { return tecnicoAsignado.get(); }

    public void setTecnicoAsignado(String tecnicoAsignado) {this.tecnicoAsignado.set(tecnicoAsignado);}
    public void setCodigo(String codigo) { this.codigo.set(codigo); }
    public void setCliente(String cliente) { this.cliente.set(cliente); }
    public void setFecha(String fecha) { this.fecha.set(fecha); }
    public void setHora(String hora) { this.hora.set(hora); }
    public void setEstado(String estado) { this.estado.set(estado); }
    public void setTipoMantenimiento(String tipoMantenimiento) { this.tipoMantenimiento.set(tipoMantenimiento); }

}
