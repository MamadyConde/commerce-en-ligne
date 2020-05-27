package com.mComLigComPanier.services;

import com.mComLigComPanier.beans.ProductBean;
import com.mComLigComPanier.entity.Cart;
import com.mComLigComPanier.entity.CartLine;
import com.mComLigComPanier.entity.OrderInfo;
import com.mComLigComPanier.entity.OrderLine;

import java.util.List;

public interface IorderCartService {
    boolean addProductInCart(ProductBean productBean, int quantityOrder, int idUser);
    void updateQuantityProductInCart(CartLine cartLine);
    void deleteProductInCart(int idProductBean, int idUser);
    List<Cart> listCartByUser(int idUser);


    boolean saveOrder(List<Cart> cartList);// et saveOrderLine(CartLine cartLine); dans la même methode
    List<OrderInfo> listOrder();
    List<OrderInfo> listOrderByUser(int idUser);
    void updateOrder(List<OrderInfo> orderList);
    void deleteOneCartInOrder(int idOrder);
    OrderInfo getOneOrder(int id);
    List<OrderLine> listOrderLine(OrderInfo idOrder);
    List<Cart> listCart();
    Cart getOneCart(int id);

}
