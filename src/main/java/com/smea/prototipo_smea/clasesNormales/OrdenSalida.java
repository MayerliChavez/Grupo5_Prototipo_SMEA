package com.smea.prototipo_smea.clasesNormales;

import java.time.LocalDate;

public class OrdenSalida {

    private String codigo;
    private String responsable;
    private LocalDate fechaEmision;
    private String motivo;
    private String destino;
    private String descripcion;
    private String estado;

    public OrdenSalida(String codigo, String responsable,
                       LocalDate fechaEmision, String motivo,
                       String destino, String descripcion, String estado) {
        this.codigo = codigo;
        this.responsable = responsable;
        this.fechaEmision = fechaEmision;
        this.motivo = motivo;
        this.destino = destino;
        this.descripcion = descripcion;
        this.estado = estado;
    }

    public String getCodigo() { return codigo; }
    public String getResponsable() { return responsable; }
    public LocalDate getFechaEmision() { return fechaEmision; }
    public String getMotivo() { return motivo; }
    public String getDestino() { return destino; }
    public String getDescripcion() { return descripcion; }
    public String getEstado() { return estado; }
}

