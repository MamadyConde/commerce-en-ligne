package com.mPaiement.proxies;

import com.mPaiement.beans.CartBean;
import com.mPaiement.beans.OrderInfoBean;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "microservices-comLigComPanier", url = "localhost:8083")
public interface MicroservicesComLigComPanier {
    @GetMapping("/Orders/{idOrder}")
    OrderInfoBean getOneOrder(@PathVariable int idOrder);

    @GetMapping("/Carts/{idCart}")
    CartBean getOneCart(@PathVariable int idCart);
}
