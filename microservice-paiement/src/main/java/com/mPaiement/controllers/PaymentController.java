package com.mPaiement.controllers;

import com.mPaiement.beans.OrderInfoBean;
import com.mPaiement.entity.CreditcartPayment;
import com.mPaiement.entity.Payment;
import com.mPaiement.entity.PaypalPayment;
import com.mPaiement.services.PaymentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PaymentController {
    @Autowired
    private PaymentServiceImpl paymentService;
/*
    @GetMapping("/payments")
    public List<Payment> paymentList(){
        return paymentService.paymentList();
    }
    */

/*
    @GetMapping("/payments/{idPayment}")
    public Payment getOnePayment(@PathVariable int idPayment){
        return paymentService.getOnePayment(idPayment);
    }
*/
    @PostMapping("/addPaymentCc/{idcart}")
    public ResponseEntity<Boolean> addCreditcartPayment(@PathVariable int idcart, @RequestBody CreditcartPayment creditcartPayment){
        boolean savePayment = paymentService.addCreditcartPayment(idcart,creditcartPayment);
        if (!savePayment) return new ResponseEntity<>(HttpStatus.CONFLICT);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

   @PostMapping("/addPaymentPp/{idcart}")
    public ResponseEntity<Boolean> PaypalPayment(@PathVariable int idcart, @RequestBody PaypalPayment paypalPayment){
        boolean savePayment = paymentService.addPaypalPayment(idcart, paypalPayment);
        if (!savePayment) return new ResponseEntity<>(HttpStatus.CONFLICT);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
/*
    @DeleteMapping("/payments/{idPayment}")
    public ResponseEntity<Void> deletePayment(@RequestBody OrderInfoBean orderInfoBean,@PathVariable int idPayment){
        paymentService.delete(orderInfoBean, idPayment);
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }
*/
/*
    @GetMapping("/paymentsType/{typePay}")
    public List<Payment> paymentByTypePayList(@PathVariable String typePay){
        return paymentService.paymentByTypePay(typePay);
    }

 */
}
