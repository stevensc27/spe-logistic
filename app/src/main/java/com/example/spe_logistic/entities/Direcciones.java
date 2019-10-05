package com.example.spe_logistic.entities;

public class Direcciones {

    private Integer id;
    private Integer tipo_via_id;
    private Integer numero_1;
    private String  letra_1;
    private Integer orientacion_1_id;
    private Integer numero_2;
    private String  letra_2;
    private Integer orientacion_2_id;
    private Integer numero_3;
    private Integer ciudad_id;

    public Direcciones(Integer id, Integer tipo_via_id, Integer numero_1, String letra_1, Integer orientacion_1_id, Integer numero_2, String letra_2, Integer orientacion_2_id, Integer numero_3, Integer ciudad_id) {
        this.id = id;
        this.tipo_via_id = tipo_via_id;
        this.numero_1 = numero_1;
        this.letra_1 = letra_1;
        this.orientacion_1_id = orientacion_1_id;
        this.numero_2 = numero_2;
        this.letra_2 = letra_2;
        this.orientacion_2_id = orientacion_2_id;
        this.numero_3 = numero_3;
        this.ciudad_id = ciudad_id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTipo_via_id() {
        return tipo_via_id;
    }

    public void setTipo_via_id(Integer tipo_via_id) {
        this.tipo_via_id = tipo_via_id;
    }

    public Integer getNumero_1() {
        return numero_1;
    }

    public void setNumero_1(Integer numero_1) {
        this.numero_1 = numero_1;
    }

    public String getLetra_1() {
        return letra_1;
    }

    public void setLetra_1(String letra_1) {
        this.letra_1 = letra_1;
    }

    public Integer getOrientacion_1_id() {
        return orientacion_1_id;
    }

    public void setOrientacion_1_id(Integer orientacion_1_id) {
        this.orientacion_1_id = orientacion_1_id;
    }

    public Integer getNumero_2() {
        return numero_2;
    }

    public void setNumero_2(Integer numero_2) {
        this.numero_2 = numero_2;
    }

    public String getLetra_2() {
        return letra_2;
    }

    public void setLetra_2(String letra_2) {
        this.letra_2 = letra_2;
    }

    public Integer getOrientacion_2_id() {
        return orientacion_2_id;
    }

    public void setOrientacion_2_id(Integer orientacion_2_id) {
        this.orientacion_2_id = orientacion_2_id;
    }

    public Integer getNumero_3() {
        return numero_3;
    }

    public void setNumero_3(Integer numero_3) {
        this.numero_3 = numero_3;
    }

    public Integer getCiudad_id() {
        return ciudad_id;
    }

    public void setCiudad_id(Integer ciudad_id) {
        this.ciudad_id = ciudad_id;
    }
}
