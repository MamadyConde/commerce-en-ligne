package com.mPaiement.entity;

import com.mPaiement.beans.CartLineBean;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(discriminatorType = DiscriminatorType.STRING,name = "TYPE_PAY")
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    private int id;
    private double amount;
    private Date datePayment=new Date();
    @Column(name = "TYPE_PAY",insertable = false, updatable = false)
    protected String typePay;
    private int idCart;

    public Payment() {
    }

    public Payment(double amount, Date datePayment) {
        this.amount = amount;
        this.datePayment = datePayment;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Date getdatePayment() {
        return datePayment;
    }

    public void setdatePayment(Date datePayment) {
        this.datePayment = datePayment;
    }

    public String getTypePay() {
        return typePay;
    }

    public int getIdCart() {
        return idCart;
    }

    public void setIdCart(int idCart) {
        this.idCart = idCart;
    }

    @Override
    public String toString() {
        return "Payment{" +
                "id=" + id +
                ", amount=" + amount +
                ", dateCommande=" + datePayment +
                '}';
    }
}
