package com.clientui.proxies;

import com.clientui.beans.CartBean;
import com.clientui.beans.CartLineBean;
import com.clientui.beans.OrderInfoBean;
import com.clientui.beans.ProductBean;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(name = "microservice-comLigComPanier", url = "localhost:8083")
public interface MicroservicesComLigComPanierProxy {
    @PostMapping("/addProductInCart/{quantityOrder}/{idUser}")
    ResponseEntity<Boolean> addProductInCart(@RequestBody ProductBean productBean, @PathVariable int quantityOrder, @PathVariable int idUser);

    @GetMapping("/CartsUser/{idUser}")
    List <CartBean> listCartByUser(@PathVariable int idUser);

    @DeleteMapping("/deleteProductInCart/{idProductBean}/{idUser}")
    ResponseEntity<Void> deleteProductInCart (@PathVariable int idProductBean,@PathVariable int idUser);

    @PutMapping("/updateQuantityProductInCart/{idUser}")
    ResponseEntity<CartLineBean> updateQuantityProductInCart(@RequestBody CartLineBean cartLine, @PathVariable int idUser);

    @GetMapping("/OrdersByUser/{idUser}")
    List<OrderInfoBean> listOrderByUser(@PathVariable int idUser);

}
