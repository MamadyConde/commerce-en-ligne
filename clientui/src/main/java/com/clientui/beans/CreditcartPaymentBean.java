package com.clientui.beans;

import java.util.Date;

public class CreditcartPaymentBean extends PaymentBean {
    private int cardNumber;
    private Date dateExpiration;

    public CreditcartPaymentBean() {
        super();
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
