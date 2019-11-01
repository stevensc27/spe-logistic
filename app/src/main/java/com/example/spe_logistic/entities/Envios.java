package com.example.spe_logistic.entities;

import java.util.Date;

public class Envios {

    private Integer id;
    private String  nombre_destinatario;
    private String  direccion_destinatario;
    private Integer ciudad_destinatario_id;
    private String  telefono_destinatario;
    private String  email_destinatario;
    private String  factura;
    private String  archivo;
    private Integer despacho_id;
    private Date    fecha_reservado;
    private Date    fecha_alistado;
    private Date    fecha_empacado;
    private Integer cliente_id;
    private Integer estado_id;

    public Envios() {
    }

    public Envios(Integer id, String nombre_destinatario, String direccion_destinatario, Integer ciudad_destinatario_id, String telefono_destinatario, String email_destinatario, String factura, String archivo, Integer despacho_id, Date fecha_alistado, Date fecha_empacado, Integer cliente_id, Integer estado_id) {
        this.id = id;
        this.nombre_destinatario = nombre_destinatario;
        this.direccion_destinatario = direccion_destinatario;
        this.ciudad_destinatario_id = ciudad_destinatario_id;
        this.telefono_destinatario = telefono_destinatario;
        this.email_destinatario = email_destinatario;
        this.factura = factura;
        this.archivo = archivo;
        this.despacho_id = despacho_id;
        this.fecha_alistado = fecha_alistado;
        this.fecha_empacado = fecha_empacado;
        this.cliente_id = cliente_id;
        this.estado_id = estado_id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre_destinatario() {
        return nombre_destinatario;
    }

    public void setNombre_destinatario(String nombre_destinatario) {
        this.nombre_destinatario = nombre_destinatario;
    }

    public String getDireccion_destinatario() {
        return direccion_destinatario;
    }

    public void setDireccion_destinatario(String direccion_destinatario) {
        this.direccion_destinatario = direccion_destinatario;
    }

    public Integer getCiudad_destinatario_id() {
        return ciudad_destinatario_id;
    }

    public void setCiudad_destinatario_id(Integer ciudad_destinatario_id) {
        this.ciudad_destinatario_id = ciudad_destinatario_id;
    }

    public String getTelefono_destinatario() {
        return telefono_destinatario;
    }

    public void setTelefono_destinatario(String telefono_destinatario) {
        this.telefono_destinatario = telefono_destinatario;
    }

    public String getEmail_destinatario() {
        return email_destinatario;
    }

    public void setEmail_destinatario(String email_destinatario) {
        this.email_destinatario = email_destinatario;
    }

    public String getFactura() {
        return factura;
    }

    public void setFactura(String factura) {
        this.factura = factura;
    }

    public String getArchivo() {
        return archivo;
    }

    public void setArchivo(String archivo) {
        this.archivo = archivo;
    }

    public Integer getDespacho_id() {
        return despacho_id;
    }

    public void setDespacho_id(Integer despacho_id) {
        this.despacho_id = despacho_id;
    }

    public Date getFecha_alistado() {
        return fecha_alistado;
    }

    public void setFecha_alistado(Date fecha_alistado) {
        this.fecha_alistado = fecha_alistado;
    }

    public Date getFecha_empacado() {
        return fecha_empacado;
    }

    public void setFecha_empacado(Date fecha_empacado) {
        this.fecha_empacado = fecha_empacado;
    }

    public Integer getCliente_id() {
        return cliente_id;
    }

    public void setCliente_id(Integer cliente_id) {
        this.cliente_id = cliente_id;
    }

    public Integer getEstado_id() {
        return estado_id;
    }

    public void setEstado_id(Integer estado_id) {
        this.estado_id = estado_id;
    }
}
