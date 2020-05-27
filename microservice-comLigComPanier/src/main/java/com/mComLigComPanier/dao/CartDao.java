package com.mComLigComPanier.dao;

import com.mComLigComPanier.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CartDao extends JpaRepository<Cart, Integer> {
    List<Cart> findByIdUser(int IdUser);
}
