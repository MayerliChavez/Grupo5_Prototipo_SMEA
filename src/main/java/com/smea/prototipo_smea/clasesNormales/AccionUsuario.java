package com.smea.prototipo_smea.clasesNormales;

public class AccionUsuario {

    private String usuario;
    private String rol;
    private String modulo;
    private String accion;
    private String fecha;

    public AccionUsuario(String usuario, String rol, String modulo, String accion, String fecha) {
        this.usuario = usuario;
        this.rol = rol;
        this.modulo = modulo;
        this.accion = accion;
        this.fecha = fecha;
    }

    public String getUsuario() { return usuario; }
    public String getRol() { return rol; }
    public String getModulo() { return modulo; }
    public String getAccion() { return accion; }
    public String getFecha() { return fecha; }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public void setAccion(String accion) {
        this.accion = accion;
    }

    public void setModulo(String modulo) {
        this.modulo = modulo;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }
}
