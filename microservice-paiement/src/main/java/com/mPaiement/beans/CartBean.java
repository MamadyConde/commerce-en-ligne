package com.mPaiement.beans;

import java.util.ArrayList;
import java.util.List;

public class CartBean {
    private int id;
    private List<CartLineBean> listCartLine = new ArrayList<>();

    public CartBean() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<CartLineBean> getListCartLine() {
        return listCartLine;
    }

    public void setListCartLine(List<CartLineBean> listCartLine) {
        this.listCartLine = listCartLine;
    }
}
