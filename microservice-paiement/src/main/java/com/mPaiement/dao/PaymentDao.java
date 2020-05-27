package com.mPaiement.dao;

import com.mPaiement.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PaymentDao extends JpaRepository<Payment, Integer> {
    List<Payment> findBytypePay(String typePay);
}
