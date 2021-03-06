package com.mComLigComPanier.beans;

public class ProductBean {
    private int id;
    private String designation;
    private String description;
    private Double prix;
    private int quantity;
    private String photo;
    private CategoryBean category;

    public ProductBean() {
    }

    public ProductBean(int id) {
        this.id = id;
    }

    public ProductBean(String designation, String description, Double prix, int quantity, String photo, CategoryBean category) {
        this.designation = designation;
        this.description = description;
        this.prix = prix;
        this.quantity = quantity;
        this.photo = photo;
        this.category = category;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public Double getPrix() {
        return prix;
    }

    public void setPrix(Double prix) {
        this.prix = prix;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public CategoryBean getCategory() {
        return category;
    }

    public void setCategory(CategoryBean category) {
        this.category = category;
    }
}
