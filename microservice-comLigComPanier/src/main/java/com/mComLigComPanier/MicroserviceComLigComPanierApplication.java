package com.mComLigComPanier;

import com.mComLigComPanier.beans.ProductBean;
import com.mComLigComPanier.dao.CartLineDao;
import com.mComLigComPanier.entity.Cart;
import com.mComLigComPanier.entity.CartLine;
import com.mComLigComPanier.services.OrderCartServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@EnableFeignClients("com.mComLigComPanier")
public class MicroserviceComLigComPanierApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(MicroserviceComLigComPanierApplication.class, args);
	}
	@Autowired
	private OrderCartServiceImpl orderCartService;
	@Autowired
	private CartLineDao cartLineDao;
	@Override
	public void run(String... args) throws Exception {
		ProductBean P1 = new ProductBean(1);
		ProductBean P2 = new ProductBean(2);
		//CartLine cl = new CartLine(10,1.1,1);
		//CartLine cl2 = new CartLine(10,1.1,2);
		//List<CartLine> l = new ArrayList<CartLine>();
		//List<CartLine> ll = new ArrayList<CartLine>();
		//l.add(cl);
		//ll.add(cl2);
		//Cart c1 = new Cart(l);
		//Cart c2 = new Cart(ll);

		orderCartService.addProductInCart(P1, 2, 1);
		orderCartService.addProductInCart(P2, 2,2);
		//orderCartService.saveOrder(c1);
		//orderCartService.saveOrder(c2);


	}
}
