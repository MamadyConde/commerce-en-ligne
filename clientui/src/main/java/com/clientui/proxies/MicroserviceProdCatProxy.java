package com.clientui.proxies;

import com.clientui.beans.CategoryBean;
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
    @GetMapping("/productByCategory/{idCategory}")
    List<ProductBean> getproductByCategory(@PathVariable int idCategory);
    @GetMapping("/category")
    List<CategoryBean> categoryList();
    @GetMapping("/category/{idCategory}")
    CategoryBean getOneCategory(@PathVariable int idCategory);
    @PostMapping("/addCategory")
    ResponseEntity<CategoryBean> addCategory(@RequestBody CategoryBean category);
    @PutMapping("/updateCategory/{idCategory}")
    ResponseEntity<CategoryBean> updateCategory(@PathVariable int idCategory, @RequestBody CategoryBean category);
    @DeleteMapping("/deleteCategory/{idCategory}")
    ResponseEntity<Void> deleteCategory(@PathVariable int idCategory);
}
