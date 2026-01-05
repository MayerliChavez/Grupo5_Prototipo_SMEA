package com.smea.prototipo_smea.clasesNormales;


public class Sesion {

    private String usuario;
    private String rol;
    private String inicio;
    private String fin;
    private String ip;
    private String equipo;

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public void setEquipo(String equipo) {
        this.equipo = equipo;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public void setFin(String fin) {
        this.fin = fin;
    }

    public void setInicio(String inicio) {
        this.inicio = inicio;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    private String estado;

    public Sesion(String usuario, String rol, String inicio,
                  String fin, String ip, String equipo, String estado) {
        this.usuario = usuario;
        this.rol = rol;
        this.inicio = inicio;
        this.fin = fin;
        this.ip = ip;
        this.equipo = equipo;
        this.estado = estado;
    }

    public String getUsuario() { return usuario; }
    public String getRol() { return rol; }
    public String getInicio() { return inicio; }
    public String getFin() { return fin; }
    public String getIp() { return ip; }
    public String getEquipo() { return equipo; }
    public String getEstado() { return estado; }
}


