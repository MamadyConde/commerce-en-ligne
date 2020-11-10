package com.clientui.proxies;

import com.clientui.beans.CreditcartPaymentBean;
import com.clientui.beans.PaymentBean;
import com.clientui.beans.PaypalPaymentBean;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "microservice-paiement", url = "localhost:8084")
public interface MicroservicesPaiementProxy {
    @PostMapping("/addPaymentCc/{idcart}")
    ResponseEntity<Boolean> addCreditcartPayment(@PathVariable int idcart, @RequestBody CreditcartPaymentBean creditcartPayment);

    @PostMapping("/addPaymentPp/{idcart}")
    ResponseEntity<Boolean> PaypalPayment(@PathVariable int idcart, @RequestBody PaypalPaymentBean paypalPayment);
}
