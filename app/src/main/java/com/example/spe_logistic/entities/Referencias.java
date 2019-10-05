package com.example.spe_logistic.entities;

public class Referencias {

    private Integer id;
    private String  nombre;
    private Integer valor;
    private String  codigo_barras;
    private Integer unidades_empaque;
    private Integer cliente_id;

    public Referencias(Integer id, String nombre, Integer valor, String codigo_barras, Integer unidades_empaque, Integer cliente_id) {
        this.id = id;
        this.nombre = nombre;
        this.valor = valor;
        this.codigo_barras = codigo_barras;
        this.unidades_empaque = unidades_empaque;
        this.cliente_id = cliente_id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getValor() {
        return valor;
    }

    public void setValor(Integer valor) {
        this.valor = valor;
    }

    public String getCodigo_barras() {
        return codigo_barras;
    }

    public void setCodigo_barras(String codigo_barras) {
        this.codigo_barras = codigo_barras;
    }

    public Integer getUnidades_empaque() {
        return unidades_empaque;
    }

    public void setUnidades_empaque(Integer unidades_empaque) {
        this.unidades_empaque = unidades_empaque;
    }

    public Integer getCliente_id() {
        return cliente_id;
    }

    public void setCliente_id(Integer cliente_id) {
        this.cliente_id = cliente_id;
    }
}
