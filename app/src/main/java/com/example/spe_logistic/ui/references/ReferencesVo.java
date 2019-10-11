package com.example.spe_logistic.ui.references;

public class ReferencesVo {
    private String id;
    private String reference;
    private String code_bar;
    private String description;

    public ReferencesVo() {
    }

    public ReferencesVo(String id, String reference, String code_bar, String description) {
        this.id = id;
        this.reference = reference;
        this.code_bar = code_bar;
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public String getCode_bar() {
        return code_bar;
    }

    public void setCode_bar(String code_bar) {
        this.code_bar = code_bar;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
