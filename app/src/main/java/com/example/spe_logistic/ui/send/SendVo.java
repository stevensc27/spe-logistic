package com.example.spe_logistic.ui.send;

public class SendVo {
    private String id;
    private String address;
    private String status;

    public SendVo() {
    }

    public SendVo(String id, String address, String status) {
        this.id = id;
        this.address = address;
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
