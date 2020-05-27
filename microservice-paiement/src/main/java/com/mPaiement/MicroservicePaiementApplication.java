package com.mPaiement;

import com.mPaiement.dao.PaymentDao;
import com.mPaiement.entity.CreditcartPayment;
import com.mPaiement.entity.PaypalPayment;
import com.mPaiement.services.PaymentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

import java.text.SimpleDateFormat;
import java.util.Date;

@SpringBootApplication
@EnableFeignClients("com.mPaiement")
public class MicroservicePaiementApplication  implements CommandLineRunner {
	@Autowired
	private PaymentServiceImpl paymentService;
	@Autowired
	private PaymentDao paymentDao;

	public static void main(String[] args) {
		SpringApplication.run(MicroservicePaiementApplication.class, args);
	}

	SimpleDateFormat formater = new SimpleDateFormat("dd/MM/yy");

	@Override
	public void run(String... args) throws Exception {
		//paymentService.addCreditcartPayment(new CreditcartPayment(12.0,new Date(),1111, formater.parse("20/04/2020")));
		//paymentService.addPaypalPayment(new PaypalPayment(12.0,new Date(),"22"));
		//paymentService.addCreditcartPayment(new CreditcartPayment(10.0,new Date(),1111, formater.parse("21/04/2020")));
	}
}
