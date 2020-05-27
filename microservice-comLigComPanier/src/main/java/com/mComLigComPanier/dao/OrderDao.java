package com.mComLigComPanier.dao;

import com.mComLigComPanier.entity.Cart;
import com.mComLigComPanier.entity.OrderInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderDao extends JpaRepository<OrderInfo, Integer> {
    List<OrderInfo> findByIdUser(int IdUser);

}
