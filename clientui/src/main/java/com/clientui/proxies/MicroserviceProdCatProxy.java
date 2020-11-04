package com.clientui.proxies;

import com.clientui.beans.ProductBean;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// avec Ribbon l'url ne sera pas en dur,
// ribbon s'occupe de la reaprtion entre charge de l'instance du microservice, la moins chargé, à prendre
@FeignClient(name = "microservice-prodCat")
@RibbonClient(name = "microservice-prodCat")
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
