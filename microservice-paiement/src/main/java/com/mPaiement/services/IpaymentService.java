package com.mPaiement.services;

import com.mPaiement.beans.OrderInfoBean;
import com.mPaiement.entity.CreditcartPayment;
import com.mPaiement.entity.Payment;
import com.mPaiement.entity.PaypalPayment;

import java.util.List;

public interface IpaymentService {
    List<Payment> paymentList();
    void delete(OrderInfoBean orderInfoBean,int id);
    boolean addCreditcartPayment(int idorder, CreditcartPayment creditcartPayment);
    boolean addPaypalPayment(int idorder, PaypalPayment paypalPayment);
    Payment getOnePayment(int id);
    void updatePayment(Payment payment);
    List<Payment> paymentByTypePay(String typePay);
}
