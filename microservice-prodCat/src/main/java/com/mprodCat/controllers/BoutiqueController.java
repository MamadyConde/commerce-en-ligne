package com.mprodCat.controllers;

import com.mprodCat.entity.Category;
import com.mprodCat.entity.Product;
import com.mprodCat.services.BoutiqueServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BoutiqueController {

    @Autowired
    private BoutiqueServiceImpl boutiqueService;

    @GetMapping("/products")
    public List<Product> productList(){
        return boutiqueService.getAllProducts();
    }

    @GetMapping("/products/{idProduit}")
    public Product getOneProduct(@PathVariable int idProduit){
        return boutiqueService.getOneProduct(idProduit);
    }

    @PutMapping("/updateProduct/{idProduit}")
    public ResponseEntity<Product> updateProduct(@PathVariable int idProduit, @RequestBody Product product){
        boutiqueService.updateProduct(idProduit, product);
        return new ResponseEntity<Product>(product, HttpStatus.OK);
    }

    @GetMapping("/productByCategory/{idCategory}")
    public List<Product> getproductByCategory(@PathVariable Category idCategory){

        return boutiqueService.productByCategory(idCategory);
    }

    @PostMapping("/addProduct")
    public ResponseEntity<Product> addProduct(@RequestBody Product product){
        boolean flag = boutiqueService.addProduit(product);
        if (!flag) return new ResponseEntity<>(product, HttpStatus.CONFLICT);
        return new ResponseEntity<>(product, HttpStatus.CREATED);
    }

    @DeleteMapping("/deleteProduct/{idProduit}")
    public ResponseEntity<Void> deleteProduct(@PathVariable int idProduit){
        boutiqueService.deleteProduct(idProduit);
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }
    @GetMapping("/category")
    public List<Category> categoryList(){
        return boutiqueService.getAllCategories();
    }

    @GetMapping("/category/{idCategory}")
    public Category getOneCategory(@PathVariable int idCategory){
        return boutiqueService.getOneCategory(idCategory);
    }

    @PostMapping("/addCategory")
    public ResponseEntity<Category> addCategory(@RequestBody Category category){
        boolean flag = boutiqueService.addCategory(category);
        if (!flag) return new ResponseEntity<>(HttpStatus.CONFLICT);
        return new ResponseEntity<Category>(category,HttpStatus.CREATED);
    }

    @PutMapping("/updateCategory/{idCategory}")
    public ResponseEntity<Category> updateCategory(@PathVariable int idCategory, @RequestBody Category category){
        boutiqueService.updateCategory(idCategory,category);
        return new ResponseEntity<Category>(category,HttpStatus.OK);
    }
    @DeleteMapping("/deleteCategory/{idCategory}")
    public ResponseEntity<Void> deleteCategory(@PathVariable int idCategory){
        boutiqueService.deleteCategory(idCategory);
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }

}
