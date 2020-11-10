package com.mPaiement.proxies;

import com.mPaiement.beans.CartBean;
import com.mPaiement.beans.OrderInfoBean;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(name = "microservices-comLigComPanier", url = "localhost:8083")
public interface MicroservicesComLigComPanier {
    @GetMapping("/Orders/{idOrder}")
    OrderInfoBean getOneOrder(@PathVariable int idOrder);

    @GetMapping("/getOneCart/{idCart}")
    CartBean getOneCart(@PathVariable int idCart);

    @PostMapping("/saveOrder/{idCart}")
    ResponseEntity<Void> saveOrder(@PathVariable int idCart);
}
