package com.mComLigComPanier.proxies;

import com.mComLigComPanier.beans.ProductBean;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@FeignClient(name = "microservice-prodCat", url = "localhost:8082")
public interface MicroserviceProductProxy {
    @GetMapping("/products/{idProduit}")
    ProductBean getOneProduct(@PathVariable int idProduit);
    @PutMapping("/updateProduct/{idProduit}")
    ResponseEntity<ProductBean> updateProduct(@PathVariable int idProduit, @RequestBody ProductBean product);
}
