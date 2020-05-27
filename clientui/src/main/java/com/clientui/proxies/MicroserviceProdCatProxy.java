package com.clientui.proxies;

import com.clientui.beans.ProductBean;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "microservice-prodCat", url = "localhost:8082")
public interface MicroserviceProdCatProxy {
    @GetMapping("/products")
    List<ProductBean> listProduct();
    @GetMapping( value = "/products/{idProduit}")
    ProductBean getOneProduct(@PathVariable int idProduit);
    @PutMapping("/updateProduct/{idProduit}")
    ResponseEntity<ProductBean> updateProduct(@PathVariable int idProduit, @RequestBody ProductBean product);
    @PostMapping("/addProduct")
    ResponseEntity<ProductBean> addProduct(@RequestBody ProductBean product);
    @DeleteMapping("/deleteProduct/{idProduit}")
    ResponseEntity<Void> deleteProduct(@PathVariable int idProduit);
}
