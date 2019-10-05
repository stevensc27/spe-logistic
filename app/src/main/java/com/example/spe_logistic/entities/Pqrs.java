package com.example.spe_logistic.entities;

import java.util.Date;

public class Pqrs {

    private Integer id;
    private Date    fecha;
    private Integer cliente_id;
    private String  descripcion;
    private Integer categoria_id;
    private Integer estado_id;

    public Pqrs(Integer id, Date fecha, Integer cliente_id, String descripcion, Integer categoria_id, Integer estado_id) {
        this.id = id;
        this.fecha = fecha;
        this.cliente_id = cliente_id;
        this.descripcion = descripcion;
        this.categoria_id = categoria_id;
        this.estado_id = estado_id;
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

    public Integer getCliente_id() {
        return cliente_id;
    }

    public void setCliente_id(Integer cliente_id) {
        this.cliente_id = cliente_id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Integer getCategoria_id() {
        return categoria_id;
    }

    public void setCategoria_id(Integer categoria_id) {
        this.categoria_id = categoria_id;
    }

    public Integer getEstado_id() {
        return estado_id;
    }

    public void setEstado_id(Integer estado_id) {
        this.estado_id = estado_id;
    }
}
