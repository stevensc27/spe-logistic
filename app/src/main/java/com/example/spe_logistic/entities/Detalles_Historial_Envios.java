package com.example.spe_logistic.entities;

public class Detalles_Historial_Envios {

    private Integer id;
    private Integer historial_envios_id;
    private Integer referencia_id;
    private String  descripcion;

    public Detalles_Historial_Envios(Integer id, Integer historial_envios_id, Integer referencia_id, String descripcion) {
        this.id = id;
        this.historial_envios_id = historial_envios_id;
        this.referencia_id = referencia_id;
        this.descripcion = descripcion;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getHistorial_envios_id() {
        return historial_envios_id;
    }

    public void setHistorial_envios_id(Integer historial_envios_id) {
        this.historial_envios_id = historial_envios_id;
    }

    public Integer getReferencia_id() {
        return referencia_id;
    }

    public void setReferencia_id(Integer referencia_id) {
        this.referencia_id = referencia_id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
