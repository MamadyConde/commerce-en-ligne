package com.mComLigComPanier.dao;

import com.mComLigComPanier.entity.OrderInfo;
import com.mComLigComPanier.entity.OrderLine;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderLineDao extends JpaRepository<OrderLine, Integer> {
    List<OrderLine> findByOrder(OrderInfo idOrder);
}
