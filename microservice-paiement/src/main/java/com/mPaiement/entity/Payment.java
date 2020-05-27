package com.mPaiement.entity;

import javax.persistence.*;
import java.util.Date;

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

    @Override
    public String toString() {
        return "Payment{" +
                "id=" + id +
                ", amount=" + amount +
                ", dateCommande=" + datePayment +
                '}';
    }
}
