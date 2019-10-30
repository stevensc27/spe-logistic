package com.example.spe_logistic.entities;

import java.util.Date;

public class Historial_Referencias {

    private Integer id;
    private Date    fecha;
    private String  descripcion;
    private Integer cliente_id;
    private Integer referencia_id;

    public Historial_Referencias(Integer id, Date fecha, String descripcion, Integer cliente_id, Integer referencia_id) {
        this.id = id;
        this.fecha = fecha;
        this.descripcion = descripcion;
        this.cliente_id = cliente_id;
        this.referencia_id = referencia_id;
    }

    public Integer getId() {
        return id;
    }

    public Integer getCliente_id() {
        return cliente_id;
    }

    public void setCliente_id(Integer cliente_id) {
        this.cliente_id = cliente_id;
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
