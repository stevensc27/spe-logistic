package com.example.spe_logistic.entities;

import java.util.Date;

public class Historial_Inventario {

    private Integer id;
    private Date    fecha;
    private Integer inventario_id;
    private Integer cambio_id;

    public Historial_Inventario(Integer id, Date fecha, Integer inventario_id, Integer cambio_id) {
        this.id = id;
        this.fecha = fecha;
        this.inventario_id = inventario_id;
        this.cambio_id = cambio_id;
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

    public Integer getInventario_id() {
        return inventario_id;
    }

    public void setInventario_id(Integer inventario_id) {
        this.inventario_id = inventario_id;
    }

    public Integer getCambio_id() {
        return cambio_id;
    }

    public void setCambio_id(Integer cambio_id) {
        this.cambio_id = cambio_id;
    }
}
