package com.mComLigComPanier.services;

import com.mComLigComPanier.beans.ProductBean;
import com.mComLigComPanier.dao.CartDao;
import com.mComLigComPanier.dao.CartLineDao;
import com.mComLigComPanier.dao.OrderDao;
import com.mComLigComPanier.dao.OrderLineDao;
import com.mComLigComPanier.entity.Cart;
import com.mComLigComPanier.entity.CartLine;
import com.mComLigComPanier.entity.OrderInfo;;
import com.mComLigComPanier.entity.OrderLine;
import com.mComLigComPanier.exceptions.CartLineNotFoundException;
import com.mComLigComPanier.exceptions.OrderNotFoundException;
import com.mComLigComPanier.proxies.MicroserviceProductProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class OrderCartServiceImpl implements IorderCartService {
    @Autowired
    private OrderDao orderDao;
    @Autowired
    private OrderLineDao orderLineDao;
    @Autowired
    private CartDao cartDao;
    @Autowired
    private CartLineDao cartLineDao;
    @Autowired
    private MicroserviceProductProxy microserviceProductProxy;

    @Override
    public boolean addProductInCart(ProductBean productBean, int quantityOrder, int idUser) {
        ProductBean getProduct = microserviceProductProxy.getOneProduct(productBean.getId());
        if (quantityOrder > getProduct.getQuantity()) throw new CartLineNotFoundException("La quantité de produit avec " +
                "l'Id "+productBean.getId()+" est Insuffisante");
        List<Cart> getCart = cartDao.findByIdUser(idUser);
        List<CartLine> listCL = new ArrayList<>();
       Cart newCart = new Cart();
        for (Cart cart : getCart){
            newCart = cart;
            for (CartLine line: cart.getListCartLine()){
                if (line.getIdProductBean() == productBean.getId()){
                listCL.add(cart.getListCartLine().get(0));
                }
            }
        }
        if (listCL.isEmpty()){
            CartLine newCartLine = new CartLine();
            newCartLine.setQuantity(quantityOrder);
            newCartLine.setAmount(getProduct.getPrix());
            newCartLine.setIdProductBean(getProduct.getId());
            cartLineDao.save(newCartLine);
            newCart.getListCartLine().add(newCartLine);
            newCart.setIdUser(idUser);
            cartDao.save(newCart);
            newCartLine.setCart(newCart);
            cartLineDao.save(newCartLine);
            return true;
        }
        Optional<CartLine> getCartLine = cartLineDao.findByCartAndIdProductBean(newCart, productBean.getId());
        if (!getCartLine.isPresent()) throw new CartLineNotFoundException("le panier du produit "+productBean.getId()+" n'existe pas");
        int newQuantity = getCartLine.get().getQuantity() + quantityOrder;
        getCartLine.get().setQuantity(newQuantity);
        cartLineDao.save(getCartLine.get());
        return true;
    }

    // list product in cart by user
    @Override
    public List<Cart> listCartByUser(int idUser) {
        List<Cart> cartList = cartDao.findByIdUser(idUser);
        if (cartList.isEmpty()) throw new CartLineNotFoundException("Le panier de cet utilisateur avec l'id "+idUser+" est vide");
        return cartList;
    }

    @Override
    public void updateQuantityProductInCart(CartLine cartLine, int idUser) {
        int idProduct = cartLine.getIdProductBean();
        List<Cart> cartList = cartDao.findByIdUser(idUser);
        if (cartList.isEmpty()) throw new CartLineNotFoundException("Le panier de l'utilisateur "+idUser+" est vide");
        for (Cart cart: cartList){
            for (CartLine line: cart.getListCartLine()){
                if (line.getIdProductBean()==cartLine.getIdProductBean()){
                    Optional<CartLine> getCartLine = cartLineDao.findByCartAndIdProductBean(cart,idProduct);
                    if (!getCartLine.isPresent()) throw new CartLineNotFoundException("le panier du produit "+idProduct+" n'existe pas");
                    ProductBean getProduct = microserviceProductProxy.getOneProduct(idProduct);
                    int newQuantity = 0;
                    int newQuantityProduct =0;
                    if (getCartLine.get().getQuantity() > cartLine.getQuantity() ){
                         newQuantity = getCartLine.get().getQuantity() - cartLine.getQuantity();
                         newQuantityProduct = getProduct.getQuantity() + newQuantity;
                         getProduct.setQuantity(newQuantityProduct);
                    } else {
                        newQuantity = cartLine.getQuantity() - getCartLine.get().getQuantity();
                        if(getProduct.getQuantity() < newQuantity) throw new CartLineNotFoundException("La quantité du produit "+idProduct+" est insuffisante");
                    }
                    getCartLine.get().setQuantity(cartLine.getQuantity());
                    getCartLine.get().setAmount(cartLine.getAmount());
                    getCartLine.get().setQuantity(cartLine.getQuantity());
                    cartLineDao.save(getCartLine.get());
                    microserviceProductProxy.updateProduct(getProduct.getId(),getProduct);
                }
            }
        }
    }

    @Override
    public void deleteProductInCart(int IdProductBean, int idUser) {
        ProductBean getProduct = microserviceProductProxy.getOneProduct(IdProductBean);
        List<Cart> cartList = cartDao.findByIdUser(idUser);
        if (cartList.isEmpty()) throw new CartLineNotFoundException("Le panier de l'utilisateur "+idUser+" est vide");
        for (Cart cart: cartList){
            for (CartLine line: cart.getListCartLine()){
                if (line.getIdProductBean()==IdProductBean){
                    Optional<CartLine> getCartLine = cartLineDao.findByCartAndIdProductBean(cart,IdProductBean);
                    if (!getCartLine.isPresent()) throw new CartLineNotFoundException("le panier du produit "+IdProductBean+" n'existe pas");
                    cartLineDao.delete(getCartLine.get());
                    if (cart.getListCartLine().size() == 1) {
                        cartDao.delete(getCartLine.get().getCart());
                    }
                    int newQuantityProduct = getProduct.getQuantity() + line.getQuantity();
                    getProduct.setQuantity(newQuantityProduct);
                    microserviceProductProxy.updateProduct(getProduct.getId(),getProduct);
                }
            }
        }

    }

    @Override
    public boolean saveOrder(int idCart) {
            Optional<Cart> getCart = cartDao.findById(idCart);
            OrderInfo newOrder = new OrderInfo();
            if (!getCart.isPresent()) throw new CartLineNotFoundException("Le panier avec l'id "+ getCart.get().getId()+ "n'existe pas");
            newOrder.setAmount(getCart.get().getAmountTotal());
            newOrder.setDateOrder(new Date());
            newOrder.setIdUser(getCart.get().getIdUser());
            orderDao.save(newOrder);
            List<CartLine> cartLines = getCart.get().getListCartLine();

            for (int i =0; i< cartLines.size(); i++){
                ProductBean getProduct = microserviceProductProxy.getOneProduct(getCart.get().getListCartLine().get(i).getIdProductBean());
                int newQuantityProduct = getProduct.getQuantity() - getCart.get().getListCartLine().get(i).getQuantity();
                getProduct.setQuantity(newQuantityProduct);
                microserviceProductProxy.updateProduct(getProduct.getId(),getProduct);
                cartLineDao.delete(cartLines.get(i));
            }
            for (CartLine line: cartLines){
                OrderLine newOrderLine = new OrderLine();
                newOrderLine.setOrder(newOrder);
                newOrderLine.setPrice(line.getAmount());
                newOrderLine.setQuantity(line.getQuantity());
                newOrderLine.setIdProductBean(line.getIdProductBean());
                orderLineDao.save(newOrderLine);
            }
            cartDao.delete(getCart.get());
    return true;
    }

    @Override
    public List<OrderInfo> listOrder() {
        List<OrderInfo> listOrder = orderDao.findAll();
        if (listOrder.isEmpty()) throw new OrderNotFoundException("Il n'existe aucune commande");
        return listOrder;
    }

    @Override
    public OrderInfo getOneOrder(int id) {
        Optional<OrderInfo> oneOrder = orderDao.findById(id);
        if (!oneOrder.isPresent()) throw new OrderNotFoundException("La commande avec l'id "+id+" n'existe pas");
        return oneOrder.get();
    }

    @Override
    public List<OrderLine> listOrderLine(OrderInfo idOrder) {
        List<OrderLine> listOrderLine = orderLineDao.findByOrder(idOrder);
        if (listOrderLine.isEmpty()) throw new OrderNotFoundException("Il n'existe pas la ligne de commande avec L'id "+idOrder);
        return listOrderLine;
    }

    @Override
    public List<OrderInfo> listOrderByUser(int idUser) {
        List<OrderInfo> orderList = orderDao.findByIdUser(idUser);
        if (orderList.isEmpty()) throw new OrderNotFoundException("La commande avec l'id "+idUser+" n'existe pas");
        return orderList;
    }

    @Override
    public void updateOrder(List<OrderInfo> orderList) {
        for (OrderInfo order:orderList ) {
            Optional<OrderInfo> getOrder = orderDao.findById(order.getId());
            List<OrderLine> orderLine = getOrder.get().getListOrderLine();
            for (OrderLine line: orderLine){
                Optional<OrderLine> getOrderLine = orderLineDao.findById(line.getId());
                if(!getOrderLine.isPresent()) throw new OrderNotFoundException("La ligne de cette commande Id "+line.getId()+ "n'existe pas");
                getOrderLine.get().setQuantity(line.getQuantity());
                orderLineDao.save(getOrderLine.get());
            }
        }
    }

    @Override
    public void deleteOneCartInOrder(int idOrder) {

    }

    @Override
    public List<Cart> listCart() {
        List<Cart> cartList = cartDao.findAll();
        if (cartList.isEmpty()) throw new CartLineNotFoundException("Le panier est vide");
        return cartList;
    }


    @Override
    public Cart getOneCart(int id) {
        Optional<Cart> oneCart = cartDao.findById(id);
        if(!oneCart.isPresent()) throw new CartLineNotFoundException("Le panier avec l'id "+ id+" est vide");
        return oneCart.get();
    }
}
