package com.example.spe_logistic.ui.collect;

public class CollectVo {
    private String id;
    private String address;
    private String date;
    private String status;

    public CollectVo() {
    }

    public CollectVo(String id, String address, String date, String status) {
        this.id = id;
        this.address = address;
        this.date = date;
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
