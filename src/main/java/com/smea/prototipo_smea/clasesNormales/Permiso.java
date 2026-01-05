package com.smea.prototipo_smea.clasesNormales;

public class Permiso {

    private int id;
    private String rol;
    private String modulo;
    private String accion;
    private boolean permitido;

    public Permiso(int id, String rol, String modulo, String accion, boolean permitido) {
        this.id = id;
        this.rol = rol;
        this.modulo = modulo;
        this.accion = accion;
        this.permitido = permitido;
    }

    public int getId() {
        return id;
    }

    public String getRol() {
        return rol;
    }

    public String getModulo() {
        return modulo;
    }

    public String getAccion() {
        return accion;
    }

    public boolean isPermitido() {
        return permitido;
    }

    public void setPermitido(boolean permitido) {
        this.permitido = permitido;
    }
}

