package com.example.spe_logistic.entities;

import java.util.Date;

public class Despachos {

    private Integer id;
    private Date    fecha;
    private String  guia;
    private Integer peso;
    private Integer cajas;

    public Despachos(Integer id, Date fecha, String guia, Integer peso, Integer cajas) {
        this.id = id;
        this.fecha = fecha;
        this.guia = guia;
        this.peso = peso;
        this.cajas = cajas;
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

    public String getGuia() {
        return guia;
    }

    public void setGuia(String guia) {
        this.guia = guia;
    }

    public Integer getPeso() {
        return peso;
    }

    public void setPeso(Integer peso) {
        this.peso = peso;
    }

    public Integer getCajas() {
        return cajas;
    }

    public void setCajas(Integer cajas) {
        this.cajas = cajas;
    }
}
