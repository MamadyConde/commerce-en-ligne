package com.mPaiement.beans;

import java.util.ArrayList;
import java.util.List;

public class CategoryBean {
    private int id;
    private String name;
    private String description;
    private List<ProductBean> productsList = new ArrayList<>();

    public CategoryBean() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<ProductBean> getProductsList() {
        return productsList;
    }

    public void setProductsList(List<ProductBean> productsList) {
        this.productsList = productsList;
    }
}
