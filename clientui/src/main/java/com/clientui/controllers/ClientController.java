package com.clientui.controllers;

import com.clientui.beans.CartBean;
import com.clientui.beans.CartLineBean;
import com.clientui.beans.CategoryBean;
import com.clientui.beans.CreditcartPaymentBean;
import com.clientui.beans.ListCartBean;
import com.clientui.beans.OrderInfoBean;
import com.clientui.beans.PaymentBean;
import com.clientui.beans.PaypalPaymentBean;
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
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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

    @GetMapping("/productByCategory/{idCategory}")
    @ResponseBody
    public List<ProductBean> getproductByCategory(@PathVariable int idCategory) {
        List<ProductBean> listProductByCategory = microserviceProdCatProxy.getproductByCategory(idCategory);
        return listProductByCategory;
    }

    @GetMapping("/category")
    @ResponseBody
    public List<CategoryBean> categoryList(){
        List<CategoryBean> categoryList = microserviceProdCatProxy.categoryList();
        return categoryList;
    }

    @GetMapping("/category/{idCategory}")
    @ResponseBody
    public CategoryBean getOneCategory(@PathVariable int idCategory){
        CategoryBean oneCategory = microserviceProdCatProxy.getOneCategory(idCategory);
        return oneCategory;
    }

    @PostMapping("/addCategory")
    @ResponseBody
    public ResponseEntity<CategoryBean> addCategory(@RequestBody CategoryBean category){
        ResponseEntity<CategoryBean> newCategory = microserviceProdCatProxy.addCategory(category);
        return newCategory;
    }

    @PutMapping("/updateCategory/{idCategory}")
    @ResponseBody
    public ResponseEntity<CategoryBean> updateCategory(@PathVariable int idCategory, @RequestBody CategoryBean category){
        ResponseEntity<CategoryBean> updateCategory = microserviceProdCatProxy.updateCategory(idCategory, category);
        return updateCategory;
    }

    @DeleteMapping("/deleteCategory/{idCategory}")
    @ResponseBody
    public Boolean deleteCategory(@PathVariable int idCategory){
        ResponseEntity<Void> deleteCategory = microserviceProdCatProxy.deleteCategory(idCategory);
        boolean isDeletedProduct = false;
        if (deleteCategory.getStatusCode()== HttpStatus.NO_CONTENT)
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

    @RequestMapping("deleteProductInCart/{idProductBean}/{idUser}")
    @ResponseBody
    public ResponseEntity<Void> deleteProductInCart(@PathVariable int idProductBean,@PathVariable int idUser){
        microservicesComLigComPanierProxy.deleteProductInCart(idProductBean, idUser);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @PutMapping("/updateQuantityProductInCart/{idUser}")
    @ResponseBody
    public ResponseEntity<CartLineBean> updateQuantityProductInCart(@RequestBody CartLineBean cartLine, @PathVariable int idUser){
        microservicesComLigComPanierProxy.updateQuantityProductInCart(cartLine,idUser);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/addPaymentCc/{idcart}")
    @ResponseBody
    public Boolean addCreditcartPayment(@PathVariable int idcart, @RequestBody CreditcartPaymentBean creditcartPayment){
        ResponseEntity<Boolean> savePayment = microservicesPaiementProxy.addCreditcartPayment(idcart,creditcartPayment);
        boolean isPayment = false;
        if (savePayment.getStatusCode() == HttpStatus.CREATED)
            isPayment = true;
        return isPayment;
    }

    @PostMapping("/addPaymentPp/{idcart}")
    @ResponseBody
    public Boolean PaypalPayment(@PathVariable int idcart, @RequestBody PaypalPaymentBean paypalPayment){
        ResponseEntity<Boolean> savePayment = microservicesPaiementProxy.PaypalPayment(idcart,paypalPayment);
        boolean isPayment = false;
        if (savePayment.getStatusCode() == HttpStatus.CREATED)
            isPayment = true;
        return isPayment;
    }

    @GetMapping("/OrdersByUser/{idUser}")
    @ResponseBody
    public List<OrderInfoBean> listOrderLine(@PathVariable int idUser){
        List<OrderInfoBean> listOrderByUser = microservicesComLigComPanierProxy.listOrderByUser(idUser);
        List<ProductBean> listProduct = new ArrayList<>();
        for (int i = 0; i < listOrderByUser.size() ; i++) {
            for (int j = 0; j <listOrderByUser.get(i).getListOrderLine().size() ; j++) {
                ProductBean productBean = microserviceProdCatProxy.getOneProduct(listOrderByUser.get(i).getListOrderLine().get(j).getIdProductBean());
                listProduct.add(productBean);
            }
        }
        listOrderByUser.get(listOrderByUser.size()-1).setListProductInCart(listProduct);
        return listOrderByUser;
    }

}
