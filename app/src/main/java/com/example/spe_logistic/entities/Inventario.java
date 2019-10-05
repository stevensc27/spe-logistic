package com.example.spe_logistic.entities;

import java.util.Date;

public class Inventario {

    private Integer id;
    private Integer referencia_id;
    private Integer estado_id;
    private String  posicion;
    private String  serial;
    private Date    fecha_ingreso;
    private Integer envio_id;

    public Inventario(Integer id, Integer referencia_id, Integer estado_id, String posicion, String serial, Date fecha_ingreso, Integer envio_id) {
        this.id = id;
        this.referencia_id = referencia_id;
        this.estado_id = estado_id;
        this.posicion = posicion;
        this.serial = serial;
        this.fecha_ingreso = fecha_ingreso;
        this.envio_id = envio_id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getReferencia_id() {
        return referencia_id;
    }

    public void setReferencia_id(Integer referencia_id) {
        this.referencia_id = referencia_id;
    }

    public Integer getEstado_id() {
        return estado_id;
    }

    public void setEstado_id(Integer estado_id) {
        this.estado_id = estado_id;
    }

    public String getPosicion() {
        return posicion;
    }

    public void setPosicion(String posicion) {
        this.posicion = posicion;
    }

    public String getSerial() {
        return serial;
    }

    public void setSerial(String serial) {
        this.serial = serial;
    }

    public Date getFecha_ingreso() {
        return fecha_ingreso;
    }

    public void setFecha_ingreso(Date fecha_ingreso) {
        this.fecha_ingreso = fecha_ingreso;
    }

    public Integer getEnvio_id() {
        return envio_id;
    }

    public void setEnvio_id(Integer envio_id) {
        this.envio_id = envio_id;
    }
}
