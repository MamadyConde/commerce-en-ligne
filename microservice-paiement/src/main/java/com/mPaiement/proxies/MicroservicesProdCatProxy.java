package com.mPaiement.proxies;

import com.mPaiement.beans.ProductBean;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "microservice-prodCat", url = "localhost:8082")
public interface MicroservicesProdCatProxy {
    @GetMapping("/products/{idProduit}")
    ProductBean getOneProduct(@PathVariable int idProduit);

    @PutMapping("/updateProduct/{idProduit}")
    ResponseEntity<ProductBean> updateProduct(@PathVariable int idProduit, @RequestBody ProductBean product);
}
