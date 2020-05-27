package com.mComLigComPanier.dao;

import com.mComLigComPanier.entity.Cart;
import com.mComLigComPanier.entity.CartLine;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CartLineDao extends JpaRepository<CartLine, Integer> {
    Optional<CartLine> findByIdProductBean(int IdProduct);
    Optional<CartLine> findByCartAndIdProductBean(Cart Cart, int IdProduct);
    CartLine findByCart(Cart idCart);
}
