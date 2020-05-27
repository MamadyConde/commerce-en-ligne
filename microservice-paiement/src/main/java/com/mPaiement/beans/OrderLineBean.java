package com.mPaiement.beans;

public class OrderLineBean {
    private int id;
    private int quantity;
    private double price;
    private int idProductBean;
    private OrderInfoBean order;

    public OrderLineBean() {
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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getIdProductBean() {
        return idProductBean;
    }

    public void setIdProductBean(int idProductBean) {
        this.idProductBean = idProductBean;
    }

    public OrderInfoBean getOrder() {
        return order;
    }

    public void setOrder(OrderInfoBean order) {
        this.order = order;
    }
}
