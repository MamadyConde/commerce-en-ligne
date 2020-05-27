package com.clientui.beans;

public class ListCartBean {
    private int id;
    private int QuantityTotal;
    private double AmountTotal;
    private int idProductBean;
    private String designation;
    private String description;
    private String photo;

    public ListCartBean() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getQuantityTotal() {
        return QuantityTotal;
    }

    public void setQuantityTotal(int QuantityTotal) {
        this.QuantityTotal = QuantityTotal;
    }

    public double getAmountTotal() {
        return AmountTotal;
    }

    public void setAmountTotal(double AmountTotal) {
        this.AmountTotal = AmountTotal;
    }

    public int getIdProductBean() {
        return idProductBean;
    }

    public void setIdProductBean(int idProductBean) {
        this.idProductBean = idProductBean;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }
}
