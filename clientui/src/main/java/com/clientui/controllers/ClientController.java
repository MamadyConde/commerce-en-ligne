package com.clientui.controllers;

import com.clientui.beans.CartBean;
import com.clientui.beans.CartLineBean;
import com.clientui.beans.ListCartBean;
import com.clientui.beans.ProductBean;
import com.clientui.proxies.MicroserviceProdCatProxy;
import com.clientui.proxies.MicroserviceUtilisateurProxy;
import com.clientui.proxies.MicroservicesComLigComPanierProxy;
import com.clientui.proxies.MicroservicesPaiementProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
public class ClientController {
    @Autowired
    private MicroserviceProdCatProxy microserviceProdCatProxy;
    @Autowired
    private MicroservicesComLigComPanierProxy microservicesComLigComPanierProxy;
    @Autowired
    private MicroservicesPaiementProxy microservicesPaiementProxy;
    @Autowired
    private MicroserviceUtilisateurProxy microserviceUtilisateurProxy;

    @RequestMapping("/")
    @ResponseBody
    public List<ProductBean> accueil(Model model){
        List<ProductBean> listproducts =  microserviceProdCatProxy.listProduct();
        model.addAttribute("products", listproducts);
        return listproducts;
    }
    @RequestMapping("/details-product/{idProduit}")
    @ResponseBody
    public ProductBean detailProduct(@PathVariable int idProduit, Model model) {
        ProductBean product = microserviceProdCatProxy.getOneProduct(idProduit);
        model.addAttribute("product", product);
        return product;
    }
    @RequestMapping("/updateProduct/{idProduit}")
    @ResponseBody
    public ResponseEntity<ProductBean> updateProduct(@PathVariable int idProduit, @RequestBody ProductBean productBean, Model model) {
        ResponseEntity<ProductBean> product = microserviceProdCatProxy.updateProduct(idProduit, productBean);
        model.addAttribute("product", product);
        return product;
    }
    @RequestMapping("/addProduct")
    @ResponseBody
    public ResponseEntity<ProductBean> addProduct(@RequestBody ProductBean productBean, Model model) {
        ResponseEntity<ProductBean> product = microserviceProdCatProxy.addProduct(productBean);
        model.addAttribute("product", product);
        return product;
    }
    @RequestMapping("/deleteProduct/{idProduit}")
    @ResponseBody
    public Boolean deleteProduct(@PathVariable int idProduit, Model model) {
        ResponseEntity<Void> product = microserviceProdCatProxy.deleteProduct(idProduit);
        model.addAttribute("product", product);
        boolean isDeletedProduct = false;
        if (product.getStatusCode()== HttpStatus.NO_CONTENT)
            isDeletedProduct = true;
        return isDeletedProduct;
    }
    @RequestMapping("/addProductInCart/{quantityOrder}/{idUser}")
    @ResponseBody
    public Boolean addProductInCart(@RequestBody ProductBean productBean, @PathVariable int quantityOrder, @PathVariable int idUser ){
        ResponseEntity<Boolean> cart = microservicesComLigComPanierProxy.addProductInCart(productBean, quantityOrder, idUser);
        boolean isInpanier = false;
        if (cart.getStatusCode() == HttpStatus.CREATED)
            isInpanier = true;
        return isInpanier;
    }

    @RequestMapping("/CartsUser/{idUser}")
    @ResponseBody
    public List listCartByUser(@PathVariable int idUser ){
        List <CartBean> cartList = microservicesComLigComPanierProxy.listCartByUser(idUser);
        List<ProductBean> listProduct = new ArrayList<>();

        for (int i = 0; i < cartList.size(); i++) {
            for (int j = 0; j < cartList.get(i).getListCartLine().size() ; j++) {
                ProductBean productBean = microserviceProdCatProxy.getOneProduct(cartList.get(i).getListCartLine().get(j).getIdProductBean());
                listProduct.add(productBean);
            }
        }
        cartList.get(cartList.size()-1).setListProduct(listProduct);
        return cartList;
    }
}
