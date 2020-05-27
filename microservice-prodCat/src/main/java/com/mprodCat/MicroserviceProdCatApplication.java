package com.mprodCat;

import com.mprodCat.dao.CategoryDao;
import com.mprodCat.dao.ProductDao;
import com.mprodCat.entity.Category;
import com.mprodCat.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class MicroserviceProdCatApplication implements CommandLineRunner {

	@Autowired
	private ProductDao productDao;
	@Autowired
	private CategoryDao categoryDao;

	public static void main(String[] args) {
		SpringApplication.run(MicroserviceProdCatApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Category C1 = new Category("Informatique","Le monde de la programmation");
		categoryDao.save(C1);
		Category C2 = new Category("Economie","Le monde de l'economie");
		categoryDao.save(C2);
		Category C3 = new Category("Sciences","Le monde de la sciences");
		categoryDao.save(C3);

		Product P1 = new Product("Java","Apprendre Java",12.0,10,
				"https://academy.oracle.com/en/oa-assets/i/c82/c82-java-fund-logo.jpg",C1);
		Product P4 = new Product("Scala","Apprendre scala",10.0,10,
				"https://www.archer.ie/wp-content/uploads/2019/08/Scala_1.png",C1);
		Product P2 = new Product("Eco bancaire","Systeme bancaire",12.0,10,
				"https://www.madinamen.com/wp-content/uploads/2017/01/handshake-design_1200-80.jpg",C2);
		Product P5 = new Product("Eco financière","Systeme financier",15.0,10,
				"https://www.lgdj.fr/media/catalog/product/cache/1/image/9df78eab33525d08d6e5fb8d27136e95/c/o/comprendre-le-systeme-financier-9782749539294.jpg",C2);
		Product P3 = new Product("Biologie","Systeme immunitaire",12.0,10,"https://www.vivelessvt.com/wp-content/uploads/2008/10/systeme-immunitaire.jpg",C3);
		Product P6 = new Product("Geographie","science géographique ",16.0,10,
				"https://cdn.radiofrance.fr/s3/cruiser-production/2018/04/6d311fa8-184a-4efe-a6e7-1832ed0f0183/838_054_gut8181.jpg",C3);
		productDao.save(P1);
		productDao.save(P2);
		productDao.save(P3);
		productDao.save(P4);
		productDao.save(P5);
		productDao.save(P6);
/*
		List<Product> ll = productDao.findBycategory(C1);
		ll.forEach(System.out::println);*/
	}
}
