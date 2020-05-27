package com.mComLigComPanier.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.mComLigComPanier.beans.ProductBean;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
public class CartLine {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO,
            generator="native")
    @GenericGenerator(
            name = "native",
            strategy = "native"
    )
    private int id;
    private int quantity;
    private double amount;
    @ManyToOne
    @JoinColumn(name = "IdCart")
    @JsonBackReference
    private Cart cart;
    private int idProductBean;

    public CartLine(int quantity,double amount, int idProductBean) {
        this.quantity = quantity;
        this.amount = amount;
        this.idProductBean = idProductBean;
    }


    public CartLine(int quantity) {
        this.quantity = quantity;
    }

    public CartLine() {
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

/*    public double getAmount() {
        return this.productBean.getPrix()*this.quantity;
    }*/

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public double getAmount() {
        return amount;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public int getIdProductBean() {
        return idProductBean;
    }

    public void setIdProductBean(int idProductBean) {
        this.idProductBean = idProductBean;
    }

}
