package com.example.spe_logistic.entities;

import java.util.Date;

public class Historial_Envios {

    private Integer id;
    private Date    fecha;
    private String  descripcion;
    private Integer cliente_id;
    private Integer envio_id;

    public Historial_Envios(Integer id, Date fecha, String descripcion, Integer cliente_id, Integer envio_id) {
        this.id = id;
        this.fecha = fecha;
        this.descripcion = descripcion;
        this.cliente_id = cliente_id;
        this.envio_id = envio_id;
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

    public Integer getCliente_id() {
        return cliente_id;
    }

    public void setCliente_id(Integer cliente_id) {
        this.cliente_id = cliente_id;
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

    public Integer getEnvio_id() {
        return envio_id;
    }

    public void setEnvio_id(Integer envio_id) {
        this.envio_id = envio_id;
    }
}
