package com.clientui.beans;

import java.util.Date;

public class PaypalPaymentBean extends PaymentBean{
    private String accountNumber;

    public PaypalPaymentBean() {
        super();
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }
}
