package com.mPaiement.beans;

import java.util.ArrayList;
import java.util.List;

public class CartBean {
    private int id;
    private List<CartLineBean> listCartLine = new ArrayList<>();
    private int idUser;
    public CartBean() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public List<CartLineBean> getListCartLine() {
        return listCartLine;
    }

    public void setListCartLine(List<CartLineBean> listCartLine) {
        this.listCartLine = listCartLine;
    }
}
