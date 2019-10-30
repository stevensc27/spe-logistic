package com.example.spe_logistic.entities;

import java.util.Date;

public class Historial_Recogidas {

    private Integer id;
    private Date    fecha;
    private String  descripcion;
    private Integer cliente_id;
    private Integer recogida_id;

    public Historial_Recogidas(Integer id, Date fecha, String descripcion, Integer cliente_id, Integer recogida_id) {
        this.id = id;
        this.fecha = fecha;
        this.descripcion = descripcion;
        this.cliente_id = cliente_id;
        this.recogida_id = recogida_id;
    }

    public Integer getCliente_id() {
        return cliente_id;
    }

    public void setCliente_id(Integer cliente_id) {
        this.cliente_id = cliente_id;
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

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Integer getRecogida_id() {
        return recogida_id;
    }

    public void setRecogida_id(Integer recogida_id) {
        this.recogida_id = recogida_id;
    }
}
