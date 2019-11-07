package com.example.spe_logistic.ui.perfil;

public class PerfilPqrVo {
    private String id;
    private String category;
    private String description;
    private String state;

    public PerfilPqrVo() {
    }

    public PerfilPqrVo(String id, String category, String description, String state) {
        this.id = id;
        this.category = category;
        this.description = description;
        this.state = state;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
