package com.example.spe_logistic.entities;

public class Clientes {

    private Integer id;
    private String  razon_social;
    private String  nit;
    private String  password;
    private Integer direccion_id;

    public Clientes(Integer id, String razon_social, String nit, String password, Integer direccion_id) {
        this.id = id;
        this.razon_social = razon_social;
        this.nit = nit;
        this.password = password;
        this.direccion_id = direccion_id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRazon_social() {
        return razon_social;
    }

    public void setRazon_social(String razon_social) {
        this.razon_social = razon_social;
    }

    public String getNit() {
        return nit;
    }

    public void setNit(String nit) {
        this.nit = nit;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getDireccion_id() {
        return direccion_id;
    }

    public void setDireccion_id(Integer direccion_id) {
        this.direccion_id = direccion_id;
    }
}
