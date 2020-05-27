package com.mPaiement.entity;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.util.Date;
@Entity
@DiscriminatorValue("CC")
public class CreditcartPayment extends Payment {
    private int cardNumber;
    private Date dateExpiration;

    public CreditcartPayment() {
        super();
    }

    public CreditcartPayment(double amount, Date dateOrder, int cardNumber, Date dateExpiration) {
        super(amount, dateOrder);
        this.cardNumber = cardNumber;
        this.dateExpiration = dateExpiration;
    }

    public int getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(int cardNumber) {
        this.cardNumber = cardNumber;
    }

    public Date getDateExpiration() {
        return dateExpiration;
    }

    public void setDateExpiration(Date dateExpiration) {
        this.dateExpiration = dateExpiration;
    }
}
