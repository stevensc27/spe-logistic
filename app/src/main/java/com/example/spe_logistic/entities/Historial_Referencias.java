package com.example.spe_logistic.entities;

import java.util.Date;

public class Historial_Referencias {

    private Integer id;
    private Date    fecha;
    private String  descripcion;
    private Integer referencia_id;


    public Historial_Referencias(Integer id, Date fecha, Integer referencia_id, String descripcion) {
        this.id = id;
        this.fecha = fecha;
        this.referencia_id = referencia_id;
        this.descripcion = descripcion;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
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
