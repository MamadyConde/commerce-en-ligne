package com.clientui.beans;

import java.util.ArrayList;
import java.util.List;

public class CartBean {
    private int id;
    private List<CartLineBean> listCartLine = new ArrayList<>();
    private int idUser;
    private List<ProductBean> listProductInCart = new ArrayList<>();


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

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public int getQuantityTotal(){
        int quantity = 0;
        for (CartLineBean line : this.listCartLine){
            quantity+=line.getQuantity();
        }
        return quantity;
    }

    public double getAmountTotal() {
        double total = 0;
        for (CartLineBean line : this.listCartLine) {
            total += line.getAmount()*line.getQuantity();
        }
        return total;
    }

    public List<ProductBean> getlistProductInCart() {
        return listProductInCart;
    }

    public void setListProduct(List<ProductBean> listProductInCart) {
        this.listProductInCart = listProductInCart;
    }
}
