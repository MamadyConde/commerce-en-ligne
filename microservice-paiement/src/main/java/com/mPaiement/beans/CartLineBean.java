package com.mPaiement.beans;


public class CartLineBean {
    private int id;
    private int quantity;
    private double amount;
    private CartBean cart;
    private int idProductBean;

    public CartLineBean() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public CartBean getCart() {
        return cart;
    }

    public void setCart(CartBean cart) {
        this.cart = cart;
    }

    public int getIdProductBean() {
        return idProductBean;
    }

    public void setIdProductBean(int idProductBean) {
        this.idProductBean = idProductBean;
    }
}
