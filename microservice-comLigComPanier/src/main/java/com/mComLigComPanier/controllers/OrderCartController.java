package com.mComLigComPanier.controllers;

import com.mComLigComPanier.beans.ProductBean;
import com.mComLigComPanier.entity.Cart;
import com.mComLigComPanier.entity.CartLine;
import com.mComLigComPanier.entity.OrderInfo;
import com.mComLigComPanier.entity.OrderLine;
import com.mComLigComPanier.services.OrderCartServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class OrderCartController {
    @Autowired
    private OrderCartServiceImpl orderCartService;

    @GetMapping("/Orders")
    public List<OrderInfo> listOrder(){
        return orderCartService.listOrder();
    }

    /*@GetMapping("/Orders/{idOrder}")
    public OrderInfo getOneOrder(@PathVariable int idOrder){
        return orderCartService.getOneOrder(idOrder);
    }
    @GetMapping("/LineOrders")
    public List<OrderLine> listOrderLine(@RequestBody OrderInfo idOrder){
        return orderCartService.listOrderLine(idOrder);
    }*/

    /*@GetMapping("/Carts")
    public List<Cart> cartList(){
        return orderCartService.listCart();
    }*/

   /* @GetMapping("/Carts/{idCart}")
    public Cart getOneCart(@PathVariable int idCart){
        return orderCartService.getOneCart(idCart);
    }*/
   @PostMapping("/saveOrder")
   public ResponseEntity<Void> saveOrder(@RequestBody List<Cart> cart){
       boolean flag = orderCartService.saveOrder(cart);
       if (!flag) return new ResponseEntity<>(HttpStatus.CONFLICT);
       return new ResponseEntity<>(HttpStatus.CREATED);
   }

   @GetMapping("/OrdersUser/{idUser}")
   public List<OrderInfo> listOrderLine(@PathVariable int idUser){
       return orderCartService.listOrderByUser(idUser);
   }

    @GetMapping("/CartsUser/{idUser}")
    public List<Cart> listCartByUser(@PathVariable int idUser){
        return orderCartService.listCartByUser(idUser);
    }

    @PostMapping("/addProductInCart/{quantityOrder}/{idUser}")
    public ResponseEntity<Void> addProductInCart(@RequestBody ProductBean productBean, @PathVariable int quantityOrder, @PathVariable int idUser){
        boolean flag = orderCartService.addProductInCart(productBean, quantityOrder,idUser);
        if (!flag) return new ResponseEntity<>(HttpStatus.CONFLICT);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping("/deleteProductInCart/{idProductBean}/{idUser}")
    public ResponseEntity<Void> deleteProductInCart (@PathVariable int idProductBean,@PathVariable int idUser){
        orderCartService.deleteProductInCart(idProductBean,idUser);
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }
    @PutMapping("/updateQuantityProductInCart")
    public ResponseEntity<CartLine> updateQuantityProductInCart(@RequestBody CartLine cartLine){
        orderCartService.updateQuantityProductInCart(cartLine);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
