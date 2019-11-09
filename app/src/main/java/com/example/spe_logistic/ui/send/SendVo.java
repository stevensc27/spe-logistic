package com.example.spe_logistic.ui.send;

public class SendVo {
    private String id;
    private String name_receiver;
    private String address;
    private String status;

    public SendVo() {
    }

    public SendVo(String id, String address, String status, String name_receiver) {
        this.id = id;
        this.address = address;
        this.status = status;
        this.name_receiver = name_receiver;
    }

    public String getId() {
        return id;
    }

    public String getName_receiver() {
        return name_receiver;
    }

    public void setName_receiver(String name_receiver) {
        this.name_receiver = name_receiver;
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
