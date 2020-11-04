package com.mUtilisateur;

import com.mUtilisateur.entity.User;
import com.mUtilisateur.services.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class MicroserviceUtilisateurApplication implements CommandLineRunner {
	@Autowired
	private UserServiceImpl userService;
	public static void main(String[] args) {
		SpringApplication.run(MicroserviceUtilisateurApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		User u1 = new User("Paul","11");
		User u2 = new User("Jean","22");
		userService.addUser(u1);
		userService.addUser(u2);
	}
}
