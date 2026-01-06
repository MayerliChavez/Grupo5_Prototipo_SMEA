package com.smea.prototipo_smea.clasesNormales;

import java.time.LocalDate;

public class RegistroTiempo {
    private String codigoOrden;      // Código de la orden de trabajo
    private String tecnico;          // Técnico responsable
    private LocalDate fecha;         // Fecha del registro
    private double horasTrabajadas;  // Tiempo invertido
    private String descripcion;      // Detalle del trabajo realizado

    public RegistroTiempo(String codigoOrden, String tecnico, LocalDate fecha, double horasTrabajadas, String descripcion) {
        this.codigoOrden = codigoOrden;
        this.tecnico = tecnico;
        this.fecha = fecha;
        this.horasTrabajadas = horasTrabajadas;
        this.descripcion = descripcion;
    }

    // Getters y setters
    public String getCodigoOrden() { return codigoOrden; }
    public void setCodigoOrden(String codigoOrden) { this.codigoOrden = codigoOrden; }

    public String getTecnico() { return tecnico; }
    public void setTecnico(String tecnico) { this.tecnico = tecnico; }

    public LocalDate getFecha() { return fecha; }
    public void setFecha(LocalDate fecha) { this.fecha = fecha; }

    public double getHorasTrabajadas() { return horasTrabajadas; }
    public void setHorasTrabajadas(double horasTrabajadas) { this.horasTrabajadas = horasTrabajadas; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
}

