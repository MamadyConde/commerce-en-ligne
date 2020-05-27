package com.mPaiement.beans;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OrderInfoBean {
    private int id;
    private Date dateOrder;
    private double amount;
    private List<OrderLineBean> listOrderLine = new ArrayList<>();

    public OrderInfoBean() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDateOrder() {
        return dateOrder;
    }

    public void setDateOrder(Date dateOrder) {
        this.dateOrder = dateOrder;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public List<OrderLineBean> getListOrderLine() {
        return listOrderLine;
    }

    public void setListOrderLine(List<OrderLineBean> listOrderLine) {
        this.listOrderLine = listOrderLine;
    }
}
