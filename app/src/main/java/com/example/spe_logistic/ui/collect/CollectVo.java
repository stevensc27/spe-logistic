package com.example.spe_logistic.ui.collect;

public class CollectVo {
    private String id;
    private String address;
    private String scheduled_date;
    private String status;

    public CollectVo() {
    }

    public CollectVo(String id, String address, String scheduled_date, String status) {
        this.id = id;
        this.address = address;
        this.scheduled_date = scheduled_date;
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

    public String getScheduled_date() {
        return scheduled_date;
    }

    public void setScheduled_date(String scheduled_date) {
        this.scheduled_date = scheduled_date;
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
