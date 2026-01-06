package com.smea.prototipo_smea.clasesNormales;

public class MovimientoMantenimiento {

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public String getEquipo() {
        return equipo;
    }

    public void setEquipo(String equipo) {
        this.equipo = equipo;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getAccion() {
        return accion;
    }

    public void setAccion(String accion) {
        this.accion = accion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    private String usuario;
    private String rol;
    private String equipo;
    private String tipo;
    private String accion;
    private String estado;
    private String fecha;
    private String observacion;

    public MovimientoMantenimiento(String usuario, String rol,
                                   String equipo, String tipo,
                                   String accion, String estado,
                                   String fecha, String observacion) {
        this.usuario = usuario;
        this.rol = rol;
        this.equipo = equipo;
        this.tipo = tipo;
        this.accion = accion;
        this.estado = estado;
        this.fecha = fecha;
        this.observacion = observacion;
    }

    // getters
}

