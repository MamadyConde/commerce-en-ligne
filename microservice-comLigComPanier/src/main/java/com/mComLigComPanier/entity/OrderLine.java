package com.mComLigComPanier.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.mComLigComPanier.beans.ProductBean;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
public class OrderLine {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO,
            generator="native")
    @GenericGenerator(
            name = "native",
            strategy = "native"
    )
    private int id;
    private int quantity;
    private double price;
    private int idProductBean;
    @ManyToOne
    @JoinColumn(name = "IdOrder")
    @JsonBackReference
    private OrderInfo order;

    public OrderLine() {
    }

    public OrderLine(int quantity, double price,int idProductBean, OrderInfo order) {
        this.quantity = quantity;
        this.price = price;
        this.order = order;
        this.idProductBean = idProductBean;
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

    public OrderInfo getOrder() {
        return order;
    }

    public void setOrder(OrderInfo order) {
        this.order = order;
    }


    @Override
    public String toString() {
        return "OrderLine{" +
                "id=" + id +
                ", quantity=" + quantity +
                ", order=" + order +
                '}';
    }
}
