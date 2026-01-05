package com.smea.prototipo_smea.clasesNormales;

import javafx.beans.property.*;

public class PermisoAcceso {

    private final StringProperty modulo;
    private final StringProperty subModulo;
    private final StringProperty permiso;

    public boolean isAcceso() {
        return acceso.get();
    }

    public String getPermiso() {
        return permiso.get();
    }

    public String getSubModulo() {
        return subModulo.get();
    }

    public String getModulo() {
        return modulo.get();
    }

    private final BooleanProperty acceso;

    public PermisoAcceso(String modulo, String subModulo, String permiso, boolean acceso) {
        this.modulo = new SimpleStringProperty(modulo);
        this.subModulo = new SimpleStringProperty(subModulo);
        this.permiso = new SimpleStringProperty(permiso);
        this.acceso = new SimpleBooleanProperty(acceso);
    }

    public StringProperty moduloProperty() { return modulo; }
    public StringProperty subModuloProperty() { return subModulo; }
    public StringProperty permisoProperty() { return permiso; }
    public BooleanProperty accesoProperty() { return acceso; }
}

