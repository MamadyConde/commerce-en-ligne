package com.mPaiement.entity;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.util.Date;

@Entity
@DiscriminatorValue("PP")
public class PaypalPayment extends Payment {
    private String accountNumber;

    public PaypalPayment() {
        super();
    }

    public PaypalPayment(double amount, Date dateOrder, String accountNumber) {
        super(amount, dateOrder);
        this.accountNumber = accountNumber;
    }
}
