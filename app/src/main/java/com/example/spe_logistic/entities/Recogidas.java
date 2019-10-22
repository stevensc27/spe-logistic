package com.example.spe_logistic.entities;

import java.util.Date;

public class Recogidas {

    private Integer id;
    private Date    fecha;
    private Integer cliente_id;
    private String  direccion;
    private Integer cantidad_cajas;
    private Integer alto_caja;
    private Integer ancho_caja;
    private Integer largo_caja;
    private Integer peso;
    private String  descripcion_contenido;
    private Integer valor_declarado;
    private Integer estado_id;

    public Recogidas(Integer id, Date fecha, Integer cliente_id, String direccion, Integer cantidad_cajas, Integer alto_caja, Integer ancho_caja, Integer largo_caja, Integer peso, String descripcion_contenido, Integer valor_declarado, Integer estado_id) {
        this.id = id;
        this.fecha = fecha;
        this.cliente_id = cliente_id;
        this.direccion = direccion;
        this.cantidad_cajas = cantidad_cajas;
        this.alto_caja = alto_caja;
        this.ancho_caja = ancho_caja;
        this.largo_caja = largo_caja;
        this.peso = peso;
        this.descripcion_contenido = descripcion_contenido;
        this.valor_declarado = valor_declarado;
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

    public Integer getCantidad_cajas() {
        return cantidad_cajas;
    }

    public void setCantidad_cajas(Integer cantidad_cajas) {
        this.cantidad_cajas = cantidad_cajas;
    }

    public Integer getAlto_caja() {
        return alto_caja;
    }

    public void setAlto_caja(Integer alto_caja) {
        this.alto_caja = alto_caja;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public Integer getAncho_caja() {
        return ancho_caja;
    }

    public void setAncho_caja(Integer ancho_caja) {
        this.ancho_caja = ancho_caja;
    }

    public Integer getLargo_caja() {
        return largo_caja;
    }

    public void setLargo_caja(Integer largo_caja) {
        this.largo_caja = largo_caja;
    }

    public Integer getPeso() {
        return peso;
    }

    public void setPeso(Integer peso) {
        this.peso = peso;
    }

    public String getDescripcion_contenido() {
        return descripcion_contenido;
    }

    public void setDescripcion_contenido(String descripcion_contenido) {
        this.descripcion_contenido = descripcion_contenido;
    }

    public Integer getValor_declarado() {
        return valor_declarado;
    }

    public void setValor_declarado(Integer valor_declarado) {
        this.valor_declarado = valor_declarado;
    }

    public Integer getEstado_id() {
        return estado_id;
    }

    public void setEstado_id(Integer estado_id) {
        this.estado_id = estado_id;
    }
}
