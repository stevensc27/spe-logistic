package com.example.spe_logistic.entities;

public class Clientes {

    private Integer id;
    private String  razon_social;
    private String  nit;
    private String  password;
    private String  direccion;
    private Integer ciudad_id;

    public Clientes(Integer id, String razon_social, String nit, String password, String direccion, int ciudad_id) {
        this.id = id;
        this.razon_social = razon_social;
        this.nit = nit;
        this.password = password;
        this.direccion = direccion;
        this.ciudad_id = ciudad_id;
    }

    public Integer getCiudad_id() {
        return ciudad_id;
    }

    public void setCiudad_id(Integer ciudad_id) {
        this.ciudad_id = ciudad_id;
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

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
}
