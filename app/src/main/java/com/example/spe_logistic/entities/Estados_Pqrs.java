package com.example.spe_logistic.entities;

public class Estados_Pqrs {

    private Integer id;
    private String  nombre;

    public Estados_Pqrs(Integer id, String nombre) {
        this.id = id;
        this.nombre = nombre;
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
}
